package core.extensions;

import config.AppProperties;
import core.driver.DriverFactory;
import core.driver.DriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayInputStream;

public class UiTestExtension implements BeforeEachCallback, AfterEachCallback, TestExecutionExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(UiTestExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) {
        AppProperties properties = SpringExtension.getApplicationContext(context)
                .getBean(AppProperties.class);

        WebDriver driver = DriverFactory.createDriver(properties.ui());
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        attachScreenshotOnFailure();
        throw throwable;
    }

    @Override
    public void afterEach(ExtensionContext context) {
        DriverManager.quitDriver();
    }

    private void attachScreenshotOnFailure() {
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver instanceof TakesScreenshot takesScreenshot) {
                byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot on Failure", "image/png", new ByteArrayInputStream(screenshot), "png");
            }
        } catch (Exception e) {
            log.error("Не удалось прикрепить скриншот к Allure отчету", e);
        }
    }
}