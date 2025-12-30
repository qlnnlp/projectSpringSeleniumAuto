package pages;


import annotations.Path;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Path("/web-storage")
public class WebStoragePage extends AbsBasePage<WebStoragePage> {

    public WebStoragePage(WebDriver driver) {
        super(driver);
    }

    JavascriptExecutor js = (JavascriptExecutor) driver;


    public long getLocalStorageSize() throws NullPointerException {
        var result = Objects.requireNonNull(js.executeScript
                ("return localStorage.length;"));
            return (Long) result;
    }

    public long getSessionStorageSize() throws NullPointerException {
        var result = Objects.requireNonNull( js.executeScript
                ("return sessionStorage.length;"));
        return (Long) result;
    }

    public void addElementToStorageSession(String element, String value) {
        js.executeScript("sessionStorage.setItem('"+ element +"','"+ value +"');");
    }

    public Set<String> getSessionStorageKeySet() throws NullPointerException {
        @SuppressWarnings("unchecked")
        List<String> keys = (List<String>) Objects.requireNonNull(js.executeScript(
                "var keys = []; " +
                        "for (var i = 0; i < sessionStorage.length; i++) { " +
                        "  keys.push(sessionStorage.key(i)); " +
                        "} " +
                        "return keys;"));
        return new HashSet<>(keys);
    }
}




