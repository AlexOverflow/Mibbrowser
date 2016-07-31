package ru.tecomgroup.mibbrowser.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.tecomgroup.mibbrowser.core.configuration.SnmpConfigLoader;
import ru.tecomgroup.mibbrowser.core.model.MibBrowserRequest;
import ru.tecomgroup.mibbrowser.core.model.MibBrowserResponse;
import ru.tecomgroup.mibbrowser.core.model.SnmpConfiguration;
import ru.tecomgroup.mibbrowser.spring.service.DataBindingsService;

import java.io.IOException;

@Controller
@SessionAttributes({"config", "request"})
@RequestMapping("mibbrowser/browser")
public class BrowserController {


    @Autowired
    @Qualifier( "DataBindingService")
    private DataBindingsService dataBindingsService;

    @Autowired
    @Qualifier( "configLoader")
    private SnmpConfigLoader configLoader;


    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("request", new MibBrowserRequest());
        return "browser";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView getOidInfoByHost(@ModelAttribute MibBrowserRequest request,
                                         @ModelAttribute SnmpConfiguration config, Model model){
        ModelAndView modelAndView = new ModelAndView("browser");
        model.addAttribute("request", request);
        MibBrowserResponse response = dataBindingsService.doRequest(request, config);
        modelAndView.addObject("response", response);
        return modelAndView;
    }

    @ModelAttribute("config")
    public SnmpConfiguration getSnmpDefaultConfig(){
        return configLoader.getDefaultSnmpConfig();
    }

}
