package ru.tecomgroup.mibbrowser.core.mib;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface MibManager {

    void reLoadMib();
    void deleteMibFile(String mibFileName);
    List<String> getMibList();
    void saveMib(MultipartFile file);

   String findMibValueByOId(String oid);

}
