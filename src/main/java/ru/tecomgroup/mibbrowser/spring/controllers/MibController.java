package ru.tecomgroup.mibbrowser.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.tecomgroup.mibbrowser.core.mib.MibManager;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("mibbrowser/mib")
public class MibController {

    private final String COOKIE_MIB_STRING_SERARATOR = ",";

    @Autowired
    MibManager mibManager;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("mib");

        return modelAndView;
    }


   @RequestMapping(value = "select", method = RequestMethod.POST)
   public ModelAndView addSelectMibFileList(
           @ModelAttribute("mibList") List<String> mibList,
           HttpServletResponse response,
           @CookieValue(value = "selectdeMibList", defaultValue = "") String cookieMibListStr){

       ModelAndView modelAndView = new ModelAndView("mib");

       List<String> cookieMibList = convertStrToList(cookieMibListStr, COOKIE_MIB_STRING_SERARATOR);
       List<String> selectedMibList = mergeLists(cookieMibList, mibList);
       List<String> allMibList = mibManager.getMibList();
       List<String> availableMibList = getAvailableMibList(selectedMibList, allMibList);

       modelAndView.addObject("selectedMibList", selectedMibList);
       modelAndView.addObject("availableMibList", availableMibList);

       String selectedMibListStr = convertListToString(selectedMibList, COOKIE_MIB_STRING_SERARATOR);
       Cookie cookie = new Cookie("selectedMibList", selectedMibListStr);
       response.addCookie(cookie);
       return modelAndView;
   }


    @RequestMapping(value = "delete/{name}", method = RequestMethod.GET )
    public ModelAndView deleteMibFile(
            @PathVariable String name,
            @CookieValue(value = "selectedMibList") String cookieMibListStr){

        ModelAndView modelAndView = new ModelAndView("mib");

        List<String> cookieMibList = convertStrToList(cookieMibListStr, COOKIE_MIB_STRING_SERARATOR);
        cookieMibList.remove(name);
        List<String> mibList =  mibManager.getMibList();
        List<String> availableMibList = getAvailableMibList(cookieMibList, mibList);

        modelAndView.addObject("selectedMibList", cookieMibList);
        modelAndView.addObject("availableMibList", availableMibList);

        return modelAndView;
    }


    private String convertListToString(List<String> mibList, String separator) {
        StringBuilder builder = new StringBuilder();
        for(String mibName : mibList) {
            builder.append(mibName);
            builder.append(separator);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private List<String> getAvailableMibList(List<String> mibList, List<String> allMibList) {
        List<String> mibAvailableList = new LinkedList<>();
        for (String mibName : allMibList){
           if(!(mibList.contains(mibName))){
               mibAvailableList.add(mibName);
           }
        }
        return mibAvailableList;
    }


    private List<String> convertStrToList(String cookieMibListStr, String separator) {
        return Arrays.asList(cookieMibListStr.split(separator));
    }

    private List<String> mergeLists(List<String> cookieMibList, List<String> mibList) {
        List<String> mibMergeList = new LinkedList<>();

        for(String mibName : cookieMibList) mibMergeList.add(mibName);

        for (String mibName : mibList) mibMergeList.add(mibName);

        return mibMergeList;
    }
}
