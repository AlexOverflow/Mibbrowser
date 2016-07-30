package ru.tecomgroup.mibbrowser.core.model;


public enum  SnmpCommand {

    SNMP_GET("SNMP_GET"), SNMP_GET_NEXT("SNMP_GET_NEXT"), SNMP_WALK("SNM_WALK");

    String strValue;


    SnmpCommand(String s){
        strValue = s;
    }
}
