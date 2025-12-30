package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;


@Path("/mouse-over")
public class MouseOverPage extends AbsBasePage<MouseOverPage> {

    public MouseOverPage(WebDriver driver) {
        super(driver);
    }

    // Selectors and methods

    @FindBy(xpath = "//img[@src = 'img/compass.png']")
    public WebElement imgCompass;


    @FindBy(xpath = "//img[@src = 'img/calendar.png']")
    public WebElement imgCalendar;

    @FindBy(xpath = "//img[@src = 'img/award.png']")
    public WebElement imgAward;

    @FindBy(xpath = "//img[@src = 'img/landscape.png']")
    public WebElement imgLandscape;

    Actions action = new Actions(driver);

    public void moveToImg(WebElement element) {
        waiters.waitForCondition(ExpectedConditions.visibilityOf(element));
        action.moveToElement(element).build().perform();
    }

    public String getCaption(String caption) {
        String captionPath = String.format("//img[@src = 'img/%s.png']",caption).toLowerCase();
        var captionLocation = driver.findElement
                (RelativeLocator.with(By.tagName("div"))
                        .near(By.xpath(captionPath)));
        return captionLocation.getText();
    }
}


