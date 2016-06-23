package ru.tecomgroup.mibbrowser.snmp.util;

import net.percederberg.mibble.*;
import net.percederberg.mibble.value.ObjectIdentifierValue;
import ru.tecomgroup.mibbrowser.snmp.mib.MibDataStorage;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class MainTest {
    public static void main(String[] args) {
     /*
        SnmpRequest req = new SnmpRequest("udp:192.168.1.1/161", ".1.3.6.1.2.1.1.1.0");
        OidObjectReader reader = new OidObjectReader(req.getAddress());
        SnmpResponse resp;
         while((resp = reader.getNextObj(req)) != null){
             System.out.println(resp);
             req.setOid(resp.getVariableList().get(0).getOid());
         }

        reader.close();
    }
    */

        /*MibLoader loader = new MibLoader();
        File dirFile = new File("/home/alex/mib");
        List<Mib> mibs = new LinkedList<>();
        for(File file : dirFile.listFiles()){
            try {
                mibs.add(loadMib(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MibLoaderException e) {
                e.printStackTrace();
            }

        }*/


     /* HashMap map =  extractOids(mibs.get(0));

        System.out.println(map.keySet());
        System.out.println(map.values());*/
      /*  Collection<String> collection = map.values();
        for(String k : collection){
            System.out.println(k);
        }

        System.out.println(map);*/
       /* for(Mib mib : mibs){
            MibValueSymbol value = mib.getSymbolByOid("1.3.6.1.2.1.4.21.1.11");
            if(value != null) {
                System.out.println(value.getName());
                System.out.println(value.getMib().getName());
            }
        }*/








        /*for(File file : dirFile.listFiles()) {
            try {
                loader.load(file.getName());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MibLoaderException e) {
                e.printStackTrace();
            }
        }*/
      /*  MibValueSymbol symbol = null;

            Mib[] mibs = loader.getAllMibs();
           for (Mib mib : mibs ){
              symbol = mib.getRootSymbol();
                if(symbol != null){
                    System.out.println("Ураааа");
                    System.out.println(symbol.getName());
                }
            }
                // Iterator iter = mibs[0].getAllSymbols().iterator();




    }*/


/*
        HashMap map = new HashMap();
        Iterator iter = mib.getAllSymbols().iterator();
        MibSymbol  symbol;
        MibValue   value;

        while (iter.hasNext()) {
            symbol = (MibSymbol) iter.next();
            value = extractOid(symbol);
            if (value != null) {
                map.put(symbol.getName(), value);
            }
        }

*/
        /*SnmpRequest request = new SnmpRequest("udp:192.168.1.1/161", "1.3.6.1.2.1.1.1.0");
        MibObjectReader reader = new MibObjectReader("udp:192.168.1.1/161", new File("/home/alex/mib"));
        System.out.println("Build Finish");
        System.out.println(reader.walk(request));*/

        LocalTime timeObj = LocalTime.now();
        System.out.println(timeObj.format(DateTimeFormatter
                .ofPattern("hh:mm")));



       /* MibDataStorage mibDataStorage = new MibDataStorage();
        mibDataStorage.addMibDirectory(new File("/home/alex/mib"));
        System.out.println(mibDataStorage.getMibDescrByOid("1.3.6.1.2.1.2").getMibName());
*/

    }

    public static Mib loadMib(File file)
            throws FileNotFoundException, MibLoaderException {

        MibLoader  loader = new MibLoader();

        loader.addDir(file.getParentFile());
        try {
            return loader.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  HashMap extractOids(Mib mib) {
        HashMap    map = new HashMap();
        Iterator   iter = mib.getAllSymbols().iterator();
        MibSymbol  symbol;
        MibValue   value;

        while (iter.hasNext()) {
            symbol = (MibSymbol) iter.next();
            value = extractOid(symbol);
            if (value != null) {
                map.put(symbol.getName(), value.toObject());
            }
        }
        return map;
    }

    public static ObjectIdentifierValue extractOid(MibSymbol symbol) {
        MibValue  value;

        if (symbol instanceof MibValueSymbol) {
            value = ((MibValueSymbol) symbol).getValue();
            if (value instanceof ObjectIdentifierValue) {
                return (ObjectIdentifierValue) value;
            }
        }
        return null;
    }


}
