package ru.tecomgroup.mibbrowser.core.mib.mibble;

import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibValueSymbol;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import ru.tecomgroup.mibbrowser.core.mib.MibManager;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class MibbleMibManager implements MibManager {

    private static Logger log = Logger.getLogger(MibbleMibManager.class);

    private MibbleMibLoader mibLoader;

    private Mib[] mibs;
    private final File MIBS_PATH_NAME = new File(this.getClass().getClassLoader().getResource("mibs").getFile());


    public MibbleMibManager() {
        try {
            mibLoader = new MibbleMibLoader();
            mibLoader.setMibDirectory(MIBS_PATH_NAME);
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
    public void unloadMib(String mibName) {
        for (Mib mib : mibs) {
            if (mibName.equals(mib.getName()))
                mib.getFile().delete();
        }
        reLoadMib();
    }

    @Override
    public List<String> getMibList() {
        List<String> mibNameList = new LinkedList<>();
        for (Mib mib : mibs) {
            mibNameList.add(mib.getName());
        }
        return mibNameList;
    }

    @Override
    public void saveMib(MultipartFile file) {
        File mibFile = new File(MIBS_PATH_NAME + File.separator + file.getOriginalFilename());
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
