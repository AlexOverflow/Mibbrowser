package ru.tecomgroup.mibbrowser.core.model;


public class MibVariable {
    String oid;
    String oidValue;
    String mibName;

    public MibVariable(String oid, String oidValue, String mibName) {
        this.oid = oid;
        this.oidValue = oidValue;
        this.mibName = mibName;
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

    public String getMibName() {
        return mibName;
    }

    public void setMibName(String mibName) {
        this.mibName = mibName;
    }
}
