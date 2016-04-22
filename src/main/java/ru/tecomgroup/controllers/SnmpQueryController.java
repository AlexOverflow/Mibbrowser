package ru.tecomgroup.controllers;

import model.OidInfo;
import model.SnmpQueryHandler;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping(value = "/mibbrowser")
public class SnmpQueryController {
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
        public OidInfo snmpGet(@PathVariable(value = "oid") String oid ){
            return SnmpQueryHandler.snmpGet(oid);
        }

    @RequestMapping(value = "/next/{oid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
        public OidInfo snmpNext(@PathVariable(value = "oid") String oid ){
            return SnmpQueryHandler.snmpGetNext(oid);
        }

    @RequestMapping(value = "/walk/{oid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OidInfo> snmpWalk (@PathVariable(value = "oid") String oid ){
        return SnmpQueryHandler.snmpWalk(oid);
    }
}
