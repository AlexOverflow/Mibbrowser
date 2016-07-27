package ru.tecomgroup.mibbrowser.core.model;


public class MibBrowserRequest {

    private SnmpCommand command;
    private String hostAddress;
    private String oid;

    public SnmpCommand getCommand() {
        return command;
    }

    public void setCommand(SnmpCommand command) {
        this.command = command;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }


}
