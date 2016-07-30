package ru.tecomgroup.mibbrowser.core.snmp;
import ru.tecomgroup.mibbrowser.core.model.MibBrowserRequest;
import ru.tecomgroup.mibbrowser.core.model.SnmpConfiguration;
import ru.tecomgroup.mibbrowser.core.model.SnmpResponse;

public interface SnmpMessageBroker {

    SnmpResponse sendQuery(MibBrowserRequest request, SnmpConfiguration config);

}
