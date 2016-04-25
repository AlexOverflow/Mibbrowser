package ru.tecomgroup.mibbrowser.service.snmp;

import org.snmp4j.*;
import org.snmp4j.CommunityTarget;import org.snmp4j.PDU;import org.snmp4j.Snmp;import org.snmp4j.Target;import org.snmp4j.TransportMapping;import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.smi.Address;import org.snmp4j.smi.GenericAddress;import org.snmp4j.smi.OID;import org.snmp4j.smi.OctetString;import org.snmp4j.smi.VariableBinding;import org.snmp4j.transport.DefaultUdpTransportMapping;
import ru.tecomgroup.mibbrowser.service.snmp.configuration.Snmp4jConfiguration;

import java.io.IOException;import java.lang.String;



public class Snmp4jUtilities {
    private Snmp snmp;
    private TransportMapping transport;
    private Address address;

    private static Snmp connect(Snmp4jConfiguration config){
        Address address = config.getAddress();
        TransportMapping transportMapping = config.getTransportMapping();
        Snmp snmp = new Snmp(transportMapping);
        try {
            snmp.listen();
        } catch (IOException e){
            e.printStackTrace();
        }
        return snmp;
    }



   public static ResponseEvent get(OID oid, Snmp4jConfiguration config){
        PDU pdu = new PDU();
            pdu.add(new VariableBinding(oid));
        pdu.setType(PDU.GET);
       ResponseEvent event = getSnmpResponseEvent(pdu, config);
        return event;
    }

  /* public static ResponseEvent get(OID[] oids) {
       PDU pdu = new PDU();
       ResponseEvent event = null;
        for(OID oid : oids){
          pdu.add(new VariableBinding(oid));
            try {
                event =  snmp.send(pdu, getTarget());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       return event;
    }*/

    public static ResponseEvent getNext(OID oid, Snmp4jConfiguration config){
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(oid));
        pdu.setType(PDU.GETNEXT);
        ResponseEvent event = getSnmpResponseEvent(pdu, config);
        return event;
    }



    private Target getTarget(){
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setAddress(address);
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);
        return target;
    }

    private static ResponseEvent getSnmpResponseEvent(PDU pdu, Snmp4jConfiguration config) {
        ResponseEvent event = null;
        try {
            Snmp snmp = connect(config);
             event = snmp.send(pdu, config.getTarget());
            snmp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  event;
    }

}
