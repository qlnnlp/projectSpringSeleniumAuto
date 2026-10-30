package tests.baseTests;

import core.driver.DriverManager;
import com.example.qa.core.extensions.UiTestExtension;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(UiTestExtension.class) @Tag("ui")
public abstract class BaseUiTest extends BaseTest {

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    protected void open(String relativeOrAbsoluteUrl) {
        String targetUrl = relativeOrAbsoluteUrl.startsWith("http")
                ? relativeOrAbsoluteUrl
                : appProperties.ui().baseUrl() + relativeOrAbsoluteUrl;
        getDriver().get(targetUrl);
    }
}