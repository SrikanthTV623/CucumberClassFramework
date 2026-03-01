package com.automation.utils;


public class TestContext {

    private static ThreadLocal<String> scenarioName = new ThreadLocal<>();

    public static void setScenarioName(String name) {
        scenarioName.set(name);
    }

    public static String getScenarioName() {
        return scenarioName.get();
    }

    public static void clear() {
        scenarioName.remove();
    }
}
