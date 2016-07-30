package ru.tecomgroup.mibbrowser.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.tecomgroup.mibbrowser.core.mib.MibManager;
import ru.tecomgroup.mibbrowser.core.model.*;
import ru.tecomgroup.mibbrowser.core.snmp.SnmpMessageBroker;
import java.util.LinkedList;
import java.util.List;

public class DataBindingsService {


    @Autowired
    @Qualifier( "snmp4jMenegerBroker")
    private SnmpMessageBroker snmp;

    @Autowired
    @Qualifier( "mibbleMibManger")
    private MibManager mibManager;

    @Autowired
    @Qualifier( "timeService")
    private TimeService timeService;


    public MibBrowserResponse doRequest(MibBrowserRequest request, SnmpConfiguration config) {
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

