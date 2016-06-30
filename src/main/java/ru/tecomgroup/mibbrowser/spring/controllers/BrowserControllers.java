package ru.tecomgroup.mibbrowser.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpConfiguration;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpRequest;
import ru.tecomgroup.mibbrowser.snmp.util.SnmpConfigurationLoader;
import ru.tecomgroup.mibbrowser.spring.model.MibbrowserResponse;
import ru.tecomgroup.mibbrowser.spring.service.MibbrowserDataBindingsService;
import javax.servlet.http.HttpSession;
import java.io.File;


@Controller
@SessionAttributes("configuration")
@RequestMapping("/mibbrowser")
public class BrowserControllers {

    @Autowired
    MibbrowserDataBindingsService dataBindingsService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("browserRequest", new SnmpRequest());
        return "browser";
    }

    @RequestMapping(value = "browser/get", method = RequestMethod.POST)
    public ModelAndView describeOid(@ModelAttribute("SnmpRequest") SnmpRequest request,
                                    HttpSession session, Model m){

        //request.setConfig((SnmpConfiguration)session.getAttribute("configuration"));
        ModelAndView model = new ModelAndView("browser");
        MibbrowserResponse response = dataBindingsService.getObj(request);
        model.addObject("browserResponse", response);
        m.addAttribute("browserRequest", new SnmpRequest());
        return model;
    }

    @RequestMapping(value = "browser/walk", method = RequestMethod.POST)
    public ModelAndView describeAllByOid(@ModelAttribute("SnmpRequest") SnmpRequest request,
                                         HttpSession session){
        ModelAndView model = new ModelAndView("browser");
        request.setConfig((SnmpConfiguration)session.getAttribute("configuration"));
        MibbrowserResponse response = dataBindingsService.walk(request);
        model.addObject("browserResponse", response);
        return model;
    }

    @RequestMapping(value = "browser/next", method = RequestMethod.POST)
    public ModelAndView describeNextOid(@ModelAttribute("SnmpRequest") SnmpRequest request,
                                        HttpSession session){
        ModelAndView model = new ModelAndView("browser");
        request.setConfig((SnmpConfiguration)session.getAttribute("configuration"));
        MibbrowserResponse response = dataBindingsService.getNextObj(request);
        model.addObject("browserResponse", response);
        return model;
    }

    @RequestMapping(value = "browser/config")
    public String browserConfiguration(Model model){
        model.addAttribute("configuration", new SnmpConfiguration());
        return "config";
    }

   /* @ModelAttribute("configuration")
    public SnmpConfiguration getDefaultConfiguration(){
        return SnmpConfigurationLoader.configLoad(new File(getClass()
                .getClassLoader()
                .getResource("/configuration/browser.property").getFile()));
    }*/

}
