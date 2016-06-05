package ru.tecomgroup.mibbrowser.snmp.snmp4j;


import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpRequest;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;


public class Snmp4jObjectManager {
    private Snmp snmp;

    public Snmp4jObjectManager(Snmp snmp){
        this.snmp = snmp;
        try {
            snmp.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Snmp4jObjectManager(){
        try {
            this.snmp = new Snmp(new DefaultUdpTransportMapping());
            snmp.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    Target getDefaultTarget(String address){
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setRetries(2);
        target.setTimeout(1500);
        target.setAddress(GenericAddress.parse(address));
        target.setVersion(SnmpConstants.version2c);
        return target;
    }

    public SnmpResponse snmpGet(SnmpRequest request){
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(request.getOid())));
        ResponseEvent event = null;
        try {
             event = snmp.get(pdu, getDefaultTarget(request.getAddress()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SnmpResponse response = responseEventToObj(event);
        response.setTime(LocalTime.now().toString());
        return response;
    }

    public SnmpResponse snmpGetNext(SnmpRequest request){
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(request.getOid())));
        ResponseEvent event = null;
        try {
            event = snmp.getNext(pdu, getDefaultTarget(request.getAddress()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SnmpResponse response = responseEventToObj(event);
        response.setTime(LocalTime.now().toString());
        return response;
    }

    public List<SnmpResponse> snmpWalk(SnmpRequest request){
        TreeUtils tree = new TreeUtils(snmp, new DefaultPDUFactory());
        List<TreeEvent> events = tree.getSubtree(getDefaultTarget
                (request.getAddress()), new OID(request.getOid()));
        List<SnmpResponse> responses = new LinkedList<SnmpResponse>();
        for(TreeEvent event : events){
           VariableBinding[] variableBindings = event.getVariableBindings();
            for(VariableBinding variableBinding : variableBindings){
                responses.add(new SnmpResponse(
                        variableBinding.getOid().toString(),
                        variableBinding.toValueString(),
                        LocalTime.now().toString()));
            }
        }
        return responses;
    }

    public List<SnmpResponse> snmpWalkByGet(SnmpRequest request){
         List<SnmpResponse> responses = new LinkedList<>();
         SnmpResponse resp = null;
        while((resp = snmpGetNext(request)) != null){
            responses.add(resp);
            request.setOid(resp.getOid());
        }
        return responses;
    }

     private SnmpResponse responseEventToObj(ResponseEvent event){
        SnmpResponse response = new SnmpResponse();
        response.setOid(event.getResponse().get(0).getOid().toString());
        response.setOidValue(event.getResponse().get(0).toValueString());
        return response;
    }
}
