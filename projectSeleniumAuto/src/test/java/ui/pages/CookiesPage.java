package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import java.util.Optional;
import java.util.Set;

public class CookiesPage extends BasePage<CookiesPage> {

    @Override
    protected String getPagePath() {
        return "/cookies";
    }

    public Set<Cookie> getAllCookies() {
        return driver.manage().getCookies();
    }

    public Optional<Cookie> getCookie(String name) {
        return Optional.ofNullable(driver.manage().getCookieNamed(name));
    }

    public String getCookieValue(String name) {
        return getCookie(name).map(Cookie::getValue).orElse(null);
    }

    @Step("Добавить куку '{name}'='{value}'")
    public CookiesPage addCookie(String name, String value) {
        driver.manage().addCookie(new Cookie(name, value));
        return this;
    }

    @Step("Удалить куку '{name}'")
    public CookiesPage deleteCookie(String name) {
        driver.manage().deleteCookieNamed(name);
        return this;
    }
}