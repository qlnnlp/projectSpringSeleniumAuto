package extensions;


import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.GuiceModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;


public class UIExtensions implements BeforeEachCallback, AfterEachCallback {

    private final Logger log =  LogManager.getLogger(UIExtensions.class);
    private Injector injector;

    public void beforeEach(ExtensionContext context) {
        injector = Guice.createInjector(new GuiceModule());
        Object testInstance = context.getTestInstance()
                .orElseThrow(()-> new RuntimeException("Test instance not found"));
        injector.injectMembers(testInstance);
    }

    public void afterEach(ExtensionContext context) {
        WebDriver driver = null;
        if (injector != null) {
            try {
                driver = injector.getInstance(WebDriver.class);
            } catch (ConfigurationException e) {
                log.error("WebDriver не зарегестрирован в Guicemodule: {}", e.getMessage());
            } catch (Exception e) {
                log.error("Ошибка при получении WebDriver: {}", e.getMessage());
            }
        }
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                log.error("Ошибка при закрытии WebDriver {}", e.getMessage());
            }
        }

    }

}
