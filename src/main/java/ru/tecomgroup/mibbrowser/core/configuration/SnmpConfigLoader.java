package ru.tecomgroup.mibbrowser.core.configuration;


import ru.tecomgroup.mibbrowser.core.model.SnmpConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class SnmpConfigLoader {

    public  SnmpConfiguration getDefaultSnmpConfig() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("snmp.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SnmpConfiguration config = new SnmpConfiguration();
        config.setRetries(properties.getProperty("retries"));
        config.setTimeOut(properties.getProperty("timeOut"));
        config.setPort(properties.getProperty("port"));

        return config;
    }
}
