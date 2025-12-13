package org.sabre.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnvPropertyLoader {
    private static final Logger logger = Logger.getLogger(EnvPropertyLoader.class.getName());
    private static final Properties properties = new Properties();
    private static boolean loaded = false;

    private EnvPropertyLoader() {}

    public static synchronized Properties getProperties() {
        if (!loaded) {
            loadProperties();
        }
        return properties;
    }

    public static String getProperty(String key) {
        return getProperties().getProperty(key);
    }

    private static void loadProperties() {
        String env = System.getProperty("Env");
        if (env == null || env.trim().isEmpty()) {
            env = System.getenv("Env");
        }
        if (env == null || env.trim().isEmpty()) {
            env = "DEV";
        }
        String propFileName = env.toUpperCase() + ".properties";
        Path configPath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "env", propFileName);
        try (InputStream input = Files.newInputStream(configPath)) {
            properties.load(input);
            loaded = true;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load properties file: " + propFileName, e);
        }
    }
}

