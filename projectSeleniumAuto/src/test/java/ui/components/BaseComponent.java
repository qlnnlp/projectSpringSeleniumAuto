package ui.components;

import core.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseComponent {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected static final Duration COMPONENT_TIMEOUT = Duration.ofSeconds(5);

    protected BaseComponent() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(this.driver, COMPONENT_TIMEOUT);
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement findClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        findClickable(locator).click();
    }

    protected String getText(By locator) {
        return find(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}