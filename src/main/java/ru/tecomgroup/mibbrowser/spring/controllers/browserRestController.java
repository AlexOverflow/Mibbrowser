package ru.tecomgroup.mibbrowser.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.tecomgroup.mibbrowser.core.configuration.SnmpConfigLoader;
import ru.tecomgroup.mibbrowser.core.model.MibBrowserRequest;
import ru.tecomgroup.mibbrowser.core.model.MibBrowserResponse;
import ru.tecomgroup.mibbrowser.core.model.SnmpCommand;
import ru.tecomgroup.mibbrowser.spring.service.DataBindingsService;

@Controller
@RequestMapping("/mibbrowser")
public class browserRestController {

    @Autowired
    @Qualifier( "DataBindingService")
    private DataBindingsService dataBindingsService;

    @Autowired
    @Qualifier( "configLoader")
    private SnmpConfigLoader configLoader;

    @RequestMapping(value = "/browser", method = RequestMethod.POST)
    @ResponseBody
    public MibBrowserResponse getOidInfo(@RequestBody MibBrowserRequest request){
       return dataBindingsService.doRequest(request, configLoader.getDefaultSnmpConfig());
    }


}
