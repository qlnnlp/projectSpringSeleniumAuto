package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DrawInCanvasPage extends BasePage<DrawInCanvasPage> {

    private final By canvas = By.id("my-canvas");

    @Override
    protected String getPagePath() {
        return "/draw-in-canvas";
    }

    @Step("Нарисовать линию на холсте: steps={steps}, xOffset={xOffset}, yOffset={yOffset}")
    public DrawInCanvasPage drawLine(int steps, int xOffset, int yOffset) {
        WebElement canvasElement = find(canvas);
        Actions action = new Actions(driver);
        action.moveToElement(canvasElement).clickAndHold();
        for (int i = 0; i < steps; i++) {
            action.moveByOffset(xOffset, yOffset);
        }
        action.release().perform();
        return this;
    }

    @Step("Нарисовать окружность на холсте: радиус={radius}, точек={numPoints}")
    public DrawInCanvasPage drawCircle(int radius, int numPoints) {
        WebElement canvasElement = find(canvas);
        Actions action = new Actions(driver);
        action.moveToElement(canvasElement).clickAndHold();

        for (int i = 0; i <= numPoints; i++) {
            double angle = Math.toRadians((double) (360 * i) / numPoints);
            double x = Math.sin(angle) * radius;
            double y = Math.cos(angle) * radius;
            action.moveByOffset((int) x, (int) y);
        }
        action.release().perform();
        return this;
    }
}