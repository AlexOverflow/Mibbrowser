package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



public class SnmpQueryHandler {
    public static OidInfo snmpGet(String oid){
        return new OidInfo(oid, "32",
                new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
                        .format(new java.util.Date()));
    }

    public static OidInfo snmpGetNext(String oid){
        return new OidInfo(oid, "32",
                new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
                        .format(new java.util.Date()));
    }

    public static List<OidInfo> snmpWalk(String oid) {
        List<OidInfo> list = new ArrayList<OidInfo>();
        for(int i = 0; i < 6; i++){
            list.add(new OidInfo(oid, "2000", new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
                    .format(new java.util.Date())));
        }
        return list;
    }
}
