package model;

import java.util.Date;

public class OidInfo {
    String oid;
    String value;
    String date;

    public OidInfo(String oid, String value, String date) {
        this.oid = oid;
        this.value = value;
        this.date = date;
    }

    public String getOid() {
        return oid;
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
