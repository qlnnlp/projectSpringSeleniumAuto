package core.driver;

import config.AppProperties.UiProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public final class DriverFactory {

    private DriverFactory() {}

    public static WebDriver createDriver(UiProperties properties) {
        BrowserType browserType = BrowserType.valueOf(properties.browser().toUpperCase());
        Capabilities capabilities = buildCapabilities(browserType, properties.headless());

        if (properties.remoteUrl() != null && !properties.remoteUrl().isBlank()) {
            try {
                RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(properties.remoteUrl()), capabilities);
                // Включаем передачу локальных файлов контейнера в удаленный браузер по сети
                remoteDriver.setFileDetector(new LocalFileDetector());
                return remoteDriver;
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Некорректный remote-url: " + properties.remoteUrl(), e);
            }
        }

        return switch (browserType) {
            case CHROME -> new ChromeDriver((ChromeOptions) capabilities);
            case FIREFOX -> new FirefoxDriver((FirefoxOptions) capabilities);
            case EDGE -> new EdgeDriver((EdgeOptions) capabilities);
        };
    }

    private static Capabilities buildCapabilities(BrowserType browserType, boolean headless) {
        return switch (browserType) {
            case CHROME -> {
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless=new");
                }
                options.addArguments(
                        "--no-sandbox",
                        "--disable-dev-shm-usage",
                        "--window-size=1920,1080",
                        "--use-fake-ui-for-media-stream",
                        "--use-fake-device-for-media-stream",
                        "--lang=es-ES"
                );

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.notifications", 1);
                prefs.put("profile.default_content_setting_values.geolocation", 1);
                prefs.put("intl.accept_languages", "es-ES,es");
                options.setExperimentalOption("prefs", prefs);

                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.BROWSER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logPrefs);

                yield options;
            }
            case FIREFOX -> {
                FirefoxOptions options = new FirefoxOptions();
                if (headless) {
                    options.addArguments("-headless");
                }
                options.addArguments("--width=1920", "--height=1080");
                yield options;
            }
            case EDGE -> {
                EdgeOptions options = new EdgeOptions();
                if (headless) {
                    options.addArguments("--headless=new");
                }
                options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
                yield options;
            }
        };
    }
}