package ru.tecomgroup.mibbrowser.core.model;


public class SnmpCofiguration {

    private String retries;
    private String timeOut;
    private String port;

    public SnmpCofiguration(String retries, String timeOut, String port) {
        this.retries = retries;
        this.timeOut = timeOut;
        this.port = port;
    }

    public String getRetries() {
        return retries;
    }

    public void setRetries(String retries) {
        this.retries = retries;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
