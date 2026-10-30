package ui.pages;

import ui.components.AlertComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginFormPage extends BasePage<LoginFormPage> {

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By submitButton = By.cssSelector("button[type='submit'], button");

    private final AlertComponent alertComponent;

    public LoginFormPage() {
        super();
        this.alertComponent = new AlertComponent();
    }

    @Override
    protected String getPagePath() {
        return "/login-form";
    }

    @Step("Ввести логин: {username}")
    public LoginFormPage enterUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    @Step("Ввести пароль")
    public LoginFormPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    @Step("Нажать кнопку входа")
    public LoginFormPage clickSubmit() {
        click(submitButton);
        return this;
    }

    @Step("Выполнить вход с логином '{username}' и паролем '{password}'")
    public LoginFormPage loginAs(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickSubmit();
    }

    public AlertComponent getAlert() {
        return alertComponent;
    }
}