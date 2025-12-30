package pages;


import annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Path("/login-form")
public class LoginFormPage extends AbsBasePage<LoginFormPage> {
    public LoginFormPage(WebDriver driver) {
        super(driver);
    }

    // Selectors and methods

    @FindBy(id = "username")
    WebElement userInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(tagName = "button")
    WebElement submitB;

    @FindBy(id = "success")
    WebElement alertSuccessRow;

    @FindBy(id = "invalid")
    WebElement alertInvalidRow;

    public void successLogin() {
        type(userInput, "user");
        type(passwordInput, "user");
        click(submitB);
    }

 public String getAlertSuccessText() {
        waiters.waitForCondition(
                ExpectedConditions
                        .textToBePresentInElement(alertSuccessRow,"success"));
        return alertSuccessRow.getText();
 }
    public void failedLogin() {
        type(userInput, "bad-user");
        type(passwordInput, "bad-user");
        click(submitB);
    }
    public String getAlertInvalidText() {
        waiters.waitForCondition(
                ExpectedConditions
                        .textToBePresentInElement(alertInvalidRow,"Invalid"));
        return alertInvalidRow.getText();
    }
}
