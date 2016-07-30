package ru.tecomgroup.mibbrowser.core.snmp.snmp4j;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import java.io.IOException;

public class OidValueReader{

    private Snmp snmp;

    public OidValueReader(){
        snmpUdpProtocolInitialize();
    }


    VariableBinding snmpGet(PDU pdu, CommunityTarget target){
        ResponseEvent event = null;
        try {
            event = snmp.get(pdu, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(event != null) {
            if ("endOfMibView".equals(event.getResponse().getVariableBindings().get(0).toValueString()))
                return event.getResponse().getVariableBindings().get(0);
        }

        return null;

    }

    VariableBinding snmpGetNext(PDU pdu, CommunityTarget target){
        ResponseEvent event = null;
        try {
            event = snmp.getNext(pdu, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(event != null) return event.getResponse().getVariableBindings().get(0);

        return null;
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



    public void close(){
        try {
            snmp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}