package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

@Path("/draw-in-canvas")
public class DrawInCanvasPage extends AbsBasePage <DrawInCanvasPage>{

    public DrawInCanvasPage(WebDriver driver) {
        super(driver);
    }

    // Locators and methods

    @FindBy(id = "my-canvas")
    public WebElement canvas;

    Actions action = new Actions(driver);

    public void drawALine(WebElement element, int steps, int xOffset, int yOffset) {

        action.moveToElement(element).clickAndHold();
        for (int i = 0; i <= steps; i++) {
            action.moveByOffset(xOffset, yOffset);
        }
               action.release().build().perform();
    }

    public void drawALine(WebElement element) {

        action.moveToElement(element).clickAndHold();
        for (int i = 0; i <= 10; i++) {
            action.moveByOffset(-17, -17);
        }
        action.release().build().perform();
    }


    public void drawACircle(WebElement element) {
        action.moveToElement(element).clickAndHold();

       var numPoints = 10;
       var radius = 30;

       for (int i  = 0; i <= numPoints; i++) {
           double angle = Math.toRadians((double) (360 * i) / numPoints);
           double x = Math.sin(angle) * radius;
           double y = Math.cos(angle) * radius;
           action.moveByOffset((int) x, (int) y);
       }
       action.release().build().perform();
    }
}
