package ru.tecomgroup.mibbrowser.snmp.util;
import ru.tecomgroup.mibbrowser.snmp.model.SnmpConfiguration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SnmpConfigurationLoader {

    public static SnmpConfiguration configLoad(File configFile){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(configFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SnmpConfiguration configuration = new SnmpConfiguration();
        configuration.setRetries(Integer.parseInt(properties.getProperty("snmp.retries")));
        configuration.setTimeOut(Integer.parseInt(properties.getProperty("snmp.time.out")));
        configuration.setVersion(properties.getProperty("snmp.version"));

        return configuration;
    }

}
