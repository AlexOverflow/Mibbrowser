package ru.tecomgroup.mibbrowser.snmp.service;

import ru.tecomgroup.mibbrowser.snmp.mib.MibDataStorage;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpRequest;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpResponse;
import ru.tecomgroup.mibbrowser.snmp.util.MibObjectReader;

import java.io.File;


public class MibbrowserCoreServiceImpl implements MibbrowserCoreInterface {

    private MibDataStorage mibDataStorage;
    private File mibDir;

    public MibbrowserCoreServiceImpl(File mibDir){
        mibDataStorage = new MibDataStorage(mibDir);
        this.mibDir = mibDir;
    }

    @Override
    public SnmpResponse getObj(SnmpRequest request) {
        MibObjectReader reader = new MibObjectReader(request.getAddress(), mibDataStorage);
        SnmpResponse response =  reader.getObj(request);
        reader.close();
        return response;
    }

    @Override
    public SnmpResponse getNextObj(SnmpRequest request) {
        MibObjectReader reader = new MibObjectReader(request.getAddress(), mibDataStorage);
        SnmpResponse response =  reader.getNextObj(request);
        reader.close();
        return response;
    }

    @Override
    public SnmpResponse walk(SnmpRequest request) {
        MibObjectReader reader = new MibObjectReader(request.getAddress(), mibDataStorage);
        SnmpResponse response =  reader.walk(request);
        reader.close();
        return response;
    }

    public void reloadMibs(){
        mibDataStorage.addMibDirectory(mibDir);
    }
}
