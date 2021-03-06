package ru.tecomgroup.mibbrowser.snmp.model;

public class SnmpRequest {
    private String address;
    private String oid;
    private SnmpConfiguration config;

    public SnmpRequest(String address, String oid) {
        this.address = address;
        this.oid = oid;
    }

    public SnmpRequest(){}

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

    public SnmpConfiguration getConfig() {
        return config;
    }

    public void setConfig(SnmpConfiguration config) {
        this.config = config;
    }
}
