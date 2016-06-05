package ru.tecomgroup.mibbrowser.snmp.snmp4j;

import ru.tecomgroup.mibbrowser.snmp.model.SnmpRequest;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpResponse;

import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        Snmp4jObjectManager manager = new Snmp4jObjectManager();
        List<SnmpResponse> list = manager.snmpWalkByGet(new SnmpRequest("udp:192.168.1.1/161", "1.3.6.1.2.1.2.2.1.7.2"));
        for(SnmpResponse resp : list){
           System.out.println(resp);
        }

    }
}
