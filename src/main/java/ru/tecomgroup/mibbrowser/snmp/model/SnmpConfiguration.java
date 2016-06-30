package ru.tecomgroup.mibbrowser.snmp.model;


public class SnmpConfiguration {
    private String version;
    private int timeOut;
    private int retries;

    public SnmpConfiguration(String version, int timeOut, int retries) {
        this.version = version;
        this.timeOut = timeOut;
        this.retries = retries;
    }

    public SnmpConfiguration(){}

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }
}
