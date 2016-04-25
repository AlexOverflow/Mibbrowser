package ru.tecomgroup.mibbrowser.controllers;

import org.springframework.web.bind.annotation.*;
import ru.tecomgroup.mibbrowser.model.OidDataBinding;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import ru.tecomgroup.mibbrowser.service.snmp.SnmpDataBindingsService;
import ru.tecomgroup.mibbrowser.service.snmp.SnmpServiceFactory;


import java.util.List;
@Controller
@RequestMapping(value = "/mibbrowser")
public class SnmpQueryController {

    private SnmpDataBindingsService dataBindingsService = SnmpServiceFactory.getSnmpService("testService");

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
        public OidDataBinding snmpGet
            (@RequestParam(value = "oid") String oid, @RequestParam(value = "address") String address){
            return dataBindingsService.snmpGet(oid, address);
        }

    @RequestMapping(value = "/next", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
        public OidDataBinding snmpNext
            (@RequestParam(value = "oid") String oid, @RequestParam(value = "address") String address ){
            return dataBindingsService.snmpGetNext(oid, address);
        }

    @RequestMapping(value = "/walk", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OidDataBinding> snmpWalk
            (@RequestParam(value = "oid") String oid, @RequestParam(value = "address") String address){
        return dataBindingsService.snmpWalk(oid, address);
    }
}
