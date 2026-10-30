package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

public class WebStoragePage extends BasePage<WebStoragePage> {

    @Override
    protected String getPagePath() {
        return "/web-storage";
    }

    public long getLocalStorageSize() {
        Object size = ((JavascriptExecutor) driver).executeScript("return localStorage.length;");
        return size instanceof Number number ? number.longValue() : 0;
    }

    public long getSessionStorageSize() {
        Object size = ((JavascriptExecutor) driver).executeScript("return sessionStorage.length;");
        return size instanceof Number number ? number.longValue() : 0;
    }

    @Step("Добавить ключ '{key}' со значением '{value}' в SessionStorage")
    public WebStoragePage setSessionStorageItem(String key, String value) {
        ((JavascriptExecutor) driver).executeScript("sessionStorage.setItem(arguments[0], arguments[1]);", key, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public List<String> getSessionStorageKeys() {
        String script = """
            var keys = [];
            for (var i = 0; i < sessionStorage.length; i++) {
                keys.push(sessionStorage.key(i));
            }
            return keys;
        """;
        return (List<String>) ((JavascriptExecutor) driver).executeScript(script);
    }
}