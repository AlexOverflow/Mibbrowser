package ru.tecomgroup.mibbrowser.service.snmp;

import ru.tecomgroup.mibbrowser.model.RequestSnmpObject;
import ru.tecomgroup.mibbrowser.model.ResponseSnmpObject;

import java.util.ArrayList;
import java.util.List;


public class TestDataTransferService implements SnmpDataTransferService {
    @Override
    public ResponseSnmpObject get(RequestSnmpObject requestSnmpObject) {
        return null;
    }

    @Override
    public ResponseSnmpObject getNext(RequestSnmpObject requestSnmpObject) {
        return null;
    }

    @Override
    public List<ResponseSnmpObject> walk(RequestSnmpObject requestSnmpObject) {
        return null;
    }
    /* private static TestDataTransferService testService = new TestDataTransferService();

     private TestDataTransferService(){}

    public static TestDataTransferService getTestService(){
        return  testService;
    }

    @Override
    public ResponseSnmpObject get(RequestSnmpObject requestSnmpObject) {
        return new ResponseSnmpObject(oid, "getTest");
    }

    @Override
    public ResponseSnmpObject getNext(RequestSnmpObject requestSnmpObject) {
        return new ResponseSnmpObject(oid, "getNextTest");
    }

    @Override
    public List<ResponseSnmpObject> walk(RequestSnmpObject requestSnmpObject) {
        List<ResponseSnmpObject> list = new ArrayList<>();
        list.add(new ResponseSnmpObject(oid, "walkTest"));
        list.add(new ResponseSnmpObject(oid, "walkTest"));
        list.add(new ResponseSnmpObject(oid, "walkTest"));
        return list;
    }*/
}
