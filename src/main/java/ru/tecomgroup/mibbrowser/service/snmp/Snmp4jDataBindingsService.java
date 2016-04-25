package ru.tecomgroup.mibbrowser.service.snmp;


import ru.tecomgroup.mibbrowser.model.OidDataBinding;
import ru.tecomgroup.mibbrowser.service.snmp.configuration.Snmp4jConfigurationService;

import java.util.List;

public class Snmp4jDataBindingsService implements SnmpDataBindingsService{
    Snmp4jConfigurationService configurationService;
    private static Snmp4jDataBindingsService snmp4jService = new Snmp4jDataBindingsService();

    private Snmp4jDataBindingsService(){}

    public static Snmp4jDataBindingsService getService(){
        return snmp4jService;
    }

    @Override
    public OidDataBinding snmpGet(String oid, String address) {
        return null;
    }

    @Override
    public OidDataBinding snmpGetNext(String oid, String address) {
        return null;
    }

    @Override
    public List<OidDataBinding> snmpWalk(String oid, String address) {
        return null;
    }
}
