package ru.tecomgroup.mibbrowser.spring.service;


import ru.tecomgroup.mibbrowser.core.configuration.ConfigLoader;
import ru.tecomgroup.mibbrowser.core.mib.MibManager;
import ru.tecomgroup.mibbrowser.core.model.*;
import ru.tecomgroup.mibbrowser.core.snmp.SnmpMessageBroker;

import java.util.LinkedList;
import java.util.List;

public class DataBindingsService {
    ConfigLoader configLoader;
    SnmpMessageBroker snmp;
    MibManager mibManager;
    TimeService timeService;

    MibBrowserResponse doRequest(MibBrowserRequest request) {

        SnmpCofiguration config = configLoader.loadConfigForHost(request.getHostAddress());
        SnmpResponse snmpResp = snmp.sendQuery(request, config);
        List<MibVariable> mibVariableList = setMibForSnmpResponse(snmpResp);
        MibBrowserResponse browserResponse = new MibBrowserResponse();
        browserResponse.setMibVariables(mibVariableList);
        browserResponse.setTime(timeService.getTimeNowAsString());
        return browserResponse;
    }


     private List<MibVariable> setMibForSnmpResponse(SnmpResponse resp){
        List<MibVariable> mibVariableList = new LinkedList<MibVariable>();

            for(SnmpVariable variable : resp.getSnmpVariableList()){
                String mibName = mibManager.findMibValueByOId(variable.getOid());
                mibVariableList.add(new MibVariable(variable.getOid(), variable.getOidValue(), mibName ));
            }
         return mibVariableList;
     }

}

