package pages;

import annotations.Path;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

@Path("/drag-and-drop")
public class DragAndDropPage extends AbsBasePage <DragAndDropPage> {


    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }

    //Locators and methods


    @FindBy(id = "draggable")
    public WebElement draggableCard;

    @FindBy(id = "target")
    public WebElement targetPanel;


    Actions action = new Actions(driver);

    public Point getLocation(WebElement element) {
        return draggableCard.getLocation();
    }

    public void dragAndDropBy(WebElement element, int xOffset, int yOffset) {
        action.dragAndDropBy(element, xOffset, yOffset).build().perform();
    }

    public void dragAndDropTo(WebElement element, WebElement target) {
        action.dragAndDrop(element,target).build().perform();
    }




}
