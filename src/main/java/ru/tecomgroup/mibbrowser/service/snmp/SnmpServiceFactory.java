package ru.tecomgroup.mibbrowser.service.snmp;

/**
 * Created by alex on 25.04.16.
 */
public class SnmpServiceFactory {
    public static final String SNMP4J_DATA_BINDINGS_SERVICE = "snmp4j";
    public static final String TEST_SERVICE = "testService";
    public static SnmpDataTransferService getSnmpService(String service){
        SnmpDataTransferService snmpService = null;
        switch(service){
            case "snmp4j":
                snmpService =  Snmp4jDataTransferService.getService();
                break;
            case "testService":
                snmpService =  TestDataTransferService.getTestService();
        }
        return snmpService;
    }
}
