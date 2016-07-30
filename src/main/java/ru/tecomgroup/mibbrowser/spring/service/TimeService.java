package ru.tecomgroup.mibbrowser.spring.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeService {
    public String getTimeNowAsString() {
         SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
