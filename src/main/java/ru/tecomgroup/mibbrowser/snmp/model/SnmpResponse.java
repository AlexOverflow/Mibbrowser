package ru.tecomgroup.mibbrowser.snmp.model;
import java.util.List;

public class SnmpResponse {
    private List<SnmpVariable> variableList;

    public SnmpResponse(List<SnmpVariable> variableList) {
        this.variableList = variableList;
    }

    public void setVariableList(List<SnmpVariable> variableList) {
        this.variableList = variableList;
    }

    public List<SnmpVariable> getVariableList() {
        return variableList;
    }

    @Override
    public String toString() {
       StringBuilder builder = new StringBuilder();
        for(SnmpVariable v : variableList){
            builder.append(v.toString() + "\n");
        }
        return builder.toString();
    }
}
