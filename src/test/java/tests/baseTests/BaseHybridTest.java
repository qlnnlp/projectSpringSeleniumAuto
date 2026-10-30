package tests.baseTests;

import api.clients.AuthApiClient;
import api.clients.UserApiClient;
import api.services.UserService;
import io.qameta.allure.Step;
import net.datafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.springframework.beans.factory.annotation.Autowired;

@Tag("hybrid")
public abstract class BaseHybridTest extends BaseUiTest {

    @Autowired
    protected UserApiClient userApiClient;

    @Autowired
    protected AuthApiClient authApiClient;

    @Autowired
    protected UserService userService;

    @Autowired
    protected Faker faker;

    @Step("Инжектировать Cookie в текущую сессию браузера: {name}")
    protected void injectCookie(String name, String value) {
        getDriver().manage().addCookie(new Cookie(name, value));
    }

    @Step("Инжектировать токен в LocalStorage браузера")
    protected void injectLocalStorageItem(String key, String value) {
        ((JavascriptExecutor) getDriver())
                .executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", key, value);
    }
}