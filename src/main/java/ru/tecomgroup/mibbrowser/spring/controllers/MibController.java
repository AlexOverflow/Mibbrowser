package ru.tecomgroup.mibbrowser.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.tecomgroup.mibbrowser.core.mib.MibManager;
import ru.tecomgroup.mibbrowser.spring.Model.FileUpload;


import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("browser/mib")
public class MibController {

    @Autowired
    MibManager mibManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(Model model){
        ModelAndView modelAndView = new ModelAndView("mib");
        List<String> mibList  = mibManager.getMibList();
        modelAndView.addObject("mibList", mibList);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView saveMib(@ModelAttribute("uploadForm") FileUpload fileUpload) throws IOException {

        MultipartFile mibFile = fileUpload.getFile();
        mibManager.saveMib(mibFile);
        ModelAndView modelAndView = new ModelAndView("mib");
        List<String> mibList  = mibManager.getMibList();
        modelAndView.addObject("mibList", mibList);
        return modelAndView;
    }
}
