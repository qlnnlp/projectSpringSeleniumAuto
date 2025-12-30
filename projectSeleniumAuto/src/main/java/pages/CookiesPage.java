package pages;

import annotations.Path;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;
import java.util.Set;


@Path("/cookies")
public class CookiesPage extends AbsBasePage<CookiesPage> {

    public CookiesPage(WebDriver driver) {
        super(driver);
    }

    // Selectors and methods

    WebDriver.Options options = driver.manage();

    public Set<Cookie> getCookies() {
        return options.getCookies();
    }

    public Cookie getCookie(String cookieName) throws NullPointerException {
        return Objects.requireNonNull
                    (options.getCookieNamed(cookieName));
    }

    public String getCookieName(String cookieName) throws NullPointerException {
        return Objects.requireNonNull
                    (getCookie(cookieName).getName());
    }

    public String getCookieValue(String cookieName) throws NullPointerException {
        return Objects.requireNonNull
                    (getCookie(cookieName).getValue());
    }

    public String getCookiePath(String cookieName) throws NullPointerException {
        return Objects.requireNonNull
                (getCookie(cookieName).getPath());
    }

    public void addNewCookie(String name, String value) {
        options.addCookie(new Cookie(name ,value));
    }

    public void deleteCookie(String cookieName) throws NullPointerException {
        options.deleteCookie(getCookie(cookieName));
    }

    @FindBy(id = "refresh-cookies")
    public WebElement cookiesButton;

}
