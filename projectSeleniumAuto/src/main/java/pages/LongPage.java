package pages;

import annotations.Path;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

@Path("/long-page")
public class LongPage extends AbsBasePage <LongPage> {

    public LongPage(WebDriver driver) {
        super(driver);
    }

    // Selectors and methods

    @FindBy(css = "p:first-child")
    public WebElement firstParagraph;

    @FindBy(css = "p:last-child")
    public WebElement lastParagraph;

    JavascriptExecutor js =  (JavascriptExecutor) driver;

    String scrollScript = "window.scrollBy(0, 1000);";
    String scrollTo = "arguments[0].scrollIntoView();";


    public void executeScrollScript() {
        js.executeScript(scrollScript);
    }

    public void scrollToTheParagraph(WebElement paragraph) {
        waiters.waitForCondition(ExpectedConditions.visibilityOf(paragraph));
        js.executeScript(scrollTo, paragraph);
    }


}
