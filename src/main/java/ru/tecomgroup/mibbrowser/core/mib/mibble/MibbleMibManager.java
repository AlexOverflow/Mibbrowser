package ru.tecomgroup.mibbrowser.core.mib.mibble;


import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibValueSymbol;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.tecomgroup.mibbrowser.core.mib.MibManager;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class MibbleMibManager implements MibManager {

    private static Logger log = Logger.getLogger(MibbleMibManager.class);

    private MibbleMibLoader mibLoader;

    private Mib[] mibs;


    private  String MIBS_DIRECTORY = getClass().getClassLoader().getResource("mibs").getFile();


    public MibbleMibManager() throws IOException {
        try {
            mibLoader = new MibbleMibLoader();
            mibLoader.setMibDirectory(MIBS_DIRECTORY);
            mibs = mibLoader.loadMib();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void reLoadMib() {
        mibs = mibLoader.loadMib();
    }

    @Override
    public void deleteMib(String mibFileName) {
       for(Mib mib : mibs){
           if(mibFileName.equals(mib.getFile().getName()))
               mib.getFile().delete();
       }
        reLoadMib();
    }

    @Override
    public List<String> getMibList() {
        List<String> mibList = new LinkedList<>();
        for(Mib mib : mibs) {
            if(mib.getFile().exists())
                mibList.add(mib.getFile().getName());
        }
        return mibList;
    }

    @Override
    public void saveMib(MultipartFile file) {
        File mibFile = new File(MIBS_DIRECTORY + File.separator + file.getOriginalFilename());
        try {
            mibFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            file.transferTo(mibFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        reLoadMib();
    }



    @Override
    public String findMibValueByOId(String oid) {
        for (Mib mib : mibs) {
            MibValueSymbol symbol = mib.getSymbolByOid(oid);
            if (symbol != null) {
                return symbol.getName();
            }
        }
        return null;
    }
}
