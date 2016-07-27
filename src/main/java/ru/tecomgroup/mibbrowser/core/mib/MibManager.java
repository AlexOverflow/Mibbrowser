package ru.tecomgroup.mibbrowser.core.mib;


import org.springframework.web.multipart.MultipartFile;
import ru.tecomgroup.mibbrowser.core.model.MibVariable;

import java.util.List;

public interface MibManager {

    void reLoadMib();
    void unloadMib(String mibName);
    List<String> getMibList();
    void saveMib(MultipartFile file);

   String findMibValueByOId(String oid);

}
