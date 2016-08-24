package ru.tecomgroup.mibbrowser.core.model;


public enum  SnmpCommand {

    SNMP_GET("get"), SNMP_GET_NEXT("next"), SNMP_SET("set"), SNMP_WALK("walk");

    private String command;

    SnmpCommand(String command) {
        this.command = command;
    }

}
