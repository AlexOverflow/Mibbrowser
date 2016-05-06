package ru.tecomgroup.mibbrowser.service.snmp;
import ru.tecomgroup.mibbrowser.model.RequestSnmpObject;
import ru.tecomgroup.mibbrowser.model.ResponseSnmpObject;

import java.util.List;

public interface SnmpDataTransferService {
    public ResponseSnmpObject get(RequestSnmpObject requestSnmpObject);
    public ResponseSnmpObject getNext(RequestSnmpObject requestSnmpObject);
     List<ResponseSnmpObject> walk(RequestSnmpObject requestSnmpObject);
}
