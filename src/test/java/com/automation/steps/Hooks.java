package com.automation.steps;

import com.automation.utils.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;
import java.util.Map;

public class Hooks {

    public static Map<String,String> data;
    public ExcelUtils excelUtils;

    @Before
    public void setUp(Scenario scenario) throws IOException {
        ConfigReader.initConfig();
        DriverManager.createDriver();
        String env = EnvironmentManager.getEnvironment();

        String scenarioFullName = scenario.getName();
        System.out.println("Full Scenario Name: " + scenarioFullName);
        // Extract Scenario1
        String scenarioName = scenarioFullName
                .substring(scenarioFullName.lastIndexOf(" ") + 1)
                .replace("\"","");

        System.out.println("Extracted Excel Key: " + scenarioName);

        excelUtils = ExcelUtils.selectFile(env);
        data = excelUtils.getDataBySheetAndScenario("Login",scenarioName);
    }

    @After
    public void cleanUp(Scenario scenario) throws IOException {
        excelUtils.closeWorkbook();
        DriverManager.getDriver().quit();
    }

}
