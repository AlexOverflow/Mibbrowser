package ru.tecomgroup.mibbrowser.core.model;


import java.util.List;

public class MibBrowserResponse {
    List<MibVariable> mibVariables;
    String time;

    public List<MibVariable> getMibVariables() {
        return mibVariables;
    }

    public void setMibVariables(List<MibVariable> mibVariables) {
        this.mibVariables = mibVariables;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
