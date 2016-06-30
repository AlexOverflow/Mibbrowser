package ru.tecomgroup.mibbrowser.snmp.mib;
import net.percederberg.mibble.*;

import java.io.File;
import java.io.IOException;

public class MibDataStorage {
    private Mib[] mibs;

    public MibDataStorage(File dirFile){
        addMibDirectory(dirFile);
    }

    public MibDataStorage(){}


    public MibDescription getMibDescrByOid(String oid){
        MibDescription description = null;
        for(Mib mib : mibs){
          MibValueSymbol symbol =   mib.getSymbolByOid(oid);
            if(symbol != null){
                return new MibDescription(symbol.getName());
            }
        }
        return  description;
    }

    public void addMibDirectory(File dirFile){
        MibLoader loader = new MibLoader();
        loader.addDir(dirFile);
       for(File mibFile : dirFile.listFiles()){
           try {
               loader.load(mibFile);
           } catch (IOException | MibLoaderException e) {
               e.printStackTrace();
           }
       }
       mibs = loader.getAllMibs();
    }
}
