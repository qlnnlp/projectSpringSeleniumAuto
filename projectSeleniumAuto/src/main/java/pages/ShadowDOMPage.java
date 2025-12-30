package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Path("/shadow-dom")
public class ShadowDOMPage extends AbsBasePage <ShadowDOMPage> {

    public ShadowDOMPage(WebDriver driver) {
        super(driver);
    }

    //Locators and methods

    @FindBy(id = "content")
    WebElement content;

    SearchContext getShadowRoot() {
        return content.getShadowRoot();
    }

    public WebElement getElementFromSR(By by) {
        return getShadowRoot().findElement(by);
    }

}
