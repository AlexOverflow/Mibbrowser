package server;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import java.io.IOException;



public class SnmpClient {
    private Snmp snmp;
    private TransportMapping transport;
    private Address address;

   public  void start(String address) {
        this.address = GenericAddress.parse(address);
        try {
            transport = new DefaultUdpTransportMapping();
        } catch (IOException e) {
            e.printStackTrace();
        }
        snmp = new Snmp(transport);
        try {
            snmp.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public ResponseEvent get(OID oid){
        PDU pdu = new PDU();
            pdu.add(new VariableBinding(oid));
        pdu.setType(PDU.GET);
       ResponseEvent event = null;
        try {
            event =  snmp.send(pdu, getTarget(), transport);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return event;
    }

   public ResponseEvent get(OID[] oids) {
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
    }

    public  ResponseEvent getNext(OID oid){
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(oid));
        pdu.setType(PDU.GETNEXT);
        ResponseEvent event = null;
        try {
            event =  snmp.send(pdu, getTarget(), transport);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String getAsString(OID oid){
        ResponseEvent event = get(oid);
       return event.getResponse().get(0).toString();
    }

    public String getNextAsString(OID oid){
        ResponseEvent event = getNext(oid);
        return oid + event.getResponse().get(0).toString();
    }

}
