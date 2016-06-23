package ru.tecomgroup.mibbrowser.snmp.model;

public class SnmpVariable {
    String oid;
    String value;
    String mibName;

    public SnmpVariable(String oid, String value, String mibName) {
        this.oid = oid;
        this.value = value;
        this.mibName = mibName;
    }

    public SnmpVariable(String oid, String value) {
        this.oid = oid;
        this.value = value;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMibName() {
        return mibName;
    }

    public void setMibName(String mibName) {
        this.mibName = mibName;
    }

    @Override
    public String toString() {
        return "SnmpVariable{" +
                "oid='" + oid + '\'' +
                ", value='" + value + '\'' +
                ", mibName='" + mibName + '\'' +
                '}';
    }
}
