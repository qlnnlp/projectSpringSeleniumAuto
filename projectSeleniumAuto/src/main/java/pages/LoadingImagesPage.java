package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Path("/loading-images")
public class LoadingImagesPage extends AbsBasePage <LoadingImagesPage> {

    public LoadingImagesPage(WebDriver driver) {
        super(driver);
    }

    // Locators and methods

    @FindBy(id = "image-container")
    public WebElement imageContainer;

    @FindBy(id = "compass")
    public WebElement compass;

    @FindBy(id = "calendar")
    public WebElement calendar;

    @FindBy(id = "award")
    public WebElement award;

    @FindBy(id = "landscape")
    public WebElement landscape;

    public String getImgSrc(WebElement element) {
        waiters.fluentWait
                (ExpectedConditions.visibilityOf(element),10,2);
        return  element.getDomAttribute("src");
    }

}
