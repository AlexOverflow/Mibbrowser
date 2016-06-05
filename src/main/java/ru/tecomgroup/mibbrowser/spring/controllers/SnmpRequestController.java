package ru.tecomgroup.mibbrowser.spring.controllers;

import org.springframework.web.bind.annotation.*;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpRequest;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import ru.tecomgroup.mibbrowser.spring.service.SnmpDataBindingsService;


import java.util.List;
@Controller
@RequestMapping(value = "/mibbrowser")
public class SnmpRequestController {

    private SnmpDataBindingsService dataBindingsService = SnmpDataBindingsService.getService();

    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
        public SnmpResponse snmpGet(@RequestBody SnmpRequest request){
            return dataBindingsService.snmpGet(request);
        }

    @RequestMapping(value = "/get/next", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
        public SnmpResponse snmpGetNext(@RequestBody SnmpRequest request){
            return dataBindingsService.snmpGetNext(request);
        }

    @RequestMapping(value = "/walk", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<SnmpResponse> snmpWalk(@RequestBody SnmpRequest request){
        return dataBindingsService.snmpWalk(request);
    }
}
