package ru.tecomgroup.mibbrowser.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.tecomgroup.mibbrowser.core.mib.MibManager;
import ru.tecomgroup.mibbrowser.spring.model.FileUpload;


import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("mibbrowser/mib")
public class MibController {

    @Autowired
    MibManager mibManager;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("mib");
        setModelAndViewMibMultiMap(modelAndView);
        return modelAndView;
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView saveMib(@ModelAttribute("uploadForm") FileUpload fileUpload) throws IOException {

        MultipartFile mibFile = fileUpload.getFile();
        mibManager.saveMib(mibFile);
        ModelAndView modelAndView = new ModelAndView("mib");
        setModelAndViewMibMultiMap(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "delete/{name}", method = RequestMethod.GET )
    public ModelAndView deleteMibFile(@PathVariable String name){
        mibManager.deleteMib(name);
        ModelAndView modelAndView = new ModelAndView("mib");
        setModelAndViewMibMultiMap(modelAndView);
        return modelAndView;
        }


    private void setModelAndViewMibMultiMap(ModelAndView modelAndView){
        List<String> mibList = mibManager.getMibList();
        modelAndView.addObject("mibList", mibList);
    }
}
