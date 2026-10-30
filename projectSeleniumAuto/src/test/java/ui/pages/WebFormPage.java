package ui.pages;

import ui.components.FooterComponent;
import ui.components.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WebFormPage extends BasePage<WebFormPage> {

    private final HeaderComponent header = new HeaderComponent();
    private final FooterComponent footer = new FooterComponent();

    // Левая колонка
    private final By textInput = By.id("my-text-id");
    private final By passwordInput = By.name("my-password");
    private final By textareaInput = By.name("my-textarea");
    private final By disabledInput = By.name("my-disabled");
    private final By readonlyInput = By.name("my-readonly");
    private final By returnToIndexLink = By.linkText("Return to index");

    // Центральная колонка
    private final By selectDropdown = By.name("my-select");
    private final By datalistInput = By.name("my-datalist");
    private final By datalistOptions = By.cssSelector("datalist#my-options option");
    private final By fileInput = By.name("my-file");
    private final By checkedCheckbox = By.id("my-check-1");
    private final By defaultCheckbox = By.id("my-check-2");
    private final By checkedRadio = By.id("my-radio-1");
    private final By defaultRadio = By.id("my-radio-2");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By formSubmittedHeader = By.xpath("//*[text()='Form submitted']");

    // Правая колонка
    private final By colorPickerInput = By.name("my-colors");
    private final By datePickerInput = By.name("my-date");
    private final By rangeSliderInput = By.name("my-range");

    @Override
    protected String getPagePath() {
        return "/web-form";
    }

    public HeaderComponent getHeader() {
        return header;
    }

    public FooterComponent getFooter() {
        return footer;
    }

    @Step("Заполнить текстовое поле: {text}")
    public WebFormPage fillTextInput(String text) {
        type(textInput, text);
        return this;
    }

    public String getTextInputValue() {
        return find(textInput).getDomProperty("value");
    }

    @Step("Заполнить пароль: {password}")
    public WebFormPage fillPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    public String getPasswordValue() {
        return find(passwordInput).getDomProperty("value");
    }

    @Step("Заполнить текстовую область (textarea): {text}")
    public WebFormPage fillTextarea(String text) {
        type(textareaInput, text);
        return this;
    }

    public String getTextareaValue() {
        return find(textareaInput).getDomProperty("value");
    }

    public boolean isDisabledInputEnabled() {
        return find(disabledInput).isEnabled();
    }

    public boolean isReadonlyInputEditable() {
        WebElement element = find(readonlyInput);
        return element.getDomAttribute("readonly") == null;
    }

    public String getReadonlyInputValue() {
        return find(readonlyInput).getDomProperty("value");
    }

    @Step("Выбрать значение в дропдауне по видимому тексту: {visibleText}")
    public WebFormPage selectDropdownByText(String visibleText) {
        Select select = new Select(find(selectDropdown));
        select.selectByVisibleText(visibleText);
        return this;
    }

    @Step("Выбрать значение в дропдауне по значению (value): {value}")
    public WebFormPage selectDropdownByValue(String value) {
        Select select = new Select(find(selectDropdown));
        select.selectByValue(value);
        return this;
    }

    public String getSelectedDropdownText() {
        return new Select(find(selectDropdown)).getFirstSelectedOption().getText();
    }

    @Step("Ввести значение в Datalist: {value}")
    public WebFormPage fillDatalist(String value) {
        type(datalistInput, value);
        return this;
    }

    public List<String> getDatalistOptions() {
        return driver.findElements(datalistOptions).stream()
                .map(e -> e.getDomAttribute("value"))
                .toList();
    }

    @Step("Загрузить файл в инпут")
    public WebFormPage uploadTempFile(String fileName, String content) {
        try {
            Path tempFile = Files.createTempFile("upload-", fileName);
            Files.writeString(tempFile, content);
            tempFile.toFile().deleteOnExit();
            find(fileInput).sendKeys(tempFile.toAbsolutePath().toString());
            return this;
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать временный файл для загрузки", e);
        }
    }

    public String getUploadedFileName() {
        return find(fileInput).getDomProperty("value");
    }

    @Step("Установить чекбокс по умолчанию")
    public WebFormPage toggleDefaultCheckbox() {
        click(defaultCheckbox);
        return this;
    }

    public boolean isDefaultCheckboxSelected() {
        return find(defaultCheckbox).isSelected();
    }

    public boolean isCheckedCheckboxSelected() {
        return find(checkedCheckbox).isSelected();
    }

    @Step("Выбрать радиокнопку по умолчанию")
    public WebFormPage selectDefaultRadio() {
        click(defaultRadio);
        return this;
    }

    public boolean isDefaultRadioSelected() {
        return find(defaultRadio).isSelected();
    }

    public boolean isCheckedRadioSelected() {
        return find(checkedRadio).isSelected();
    }

    @Step("Установить цвет через JS: {hexColor}")
    public WebFormPage setColorViaJs(String hexColor) {
        WebElement element = find(colorPickerInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1]);", element, hexColor);
        return this;
    }

    public String getColorPickerValue() {
        return find(colorPickerInput).getDomAttribute("value");
    }

    @Step("Установить дату в DatePicker: {dateFormatted}")
    public WebFormPage setDate(String dateFormatted) {
        WebElement element = find(datePickerInput);
        element.sendKeys(dateFormatted);
        element.sendKeys(Keys.TAB);
        return this;
    }

    public String getDatePickerValue() {
        return find(datePickerInput).getDomProperty("value");
    }

    @Step("Сдвинуть ползунок Range Slider нажатием клавиши {arrowKey} {times} раз")
    public WebFormPage adjustRangeSlider(Keys arrowKey, int times) {
        WebElement slider = find(rangeSliderInput);
        for (int i = 0; i < times; i++) {
            slider.sendKeys(arrowKey);
        }
        return this;
    }

    public String getRangeSliderValue() {
        return find(rangeSliderInput).getDomProperty("value");
    }

    @Step("Отправить форму (клик по Submit)")
    public WebFormPage submitForm() {
        click(submitButton);
        return this;
    }

    public boolean isFormSubmittedMessageDisplayed() {
        return isDisplayed(formSubmittedHeader);
    }

    @Step("Вернуться на главную страницу через ссылку")
    public MainPage returnToMain() {
        click(returnToIndexLink);
        return new MainPage();
    }
}