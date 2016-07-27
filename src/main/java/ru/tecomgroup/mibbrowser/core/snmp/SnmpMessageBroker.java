package ru.tecomgroup.mibbrowser.core.snmp;
import ru.tecomgroup.mibbrowser.core.model.MibBrowserRequest;
import ru.tecomgroup.mibbrowser.core.model.SnmpCofiguration;
import ru.tecomgroup.mibbrowser.core.model.SnmpResponse;

public interface SnmpMessageBroker {

    SnmpResponse sendQuery(MibBrowserRequest request, SnmpCofiguration config);

}
