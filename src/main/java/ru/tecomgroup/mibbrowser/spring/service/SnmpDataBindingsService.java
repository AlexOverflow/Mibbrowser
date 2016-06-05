package ru.tecomgroup.mibbrowser.spring.service;

import ru.tecomgroup.mibbrowser.snmp.model.SnmpRequest;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpResponse;
import ru.tecomgroup.mibbrowser.snmp.snmp4j.Snmp4jObjectManager;

import java.util.List;

public class SnmpDataBindingsService {
    private static SnmpDataBindingsService bindingsService = new SnmpDataBindingsService();
    private SnmpDataBindingsService(){}

    public static SnmpDataBindingsService getService(){
        return bindingsService;
    }

    public SnmpResponse snmpGet(SnmpRequest request){
        return new Snmp4jObjectManager().snmpGet(request);
    }

    public SnmpResponse snmpGetNext(SnmpRequest request){
        return new Snmp4jObjectManager().snmpGetNext(request);
    }
    public List<SnmpResponse> snmpWalk(SnmpRequest request){
        return new Snmp4jObjectManager().snmpWalk(request);
    }

}
