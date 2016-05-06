package ru.tecomgroup.mibbrowser.model;

import org.snmp4j.PDU;
import org.snmp4j.event.ResponseEvent;

import java.util.Date;

public class ResponseSnmpObject {
    String oid;
    String value;

    public ResponseSnmpObject(String oid, String value) {
        this.oid = oid;
        this.value = value;
    }

    public ResponseSnmpObject(ResponseEvent event) {
        PDU responsePdu = event.getResponse();
        PDU requestPdu = event.getRequest();
       // oid = requestPdu.value = responsePdu.get(0).toString();
    }

    public String getOid() {
        return oid;
    }

    public String getValue() {
        return value;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
