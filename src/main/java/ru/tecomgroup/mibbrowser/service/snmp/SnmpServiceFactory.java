package ru.tecomgroup.mibbrowser.service.snmp;

/**
 * Created by alex on 25.04.16.
 */
public class SnmpServiceFactory {
    public static SnmpDataBindingsService getSnmpService(String service){
        SnmpDataBindingsService snmpService = null;
        switch(service){
            case "snmp4j":
                snmpService =  Snmp4jDataBindingsService.getService();
                break;
            case "testService":
                snmpService =  TestDataBindingsService.getTestService();
        }
        return snmpService;
    }
}
