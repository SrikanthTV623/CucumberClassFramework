package com.automation.pages;

import com.automation.utils.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 10;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public BasePage(){
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);

    }

    // WAIT METHODS

    public WebElement waitForElement(WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElement(WebElement element) {
        return waitForElement(element, DEFAULT_TIMEOUT);
    }

    // CLICK METHODS

    public void clickElement(WebElement element) {
        waitForElement(element).click();
    }

    public void clickElement(WebElement element, int waitTime) {
        waitForElement(element, waitTime).click();
    }

    public void advanceClickElement(WebElement element) {

        int maxAttempts = 3;

        for (int i = 1; i <= maxAttempts; i++) {
            try {
                waitForElement(element).click();
                return;
            }
            catch (Exception e) {

                System.out.println("Attempt " + i + " failed. Trying JS click...");

                try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", element);
                    return;
                }
                catch (Exception ex) {
                    if (i == maxAttempts) {
                        throw new RuntimeException(
                                "Click failed after " + maxAttempts + " attempts", ex
                        );
                    }
                }
            }
        }
    }

}

