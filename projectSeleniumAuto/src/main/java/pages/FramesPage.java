package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Path("/frames")
public class FramesPage extends AbsBasePage<FramesPage> {

    public FramesPage(WebDriver driver) {
        super(driver);
    }

    //Selectors and methods

    @FindBy(name = "frame-body")
    public WebElement frame;

    By paragraphsTag = By.tagName("p");

    public void switchToFrame(WebElement frame) {
        waiters.waitForCondition(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
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
