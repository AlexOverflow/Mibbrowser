package ru.tecomgroup.mibbrowser.spring.model;

import ru.tecomgroup.mibbrowser.snmp.model.SnmpResponse;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpVariable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;


public class MibbrowserResponse {

    private SnmpResponse response;
    private String time;

    public MibbrowserResponse(SnmpResponse response) {
        this.response = response;
        setCurrentTime();
    }

    private void setCurrentTime(){
        LocalTime timeObj = LocalTime.now();
        time = timeObj.format( DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT));
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public SnmpResponse getResponse() {
        return response;
    }

    public void setResponse(SnmpResponse response) {
        this.response = response;
    }
}
