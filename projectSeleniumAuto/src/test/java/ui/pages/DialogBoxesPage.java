package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.components.ModalComponent;

public class DialogBoxesPage extends BasePage<DialogBoxesPage> {

    private final By alertBtn = By.id("my-alert");
    private final By confirmBtn = By.id("my-confirm");
    private final By promptBtn = By.id("my-prompt");
    private final By promptResultText = By.id("prompt-text");
    private final By modalBtn = By.id("my-modal");
    private final By modalResultText = By.id("modal-text");

    private final ModalComponent modalComponent;

    public DialogBoxesPage() {
        super();
        this.modalComponent = new ModalComponent();
    }

    @Override
    protected String getPagePath() {
        return "/dialog-boxes";
    }

    public ModalComponent getModal() {
        return modalComponent;
    }

    @Step("Вызвать нативный Alert и подтвердить")
    public String triggerAlertAndAccept() {
        click(alertBtn);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    @Step("Вызвать Confirm dialog и отклонить")
    public String triggerConfirmAndDismiss() {
        click(confirmBtn);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.dismiss();
        return text;
    }

    @Step("Вызвать Prompt dialog, ввести '{inputText}' и подтвердить")
    public String triggerPromptAndAccept(String inputText) {
        click(promptBtn);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String promptHeader = alert.getText();
        alert.sendKeys(inputText);
        alert.accept();
        return promptHeader;
    }

    public String getPromptResultText() {
        return getText(promptResultText);
    }

    @Step("Открыть модальное окно")
    public DialogBoxesPage openModal() {
        click(modalBtn);
        return this;
    }

    public String getModalResultText() {
        return getText(modalResultText);
    }
}