package ru.tecomgroup.mibbrowser.core.snmp.snmp4j;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.smi.VariableBinding;
import ru.tecomgroup.mibbrowser.core.model.*;
import ru.tecomgroup.mibbrowser.core.snmp.SnmpMessageBroker;

import java.util.LinkedList;
import java.util.List;


public class Snmp4jMessageBroker implements SnmpMessageBroker {

    RequestToSnmp4jConverter converter = new RequestToSnmp4jConverter();

    @Override
    public SnmpResponse sendQuery(MibBrowserRequest request, SnmpConfiguration config){
        SnmpResponse resp = new SnmpResponse();
        OidValueReader reader = new OidValueReader();
        PDU pdu = converter.convertToPDU(request, config);
        CommunityTarget target = converter.convertToTarget(request, config);
        VariableBinding variableBinding;
        switch (request.getCommand()){
            case SNMP_GET:
                variableBinding = reader.snmpGet(pdu, target);
                resp = convertVariableBindingToResponse(variableBinding);
                break;
            case SNMP_GET_NEXT:
                variableBinding = reader.snmpGet(pdu, target);
                resp = convertVariableBindingToResponse(variableBinding);
                break;
            case SNMP_WALK:
                resp = makeSnmpWalkQuery(pdu, target);
                break;
        }
        reader.close();
        return resp;
    }

    SnmpResponse convertVariableBindingToResponse(VariableBinding variable) {
        SnmpResponse response = new SnmpResponse();
        if(variable != null) {
            List<SnmpVariable> list = new LinkedList<SnmpVariable>();
            list.add(new SnmpVariable(variable.getOid().toString(), variable.toValueString()));
            response.setSnmpVariableList(list);
        }
        return response;
    }





    public SnmpResponse makeSnmpWalkQuery(PDU pdu, CommunityTarget target) {
        OidValueReader reader = new OidValueReader();
        SnmpResponse resp = new SnmpResponse();
        List<SnmpVariable> variableList = new LinkedList<>();

        VariableBinding var =  reader.snmpGet(pdu, target);
        if(var != null) variableList.add(new SnmpVariable(var.getOid().toString(), var.toValueString()));

        while((var = reader.snmpGetNext(pdu, target)) != null ){
            variableList.add(new SnmpVariable(var.getOid().toString(), var.toValueString()));
            pdu.add(new VariableBinding(var.getOid()));
        }

        resp.setSnmpVariableList(variableList);
        reader.close();
        return resp;
    }

}
