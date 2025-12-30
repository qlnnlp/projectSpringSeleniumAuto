package commons.waiters;

import commons.AbsCommon;
import jakarta.inject.Inject;
import modules.GuiceModule;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class Waiters {

    private WebDriverWait wait;


    private WebDriver driver;



    public Waiters(WebDriver driver) {
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds
                        (Integer.parseInt((System.getProperty("webdriver.waiter.timeout")))));
    }

    public <T> T getWaiter(ExpectedCondition<T> condition) {
        return wait.until(condition);
    }

    public <T> boolean waitForCondition(ExpectedCondition<T> condition) {
        try {
            wait.until(condition);
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public boolean waitForElmntPrsntByLocator(By locator) {
        return  waitForCondition(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public <V> V fluentWait(Function<? super WebDriver, V> condition, long timeoutSeconds, long pollingSeconds) {

        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofSeconds(pollingSeconds))
              .ignoring(NoSuchElementException.class);
        return  fluentWait.until(condition);
    }
}
