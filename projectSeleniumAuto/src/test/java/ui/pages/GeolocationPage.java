package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GeolocationPage extends BasePage<GeolocationPage> {

    private final By getCoordinatesBtn = By.id("get-coordinates");
    private final By coordinatesResult = By.id("coordinates");

    @Override
    protected String getPagePath() {
        return "/geolocation";
    }

    @Step("Запросить геолокацию")
    public GeolocationPage requestCoordinates() {
        click(getCoordinatesBtn);
        return this;
    }

    public String getCoordinatesText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(coordinatesResult)).getText().trim();
    }
}