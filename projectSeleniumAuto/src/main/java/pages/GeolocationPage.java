package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Path("/geolocation")
public class GeolocationPage extends AbsBasePage<GeolocationPage> {

    public GeolocationPage(WebDriver driver) {
        super(driver);
    }

    //Selectors and methods

    @FindBy(id = "get-coordinates")
    public WebElement coordinatesB;

    @FindBy(id = "coordinates")
    WebElement coordinatesText;

    public void clickCoordinatesB() {
        waiters.waitForCondition(
                ExpectedConditions.elementToBeClickable(coordinatesB)
        );
        coordinatesB.click();
    }

     public String getCoordinates(int triesToGet) {
        waiters.waitForCondition(
                ExpectedConditions.visibilityOf(coordinatesText)
        );
      var text = coordinatesText.getText();
      var errorText = "Error accessing to current geolocation position";
      for (int i = 0; i < triesToGet; i++) {
      if (text.equals(errorText)) {
          coordinatesB.click();
          waiters.waitForCondition(
                  ExpectedConditions.textToBePresentInElement(coordinatesText, "Latitude"));
          text = coordinatesText.getText();
          if (text.contains("Latitude")) break;
      }
      }
      return text;
    }
}



