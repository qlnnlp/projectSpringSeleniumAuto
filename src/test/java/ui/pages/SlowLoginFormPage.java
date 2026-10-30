package ui.pages;

import ui.components.AlertComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SlowLoginFormPage extends BasePage<SlowLoginFormPage> {

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By submitButton = By.cssSelector("button[type='submit'], button");

    private final AlertComponent alertComponent;

    public SlowLoginFormPage() {
        super();
        this.alertComponent = new AlertComponent();
    }

    @Override
    protected String getPagePath() {
        return "/login-slow";
    }

    @Step("Ввести логин: {username}")
    public SlowLoginFormPage enterUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    @Step("Ввести пароль")
    public SlowLoginFormPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    @Step("Нажать кнопку входа")
    public SlowLoginFormPage clickSubmit() {
        click(submitButton);
        return this;
    }

    @Step("Выполнить вход с логином '{username}' и паролем '{password}' (с ожиданием медленного ответа)")
    public SlowLoginFormPage loginAs(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickSubmit();
    }

    public AlertComponent getAlert() {
        return alertComponent;
    }
}