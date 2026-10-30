package ui.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class AlertComponent extends BaseComponent {

    private final By successAlert = By.id("success");
    private final By dangerAlert = By.id("invalid");

    @Step("Получить текст сообщения об успехе")
    public String getSuccessMessage() {
        return getText(successAlert);
    }

    @Step("Получить текст сообщения об ошибке")
    public String getDangerMessage() {
        return getText(dangerAlert);
    }

    @Step("Проверить видимость алерта об успехе")
    public boolean isSuccessAlertDisplayed() {
        return isDisplayed(successAlert);
    }

    @Step("Проверить видимость алерта об ошибке")
    public boolean isDangerAlertDisplayed() {
        return isDisplayed(dangerAlert);
    }
}