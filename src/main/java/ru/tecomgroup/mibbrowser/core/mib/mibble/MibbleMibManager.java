package ru.tecomgroup.mibbrowser.core.mib.mibble;



import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibValue;
import net.percederberg.mibble.MibValueSymbol;
import net.percederberg.mibble.value.ObjectIdentifierValue;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import ru.tecomgroup.mibbrowser.core.mib.MibManager;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class MibbleMibManager implements MibManager {

    private static Logger log = Logger.getLogger(MibbleMibManager.class);

    private MibbleMibLoader mibLoader;

    private Mib[] mibs;


    private  String MIBS_DIRECTORY = getClass().getClassLoader().getResource("mibs").getFile();


    public MibbleMibManager() {
        try {
            mibLoader = new MibbleMibLoader();
            try {
                mibLoader.setMibDirectory(MIBS_DIRECTORY);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    public void deleteMibFile(String mibFileName) {
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
        List<MibValueSymbol> symbolList= new LinkedList<>();
        for (Mib mib : mibs) {
            MibValueSymbol symbol = mib.getSymbolByOid(oid);
            if (symbol != null) {
                symbolList.add(symbol);
            }
        }

        Collections.sort(symbolList, new Comparator<MibValueSymbol>() {
            @Override
            public int compare(MibValueSymbol o1, MibValueSymbol o2) {
                ObjectIdentifierValue value1 = (ObjectIdentifierValue) o1.getValue();
                ObjectIdentifierValue value2 = (ObjectIdentifierValue) o2.getValue();
                return (value2.toString()).compareTo(value1.toString());
            }
        });

        if(!(symbolList.isEmpty())){
           return symbolList.get(0).getName();
        }
        return null;
    }
}
