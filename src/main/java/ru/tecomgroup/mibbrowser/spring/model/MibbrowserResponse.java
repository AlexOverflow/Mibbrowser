package ru.tecomgroup.mibbrowser.spring.model;

import ru.tecomgroup.mibbrowser.snmp.model.SnmpResponse;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpVariable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;


public class MibbrowserResponse extends SnmpResponse {

    String time;

    public MibbrowserResponse(List<SnmpVariable> variableList) {
        super(variableList);
        setCurrentTime();
    }

    public MibbrowserResponse(SnmpResponse response){
        super(response.getVariableList());
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
}
