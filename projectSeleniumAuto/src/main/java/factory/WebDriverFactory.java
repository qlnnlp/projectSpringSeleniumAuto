package factory;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class WebDriverFactory {



    public WebDriver create(BrowserType type, Object options) {
        switch (type) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver((ChromeOptions) options);
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver((EdgeOptions) options);
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver((FirefoxOptions) options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + type);
        }
    }

    public WebDriver create(BrowserType type) {
        switch (type) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + type);
        }
    }
}

