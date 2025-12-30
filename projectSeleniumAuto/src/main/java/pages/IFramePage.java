package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Path("/iframes")
public class IFramePage extends AbsBasePage <IFramePage> {

    public IFramePage(WebDriver driver) {
        super(driver);
    }

    By paragraphsTag = By.tagName("p");

    @FindBy(name = "my-iframe")
    public WebElement iframe;

    public void switchToIFrame(WebElement frame) {
        waiters.waitForCondition(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt("my-iframe"));
    }

    public List<WebElement> getParagraphs() {

       return  waiters.getWaiter
                (ExpectedConditions.numberOfElementsToBeMoreThan
                        (paragraphsTag, 0));

    }

    public String getTextFromParagraph(int index) {
        return getParagraphs().get(index).getText();
    }

}
