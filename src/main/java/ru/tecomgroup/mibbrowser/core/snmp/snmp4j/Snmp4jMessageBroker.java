package ru.tecomgroup.mibbrowser.core.snmp.snmp4j;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import ru.tecomgroup.mibbrowser.core.model.MibBrowserRequest;
import ru.tecomgroup.mibbrowser.core.model.SnmpCofiguration;
import ru.tecomgroup.mibbrowser.core.model.SnmpResponse;
import ru.tecomgroup.mibbrowser.core.snmp.SnmpMessageBroker;

import java.io.IOException;


public class Snmp4jMessageBroker implements SnmpMessageBroker {

    Snmp4jToResponseConverter responseConverter;
    RequestToSnmp4jConverter requestConverter;

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
        return reader.readOidValue(request, config);
    }


    private SnmpResponse makeSnmpWalkQuery(MibBrowserRequest request, SnmpCofiguration config) {
         return null;
    }



}
