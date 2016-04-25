package ru.tecomgroup.mibbrowser.service.snmp;

import ru.tecomgroup.mibbrowser.model.OidDataBinding;

import java.util.ArrayList;
import java.util.List;


public class TestDataBindingsService implements SnmpDataBindingsService {
     private static TestDataBindingsService testService = new TestDataBindingsService();

     private TestDataBindingsService(){}

    public static TestDataBindingsService getTestService(){
        return  testService;
    }

    @Override
    public OidDataBinding snmpGet(String oid, String address) {
        return new OidDataBinding(oid, "getTest");
    }

    @Override
    public OidDataBinding snmpGetNext(String oid, String address) {
        return new OidDataBinding(oid, "getNextTest");
    }

    @Override
    public List<OidDataBinding> snmpWalk(String oid, String address) {
        List<OidDataBinding> list = new ArrayList<>();
        list.add(new OidDataBinding(oid, "walkTest"));
        list.add(new OidDataBinding(oid, "walkTest"));
        list.add(new OidDataBinding(oid, "walkTest"));
        return list;
    }
}
