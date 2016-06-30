package ru.tecomgroup.mibbrowser.spring.service;

import ru.tecomgroup.mibbrowser.snmp.mib.MibDataStorage;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpRequest;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpResponse;
import ru.tecomgroup.mibbrowser.snmp.service.MibbrowserCoreServiceImpl;
import ru.tecomgroup.mibbrowser.snmp.util.MibObjectReader;
import ru.tecomgroup.mibbrowser.spring.model.MibbrowserResponse;

import java.io.File;

public class MibbrowserDataBindingsService {
   MibbrowserCoreServiceImpl mibbrowserCoreService;


    public MibbrowserDataBindingsService(){
        mibbrowserCoreService = new MibbrowserCoreServiceImpl(
                new File(getClass().getClassLoader().getResource("/mibs").getFile()));
    }

    public MibbrowserResponse getObj(SnmpRequest request){
        SnmpResponse response =  mibbrowserCoreService.getObj(request);
        return  new MibbrowserResponse(response);
    }
    public MibbrowserResponse getNextObj(SnmpRequest request){
        SnmpResponse response =  mibbrowserCoreService.getNextObj(request);
        return  new MibbrowserResponse(response);
    }
    public MibbrowserResponse walk(SnmpRequest request){
        SnmpResponse response =  mibbrowserCoreService.walk(request);
        return  new MibbrowserResponse(response);
    }
}
