package ru.tecomgroup.mibbrowser.core.snmp.snmp4j;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import ru.tecomgroup.mibbrowser.core.model.*;
import ru.tecomgroup.mibbrowser.core.snmp.SnmpMessageBroker;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Snmp4jMessageBroker implements SnmpMessageBroker {

    @Override
    public SnmpResponse sendQuery(MibBrowserRequest request, SnmpCofiguration config){
        SnmpResponse resp = null;
        switch (request.getCommand()){
            case SNMP_GET:
                resp = sendSingleQuery(request, config);
                break;
            case SNMP_GET_NEXT:
                resp = sendSingleQuery(request, config);
                break;
            case SNMP_WALK:
                resp = makeSnmpWalkQuery(request, config);
                break;
        }
        return resp;
    }

    private SnmpResponse sendSingleQuery(MibBrowserRequest request, SnmpCofiguration config) {
        OidValueReader reader = new OidValueReader();
        SnmpResponse resp =  reader.readOidValue(request, config);
        reader.close();
        return resp;
    }


    private SnmpResponse makeSnmpWalkQuery(MibBrowserRequest request, SnmpCofiguration config) {
        List<SnmpVariable> snmpVariableList = new LinkedList<>();
        OidValueReader reader = new OidValueReader();
        request.setCommand(SnmpCommand.SNMP_GET);
        snmpVariableList.add(reader.readOidValue(request, config).getSnmpVariableList().get(0));
        request.setCommand(SnmpCommand.SNMP_GET_NEXT);
        SnmpVariable variable;
        while((variable = reader.readOidValue(request, config).getSnmpVariableList().get(0)) != null){
            snmpVariableList.add(variable);
            request.setOid(variable.getOid());
        }
        reader.close();
        SnmpResponse resp = new SnmpResponse();
        resp.setSnmpVariableList(snmpVariableList);
        return resp;
    }



}
