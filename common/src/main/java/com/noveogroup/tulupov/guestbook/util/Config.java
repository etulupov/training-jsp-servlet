package com.noveogroup.tulupov.guestbook.util;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final Config INSTANCE = new Config();
    private static final Logger LOGGER = Logger.getLogger(Config.class);
    private Properties properties;

    private Config() {
        properties = new Properties();
        InputStream is = null;
        try {
            is = getClass().getResourceAsStream("/config.properties");
            properties.load(is);
        } catch (IOException e) {
            LOGGER.error("Load config error", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }

        }

    }


    public static Config getInstance() {
        return INSTANCE;
    }

    public String getJdbcUrl() {
        return properties.getProperty("jdbc.url");
    }

    public String getJdbcUser() {
        return properties.getProperty("jdbc.username");
    }

    public String getJdbcPassword() {
        return properties.getProperty("jdbc.password");
    }

    public long getPageLimit() {
        return Long.parseLong(properties.getProperty("limit"));
    }
}
