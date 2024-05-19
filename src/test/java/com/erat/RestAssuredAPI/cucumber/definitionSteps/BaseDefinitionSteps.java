package com.erat.RestAssuredAPI.cucumber.definitionSteps;

import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class BaseDefinitionSteps {
    protected static Properties properties = new Properties();

    static {
        LoggerFactory.getLogger(BaseDefinitionSteps.class);
        try {
            properties.load(BaseDefinitionSteps.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties file due to an I/O error: ", e);
        }
    }
}