package ru.tecomgroup.mibbrowser.core.snmp.snmp4j;


import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import ru.tecomgroup.mibbrowser.core.model.MibBrowserRequest;
import ru.tecomgroup.mibbrowser.core.model.SnmpConfiguration;

public class RequestToSnmp4jConverter {

    public CommunityTarget convertToTarget(MibBrowserRequest request, SnmpConfiguration config) {
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setRetries(Integer.parseInt(config.getRetries()));
        target.setTimeout(Integer.parseInt(config.getTimeOut()));
        target.setAddress(GenericAddress.parse(formatHostAddress(request, config)));
        target.setVersion(SnmpConstants.version2c);
        return target;
    }


    public PDU convertToPDU(MibBrowserRequest request, SnmpConfiguration config) {
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(request.getOid())));
        return pdu;
    }

    private String formatHostAddress(MibBrowserRequest request, SnmpConfiguration config){
        return "udp:" + request.getHostAddress() + "/" + config.getPort();
    }
}
