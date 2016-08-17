package ru.tecomgroup.mibbrowser.core.snmp.snmp4j;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.smi.VariableBinding;
import ru.tecomgroup.mibbrowser.core.model.*;
import ru.tecomgroup.mibbrowser.core.snmp.SnmpMessageBroker;

import java.util.LinkedList;
import java.util.List;


public class Snmp4jMessageBroker implements SnmpMessageBroker {

    private RequestToSnmp4jConverter converter = new RequestToSnmp4jConverter();

    @Override
    public SnmpResponse sendQuery(MibBrowserRequest request, SnmpConfiguration config){
        SnmpResponse resp = new SnmpResponse();
        OidValueHandler handler = new OidValueHandler();
        PDU pdu = converter.convertToPDU(request, config);
        CommunityTarget target = converter.convertToTarget(request, config);
        VariableBinding variableBinding;
        try {
            switch (request.getCommand()) {
                case SNMP_GET:
                    variableBinding = handler.snmpGet(pdu, target);
                    resp = convertVariableBindingToResponse(variableBinding);
                    break;
                case SNMP_GET_NEXT:
                    variableBinding = handler.snmpGetNext(pdu, target);
                    resp = convertVariableBindingToResponse(variableBinding);
                    break;
                case SNMP_WALK:
                    resp = makeSnmpWalkQuery(pdu, target);
                    break;
                case SNMP_SET:
                   handler.snmpSet(pdu, target);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }finally {
            handler.close();
        }

        return resp;
    }

    private SnmpResponse convertVariableBindingToResponse(VariableBinding variable) {
        SnmpResponse response = new SnmpResponse();
        if(variable != null) {
            List<SnmpVariable> list = new LinkedList<SnmpVariable>();
            list.add(new SnmpVariable(variable.getOid().toString(), variable.toValueString()));
            response.setSnmpVariableList(list);
        }
        return response;
    }





    private SnmpResponse makeSnmpWalkQuery(PDU pdu, CommunityTarget target) {
        OidValueHandler reader = new OidValueHandler();
        SnmpResponse resp = new SnmpResponse();
        List<SnmpVariable> variableList = new LinkedList<>();

        VariableBinding var =  reader.snmpGet(pdu, target);
        if(var != null) variableList.add(new SnmpVariable(var.getOid().toString(), var.toValueString()));

        while((var = reader.snmpGetNext(pdu, target)) != null ){
            variableList.add(new SnmpVariable(var.getOid().toString(), var.toValueString()));
            pdu = new PDU();
            pdu.add(new VariableBinding(var.getOid()));
        }

        resp.setSnmpVariableList(variableList);
        reader.close();
        return resp;
    }

}
