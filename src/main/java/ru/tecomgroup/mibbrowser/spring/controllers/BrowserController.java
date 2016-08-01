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
import ru.tecomgroup.mibbrowser.core.model.SnmpCommand;
import ru.tecomgroup.mibbrowser.core.model.SnmpConfiguration;
import ru.tecomgroup.mibbrowser.spring.service.DataBindingsService;

import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes({"config", "request"})
@RequestMapping({"mibbrowser/browser", "/"})
public class BrowserController {


    @Autowired
    @Qualifier( "DataBindingService")
    private DataBindingsService dataBindingsService;

    @Autowired
    @Qualifier( "configLoader")
    private SnmpConfigLoader configLoader;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(Model model){
        ModelAndView modelAndView = new ModelAndView("browser");
        modelAndView.addObject("commandValues", SnmpCommand.values());
        model.addAttribute("request", new MibBrowserRequest());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView getOidInfoByHost(@ModelAttribute MibBrowserRequest request,
                                         Model model, HttpSession session){
        ModelAndView modelAndView = new ModelAndView("browser");
        SnmpConfiguration configuration = (SnmpConfiguration) session.getAttribute("config");
        model.addAttribute("request", request);
        MibBrowserResponse response = dataBindingsService.doRequest(request, configuration);
        modelAndView.addObject("response", response);
        return modelAndView;
    }

    @RequestMapping(value = "config", method = RequestMethod.GET)
        public String getConfigPage(Model model, @ModelAttribute SnmpConfiguration config){
        model.addAttribute("config", config);
        return "config";
    }

    @RequestMapping(value = "config", method = RequestMethod.POST)
    public String getConfigPage(@ModelAttribute SnmpConfiguration config, Model model){
        model.addAttribute("config", config);
        return "config";
    }


    @ModelAttribute("config")
    public SnmpConfiguration getSnmpDefaultConfig(){
        return configLoader.getDefaultSnmpConfig();
    }

}
