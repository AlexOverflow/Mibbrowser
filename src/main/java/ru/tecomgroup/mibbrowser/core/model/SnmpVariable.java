package ru.tecomgroup.mibbrowser.core.model;


public class SnmpVariable {
    String oid;
    String oidValue;

    public SnmpVariable(String oid, String oidValue) {
        this.oid = oid;
        this.oidValue = oidValue;
    }



    public SnmpVariable(){}

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
}
