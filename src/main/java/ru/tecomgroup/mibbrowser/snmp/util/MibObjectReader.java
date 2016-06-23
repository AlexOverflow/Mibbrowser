package ru.tecomgroup.mibbrowser.snmp.util;
import org.snmp4j.event.ResponseEvent;
import ru.tecomgroup.mibbrowser.snmp.mib.MibDataStorage;
import ru.tecomgroup.mibbrowser.snmp.mib.MibDescription;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpResponse;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpVariable;

import java.io.File;
import java.util.List;

public class MibObjectReader extends OidObjectReader {

    MibDataStorage storage;

    public MibObjectReader(String address, MibDataStorage storage){
        super(address);
        this.storage = storage;
    }


    public MibObjectReader(String address, File mibDir){
        super(address);
        storage = new MibDataStorage();
        storage.addMibDirectory(mibDir);
    }

    @Override
    protected SnmpResponse getRespObjFromEvent(ResponseEvent event) {
       SnmpResponse response = super.getRespObjFromEvent(event);
        if(response != null){
          List<SnmpVariable> variableList = response.getVariableList();
            for(SnmpVariable v : variableList){
                MibDescription description = storage.getMibDescrByOid(v.getOid());
                if(description != null){
                    v.setMibName(description.getMibName());
                }

            }
            return response;
        }
        return null;
    }
}
