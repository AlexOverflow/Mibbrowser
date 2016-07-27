package ru.tecomgroup.mibbrowser.core.snmp.snmp4j;


import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import ru.tecomgroup.mibbrowser.core.model.MibBrowserRequest;
import ru.tecomgroup.mibbrowser.core.model.SnmpCofiguration;

public class RequestToSnmp4jConverter {

    public CommunityTarget convertToTarget(MibBrowserRequest request, SnmpCofiguration config) {
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setRetries(config.getRetries());
        target.setTimeout(config.getTimeOut());
        target.setAddress(GenericAddress.parse(request.getHostAddress()));
        target.setVersion(SnmpConstants.version2c);
        return target;
    }


    public PDU convertToPDU(MibBrowserRequest request, SnmpCofiguration config) {
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(request.getOid())));
        switch (request.getCommand()){
            case SNMP_GET:
                pdu.setType(PDU.GET);
                break;
            case SNMP_GET_NEXT:
                pdu.setType(PDU.GETNEXT);
                break;
        }
        return pdu;
    }
}
