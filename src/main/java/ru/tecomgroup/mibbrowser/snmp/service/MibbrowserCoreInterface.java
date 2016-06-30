package ru.tecomgroup.mibbrowser.snmp.service;

import ru.tecomgroup.mibbrowser.snmp.model.SnmpRequest;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpResponse;


public interface MibbrowserCoreInterface {

    SnmpResponse getObj(SnmpRequest request);
    SnmpResponse getNextObj(SnmpRequest request);
    SnmpResponse walk(SnmpRequest request);

}
