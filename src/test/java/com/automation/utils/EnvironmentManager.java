package com.automation.utils;

public class EnvironmentManager {

    //mvn clean test -Denv=uat
    public static String getEnvironment() {

        String env = System.getProperty("env");

        if (env == null || env.isEmpty()) {
            env = "qa";   // fallback default
        }

        System.out.println("Running on Environment: " + env.toUpperCase());
        return env;
    }
}
