package ru.tecomgroup.mibbrowser.core.snmp.snmp4j;


import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.VariableBinding;
import ru.tecomgroup.mibbrowser.core.model.SnmpResponse;
import ru.tecomgroup.mibbrowser.core.model.SnmpVariable;

import java.util.LinkedList;

public class Snmp4jToResponseConverter {
    public  SnmpResponse toSnmpResponseConvert(ResponseEvent event) {
        SnmpResponse snmpResponse = new SnmpResponse();
        VariableBinding variable = event.getResponse().getVariableBindings().get(0);
        return null;
    }
}
