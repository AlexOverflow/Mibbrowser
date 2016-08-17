package ru.tecomgroup.mibbrowser.core.snmp.snmp4j;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import java.io.IOException;

public class OidValueHandler {

    private Snmp snmp;

    public OidValueHandler(){
        snmp = snmpUdpProtocolInitialize();
    }


    VariableBinding snmpGet(PDU pdu, CommunityTarget target){
        ResponseEvent event = null;
        try {
            event = snmp.get(pdu, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(event != null) {
            String value = event.getResponse().get(0).getVariable().toString();
            if (!("endOfMibView".equals(value)) &&
                    !("noSuchInstance".equals(value)) &&
                    !("noSuchObject".equals(value)))
                return event.getResponse().get(0);
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
        if(event != null )
            if (!("endOfMibView".equals(event.getResponse().get(0).getVariable().toString())) &&
                    !("noSuchInstance".equals(event.getResponse().get(0).getVariable().toString())))
            return event.getResponse().getVariableBindings().get(0);

        return null;
    }



    private Snmp snmpUdpProtocolInitialize() {
        Snmp s = null;
        try {
            s = new Snmp(new DefaultUdpTransportMapping());
            s.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }



    public void close(){
        try {
            snmp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void snmpSet(PDU pdu, CommunityTarget target) {
        ResponseEvent event = null;
        try {
            event = snmp.set(pdu, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}