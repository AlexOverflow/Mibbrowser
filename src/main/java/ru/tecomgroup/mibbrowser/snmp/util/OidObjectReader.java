package ru.tecomgroup.mibbrowser.snmp.util;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpRequest;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpResponse;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class OidObjectReader {

    private Snmp snmp;
    private Address address;

    private SnmpResponse lastResponse;
    private  SnmpRequest lastRequest;

    OidObjectReader(String addres){
        DefaultUdpTransportMapping mapping = null;
        try {
            mapping  = new DefaultUdpTransportMapping();
        } catch (Exception e) {
            e.printStackTrace();
        }
        snmp = new Snmp(mapping);
        try {
            snmp.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

        address = GenericAddress.parse(addres);
    }

    public SnmpResponse getObj(SnmpRequest request){
        Target target = getTargetFromConfig(request);
        PDU pdu = createPDU(request);
        ResponseEvent event = null;
        try {
            event = snmp.get(pdu, target);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SnmpResponse response = getRespObjFromEvent(event);
        lastResponse = response;
        lastRequest = request;
        return response;
    }

    public SnmpResponse getNextObj(SnmpRequest request){
        Target target = getTargetFromConfig(request);
        PDU pdu = createPDU(request);
        ResponseEvent event = null;
        try {
            event = snmp.getNext(pdu, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SnmpResponse response = getRespObjFromEvent(event);
        lastResponse = response;
        lastRequest = request;
        return response;
    }

    public SnmpResponse walk(SnmpRequest request){
        SnmpResponse response = null;
        List<SnmpVariable> variableList = new LinkedList<>();
        SnmpResponse resp = getObj(request);
        if(resp != null){
            variableList.add(resp.getVariableList().get(0));
            while( (resp = getNextObj(getRequestFromResponse(resp))) != null){
                variableList.add(resp.getVariableList().get(0));
            }
            response = new SnmpResponse(variableList);
        }

         return  response;
    }


    private PDU createPDU(SnmpRequest request){
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(request.getOid())));
        return pdu;
    }

    private Target getTargetFromConfig(SnmpRequest request){
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setRetries(2);
        target.setTimeout(1500);
        target.setAddress(GenericAddress.parse(request.getAddress()));
        target.setVersion(SnmpConstants.version2c);
        return target;
    }

    protected SnmpResponse getRespObjFromEvent(ResponseEvent event){
        List<SnmpVariable> listVariable = new ArrayList<>();
       PDU pdu = event.getResponse();
        Vector<VariableBinding> variableBindings = pdu.getVariableBindings();
        for(VariableBinding v : variableBindings){
            if(!("endOfMibView".equals(v.toValueString())))
           listVariable.add(new SnmpVariable(v.getOid().toString(), v.toValueString()));
        }
        SnmpResponse resp;
        if(listVariable.isEmpty()){
            resp = null;
        } else{
            resp = new SnmpResponse(listVariable);
        }
        return resp;
    }

    public void close(){
        try {
            snmp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SnmpRequest getRequestFromResponse(SnmpResponse response){
        return new SnmpRequest(address.toString(),
                response.getVariableList().get(0).getOid());
    }

}
