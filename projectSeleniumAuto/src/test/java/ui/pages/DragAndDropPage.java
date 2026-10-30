package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropPage extends BasePage<DragAndDropPage> {

    private final By draggableCard = By.id("draggable");
    private final By targetPanel = By.id("target");

    @Override
    protected String getPagePath() {
        return "/drag-and-drop";
    }

    @Step("Получить текущие координаты перемещаемого элемента")
    public Point getDraggableLocation() {
        return find(draggableCard).getLocation();
    }

    @Step("Получить координаты целевой панели")
    public Point getTargetLocation() {
        return find(targetPanel).getLocation();
    }

    @Step("Переместить элемент со смещением: x={xOffset}, y={yOffset}")
    public DragAndDropPage dragByOffset(int xOffset, int yOffset) {
        WebElement element = find(draggableCard);
        new Actions(driver).dragAndDropBy(element, xOffset, yOffset).perform();
        return this;
    }

    @Step("Перетащить элемент на целевую панель (Drag and Drop)")
    public DragAndDropPage dragToTarget() {
        WebElement source = find(draggableCard);
        WebElement target = find(targetPanel);
        new Actions(driver).dragAndDrop(source, target).perform();
        return this;
    }
}