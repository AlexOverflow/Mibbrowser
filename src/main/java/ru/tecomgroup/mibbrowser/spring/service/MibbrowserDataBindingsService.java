package ru.tecomgroup.mibbrowser.spring.service;

import ru.tecomgroup.mibbrowser.snmp.mib.MibDataStorage;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpRequest;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpResponse;
import ru.tecomgroup.mibbrowser.snmp.util.MibObjectReader;
import ru.tecomgroup.mibbrowser.spring.model.MibbrowserResponse;

import java.io.File;

public class MibbrowserDataBindingsService {
    private static MibbrowserDataBindingsService bindingsService = new MibbrowserDataBindingsService();

    private final MibDataStorage mibDataStorage = new MibDataStorage(new File("/resources/mibs"));

    private MibbrowserDataBindingsService(){}

    public static MibbrowserDataBindingsService getService(){
        return bindingsService;
    }

    public SnmpResponse snmpGet(SnmpRequest request){
        MibObjectReader reader = new MibObjectReader(request.getAddress(), mibDataStorage);
        MibbrowserResponse resp = new MibbrowserResponse(reader.getObj(request));
        reader.close();
        return  resp;
    }

    public SnmpResponse snmpGetNext(SnmpRequest request){
        MibObjectReader reader = new MibObjectReader(request.getAddress(), mibDataStorage);
        MibbrowserResponse resp = new MibbrowserResponse(reader.getNextObj(request));
        reader.close();
        return  resp;
    }
    public SnmpResponse snmpWalk(SnmpRequest request){
        MibObjectReader reader = new MibObjectReader(request.getAddress(), mibDataStorage);
        MibbrowserResponse resp = new MibbrowserResponse(reader.walk(request));
        reader.close();
        return resp;
    }


}
