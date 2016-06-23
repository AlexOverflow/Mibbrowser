package ru.tecomgroup.mibbrowser.snmp.mib;


public class MibDescription {
    private String mibName;

    public MibDescription(String mibName) {
        this.mibName = mibName;
    }

    public String getMibName() {
        return mibName;
    }

    public void setMibName(String mibName) {
        this.mibName = mibName;
    }
}
