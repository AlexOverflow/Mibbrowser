package ru.tecomgroup.mibbrowser.core.snmp.snmp4j;


import org.snmp4j.event.ResponseEvent;
import ru.tecomgroup.mibbrowser.core.model.SnmpResponse;
import ru.tecomgroup.mibbrowser.core.model.SnmpVariable;

import java.util.LinkedList;

public class Snmp4jToResponseConverter {
    public  SnmpResponse toSnmpResponseConvert(ResponseEvent event) {
        SnmpResponse snmpResponse = new SnmpResponse();
        //String
        //snmpResponse.setSnmpVariableList(new LinkedList<SnmpVariable>().add(event));
        return null;
    }
}
