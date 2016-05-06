package ru.tecomgroup.mibbrowser.controllers;

import org.springframework.web.bind.annotation.*;
import ru.tecomgroup.mibbrowser.model.ResponseSnmpObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import ru.tecomgroup.mibbrowser.service.snmp.SnmpDataTransferService;
import ru.tecomgroup.mibbrowser.service.snmp.SnmpServiceFactory;


import java.util.List;
@Controller
@RequestMapping(value = "/mibbrowser")
public class SnmpQueryController {

    private SnmpDataTransferService dataBindingsService =
            SnmpServiceFactory.getSnmpService(SnmpServiceFactory.TEST_SERVICE);

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
        public ResponseSnmpObject snmpGet
            (@RequestParam(value = "oid") String oid, @RequestParam(value = "address") String address){
            return dataBindingsService.get(oid);
        }

    @RequestMapping(value = "/next", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
        public ResponseSnmpObject snmpNext
            (@RequestParam(value = "oid") String oid, @RequestParam(value = "address") String address ){
            return dataBindingsService.getNext(oid);
        }

    @RequestMapping(value = "/walk", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ResponseSnmpObject> snmpWalk
            (@RequestParam(value = "oid") String oid, @RequestParam(value = "address") String address){
        return dataBindingsService.walk(oid);
    }
}
