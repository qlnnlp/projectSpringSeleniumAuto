package ui.pages;

import models.ui.UserDataForm;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DataTypesPage extends BasePage<DataTypesPage> {

    private final By firstNameInput = By.name("first-name");
    private final By lastNameInput = By.name("last-name");
    private final By addressInput = By.name("address");
    private final By zipCodeInput = By.name("zip-code");
    private final By cityInput = By.name("city");
    private final By countryInput = By.name("country");
    private final By emailInput = By.name("e-mail");
    private final By phoneInput = By.name("phone");
    private final By jobPositionInput = By.name("job-position");
    private final By companyInput = By.name("company");
    private final By submitButton = By.cssSelector("button[type='submit']");

    private final By successAlerts = By.className("alert-success");

    @Override
    protected String getPagePath() {
        return "/data-types";
    }

    @Step("Заполнить форму данными пользователя из DTO")
    public DataTypesPage fillForm(UserDataForm form) {
        type(firstNameInput, form.firstName());
        type(lastNameInput, form.lastName());
        type(addressInput, form.address());
        type(zipCodeInput, form.zipCode());
        type(cityInput, form.city());
        type(countryInput, form.country());
        type(emailInput, form.email());
        type(phoneInput, form.phone());
        type(jobPositionInput, form.jobPosition());
        type(companyInput, form.company());
        return this;
    }

    @Step("Отправить форму (клик по Submit)")
    public DataTypesPage submit() {
        click(submitButton);
        return this;
    }

    public int getSuccessAlertsCount() {
        return driver.findElements(successAlerts).size();
    }
}