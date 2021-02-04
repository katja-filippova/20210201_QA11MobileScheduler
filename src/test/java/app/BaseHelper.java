package app;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseHelper {

    AppiumDriver driver;

    public BaseHelper(AppiumDriver driver) {
        this.driver = driver;
    }


    public void tap(By locator) {
        driver.findElement(locator).click();
    }

    public void fillInForm(By locator, String text) {
        if (text != null) {
            tap(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void hideKeyboard() {
        driver.hideKeyboard();
    }

    public WebElement waitForElement(By locator, int timeToWait) {
        return new WebDriverWait(driver, timeToWait)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
