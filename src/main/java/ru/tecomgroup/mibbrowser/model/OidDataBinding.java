package ru.tecomgroup.mibbrowser.model;

import java.util.Date;

public class OidDataBinding {
    String oid;
    String value;

    public OidDataBinding(String oid, String value) {
        this.oid = oid;
        this.value = value;
    }

    public String getOid() {
        return oid;
    }

    public String getValue() {
        return value;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
