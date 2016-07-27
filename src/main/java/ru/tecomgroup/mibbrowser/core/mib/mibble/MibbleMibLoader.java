package ru.tecomgroup.mibbrowser.core.mib.mibble;


import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibLoader;
import net.percederberg.mibble.MibLoaderException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class MibbleMibLoader {

    private static Logger log = Logger.getLogger(MibbleMibLoader.class);

    public MibbleMibLoader(){}

    private File mibDirectory;


    void setMibDirectory(File fileDir){
      this.mibDirectory = fileDir;
    }

    Mib[] loadMib(){
        MibLoader loader = new MibLoader();
        if(mibDirectory != null) {
            loader.addDir(mibDirectory);
            for (File mibFile : mibDirectory.listFiles()) {
                try {
                    loader.load(mibFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return loader.getAllMibs();
        }
            return new Mib[0];
    }

    Mib[] loadMib( File fileDir){
        MibLoader loader = new MibLoader();
        loader.addDir(fileDir);
        for(File mibFile : fileDir.listFiles()){
            try {
                loader.load(mibFile);
            } catch (IOException | MibLoaderException e) {
                e.printStackTrace();
            }
        }
        return loader.getAllMibs();
    }

}
