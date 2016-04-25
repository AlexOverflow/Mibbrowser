package ru.tecomgroup.mibbrowser.service.snmp;
import ru.tecomgroup.mibbrowser.model.OidDataBinding;

import java.util.List;

public interface SnmpDataBindingsService {
    public OidDataBinding snmpGet(String oid, String address);
    public OidDataBinding snmpGetNext(String oid, String address);
     List<OidDataBinding> snmpWalk(String oid, String address);
}
