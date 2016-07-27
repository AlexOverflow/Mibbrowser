package ru.tecomgroup.mibbrowser.core.snmp.snmp4j;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import ru.tecomgroup.mibbrowser.core.model.MibBrowserRequest;
import ru.tecomgroup.mibbrowser.core.model.SnmpCofiguration;
import ru.tecomgroup.mibbrowser.core.model.SnmpResponse;
import java.io.IOException;

public class OidValueReader{
    Snmp4jToResponseConverter responseConverter;
    RequestToSnmp4jConverter requestConverter;
    Snmp snmp;

    OidValueReader(){
        snmpUdpProtocolInitialize();
    }

    SnmpResponse readOidValue(MibBrowserRequest request, SnmpCofiguration config){
        PDU pdu = requestConverter.convertToPDU(request, config);
        CommunityTarget target = requestConverter.convertToTarget(request, config);
        ResponseEvent event = sendPDU(pdu, target);
        SnmpResponse snmpResponse = Snmp4jToResponseConverter.toSnmpResponseConvert(event);
        return snmpResponse;

    }

    private Snmp snmpUdpProtocolInitialize() {
        snmp = null;
        try {
            snmp = new Snmp(new DefaultUdpTransportMapping());
            snmp.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return snmp;
    }

    ResponseEvent sendPDU(PDU pdu, CommunityTarget target){
        ResponseEvent event = null;
        if(snmp != null) {
            try {
                event = snmp.send(pdu, target);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return event;
    }
}