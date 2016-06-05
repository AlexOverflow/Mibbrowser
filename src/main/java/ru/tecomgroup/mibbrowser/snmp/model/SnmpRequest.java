package ru.tecomgroup.mibbrowser.snmp.model;

public class SnmpRequest {
    private String address;
    private String oid;

    public SnmpRequest(String address, String oid) {
        this.address = address;
        this.oid = oid;
    }

    public String getAddress() {
        return address;
    }

    public String getOid() {
        return oid;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
