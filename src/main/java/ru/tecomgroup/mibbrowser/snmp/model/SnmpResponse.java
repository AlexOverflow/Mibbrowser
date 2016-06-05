package ru.tecomgroup.mibbrowser.snmp.model;

public class SnmpResponse {
    private String oid;
    private String oidValue;
    private String time;

     public SnmpResponse(String oid, String oidValue, String time) {
        this.oid = oid;
        this.oidValue = oidValue;
        this.time = time;
    }
    public SnmpResponse(){

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOidValue() {
        return oidValue;
    }

    public void setOidValue(String oidValue) {
        this.oidValue = oidValue;
    }

    @Override
    public String toString() {
        return "SnmpResponse{" +
                "oid='" + oid + '\'' +
                ", oidValue='" + oidValue + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
