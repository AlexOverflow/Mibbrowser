package ru.tecomgroup.mibbrowser.core.model;


import java.util.List;

public class SnmpResponse {
    List<SnmpVariable> snmpVariableList;

    public SnmpResponse(List<SnmpVariable> snmpVariableList) {
        this.snmpVariableList = snmpVariableList;
    }

    public List<SnmpVariable> getSnmpVariableList() {
        return snmpVariableList;
    }

    public void setSnmpVariableList(List<SnmpVariable> snmpVariableList) {
        this.snmpVariableList = snmpVariableList;
    }
}