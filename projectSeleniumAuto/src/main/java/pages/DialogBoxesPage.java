package pages;

import annotations.Path;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Path("/dialog-boxes")
public class DialogBoxesPage extends AbsBasePage<DialogBoxesPage>{

    public DialogBoxesPage(WebDriver driver) {
        super(driver);
    }

    // Selectors and methods

    @FindBy(id = "my-alert")
    WebElement alertB;

    @FindBy(id = "my-confirm")
    WebElement confirmB;

    @FindBy(id = "my-prompt")
    WebElement promptB;

    @FindBy(id = "prompt-text")
    WebElement promptText;

    @FindBy(id = "my-modal")
    WebElement modalB;

    @FindBy(className = "modal-body")
    WebElement modalBody;

    @FindBy(id = "modal-text")
    WebElement modalText;

    @FindBy(xpath = "//button[@data-bs-dismiss='modal' and contains(text(), 'Save')]")
    WebElement saveChangesB;




    Alert dialogBox;

    public void acceptDBox() {
        waiters.waitForCondition(ExpectedConditions.alertIsPresent());
        dialogBox = driver.switchTo().alert();
        dialogBox.accept();
    }
    public void dismissDBox() {
        waiters.waitForCondition(ExpectedConditions.alertIsPresent());
        dialogBox = driver.switchTo().alert();
        dialogBox.dismiss();
    }
    public String getDBoxText() {
        waiters.waitForCondition(ExpectedConditions.alertIsPresent());
        dialogBox = driver.switchTo().alert();
       return dialogBox.getText();
    }
    public void sendKeysToDBox(String text) {
        waiters.waitForCondition(ExpectedConditions.alertIsPresent());
        dialogBox = driver.switchTo().alert();
        dialogBox.sendKeys(text);
    }
    public String getTypedText() {
       return promptText.getText().substring(11);
    }

    public String getTextFromModalBody() {
        waiters.waitForCondition(
                ExpectedConditions.textToBePresentInElement(modalBody, "modal body"));
        return  modalBody.getText();
    }
    public void pressSaveChangesModal() {
        waiters.waitForCondition(ExpectedConditions.visibilityOf(saveChangesB));
        saveChangesB.click();
    }
    public String getTextResultFromModal() {
        waiters.waitForCondition(
                ExpectedConditions.visibilityOf(modalText));
        return modalText.getText();
    }

    // launch Methods

    public void launchAlert() {
        waiters.waitForCondition
                (ExpectedConditions.elementToBeClickable(alertB));
        alertB.click();
    }

    public void launchConfirm() {
        waiters.waitForCondition
                (ExpectedConditions.elementToBeClickable(confirmB));
        confirmB.click();
    }

    public void launchPrompt() {
        waiters.waitForCondition
                (ExpectedConditions.elementToBeClickable(promptB));
       promptB.click();
    }

    public void launchModal() {
        waiters.waitForCondition
                (ExpectedConditions.elementToBeClickable(modalB));
        modalB.click();
    }

}

