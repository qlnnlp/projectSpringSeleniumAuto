package ui.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ModalComponent extends BaseComponent {

    private final By modalDialog = By.className("modal-dialog");
    private final By modalTitle = By.className("modal-title");
    private final By modalBody = By.className("modal-body");
    private final By saveButton = By.xpath("//div[contains(@class,'modal-footer')]//button[contains(text(), 'Save')]");
    private final By closeButton = By.xpath("//div[contains(@class,'modal-footer')]//button[contains(text(), 'Close')]");

    @Step("Проверить, отображается ли модальное окно")
    public boolean isModalOpen() {
        return isDisplayed(modalDialog);
    }

    @Step("Получить заголовок модального окна")
    public String getTitle() {
        return getText(modalTitle);
    }

    @Step("Получить содержимое (body) модального окна")
    public String getBodyText() {
        return getText(modalBody);
    }

    @Step("Нажать 'Save changes' в модальном окне")
    public void saveChanges() {
        click(saveButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalDialog));
    }

    @Step("Нажать 'Close' в модальном окне")
    public void close() {
        click(closeButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalDialog));
    }
}