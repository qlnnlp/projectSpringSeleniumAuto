package pages;

import annotations.Path;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


@Path("/web-form")
public class WebFormPage extends AbsBasePage <WebFormPage> {


    public WebFormPage(WebDriver driver) {
        super(driver);
    }

    // basic selectors

    @FindBy(css = "div.col.col-10 > h5")
    public WebElement practiceSite;

    @FindBy(className = "img-fluid")
    public WebElement imgFluid;

    @FindBy(css = " div.col.col-2 > a")
    public WebElement imgFluidLink;

    @FindBy(className = "display-6")
    public WebElement webFormDisplayRow;

    @FindBy(css = "form > div")
    public WebElement rowMain;

    @FindBy(css = "form > div > div:nth-child(1)")
    public WebElement leftColumn;

    @FindBy(css = "form > div > div:nth-child(2)")
    public WebElement middleColumn;

    @FindBy(css = "form > div > div:nth-child(3)")
    public WebElement rightColumn;

    @FindBy(css = "body > footer")
    public WebElement footer;

    // leftColumn selectors and methods

    @FindBy(id = "my-text-id")
    public WebElement textForm;

    @FindBy(name = "my-password")
    public WebElement passwordForm;

    @FindBy(name = "my-textarea")
    public WebElement textAreaForm;

    @FindBy(name = "my-disabled")
    public WebElement disabledInputForm;

    @FindBy(name = "my-readonly")
    public WebElement readOnlyInputForm;

    @FindBy(className = "form-group")
    public WebElement returnToIndex;

    // middleColumnSelectors and methods

    @FindBy(className = "form-select")
    public WebElement selectDropdown;
    public String selectDropdownText =
            "Open this select menu" +
                    "One" + "Two" + "Three";

    public Select getSelectDropdown() {
        return new Select(selectDropdown);
    }

    public WebElement getSelectedOption(String value) {
        Select select = new Select(selectDropdown);
        select.selectByValue(value);
        return select.getFirstSelectedOption();
    }

    @FindBy(css = "div:nth-child(2) > label:nth-child(2)")
    public WebElement datalistDropdown;
    public String datalistPlaceholder = "Type to search...";
    public List<String> datalistOptions = Arrays.asList(
            "San Francisco",
            "New York",
            "Seattle",
            "Los Angeles",
            "Chicago"
    );

    @FindBy(css = "div:nth-child(2) > label:nth-child(3)")
    public WebElement fileInputContainer;
    public String filePathForInput =
            "D:\\projectSeleniumAuto\\projectSeleniumAuto\\src\\main\\resources\\pictures\\ZfAE4yXQOq0.jpg";

    @FindBy(css = "div:nth-child(4) > label:nth-child(1)")
    public WebElement checkedCheckbox;

    @FindBy(css = "div:nth-child(4) > label:nth-child(2)")
    public WebElement defaultCheckbox;

    @FindBy(css = "div:nth-child(2) > div:nth-child(5)")
    public WebElement checkedRadio;

    @FindBy(css = "div:nth-child(2) > div:nth-child(6)")
    public WebElement defaultRadio;

    @FindBy(tagName = "button")
    public WebElement button;

    @FindBy(xpath = "//*[text()='Form submitted']")
    public WebElement formSubmitted;
    public boolean isFormSubmittedDisplayed() {
        return waiters.waitForCondition(ExpectedConditions.visibilityOf(formSubmitted));
    }


    // rightColumn locators and methods

    @FindBy(css = "div:nth-child(3) > label:nth-child(1)")
    public WebElement colorPicker;


    @FindBy(css = "div:nth-child(3) > label:nth-child(1) > input")
    public WebElement colorPickerInput;
    public JavascriptExecutor js = (JavascriptExecutor) driver;
    public Color red = new Color(255, 0, 0, 1);
    public String script = String.format("arguments[0].setAttribute('value', '%S')", red.asHex());

    @FindBy(xpath = "//label[contains(text(), 'Date picker')]")
    public WebElement datePicker;

    @FindBy(css = "input[name = 'my-date']")
    public WebElement datePickerInput;
    LocalDate today = LocalDate.now();
    public int currentYear = today.getYear();
    public String currentMonth = today.getMonth().toString();
    public int currentDay = today.getDayOfMonth();
    public String formattedCurrentYM = String.format("//th[contains(text(), '%s %d')]",
            currentMonth.substring(0, 1).toUpperCase() +
                    currentMonth.substring(1).toLowerCase(), currentYear);
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate previousYear = today.minusYears(1);
    public String expectedDate = previousYear.format(dateFormat);




    public WebElement getTHeadElementForCurrentYM() {
        waiters.waitForElmntPrsntByLocator(By.xpath(formattedCurrentYM));
        return driver.findElement(By.xpath(formattedCurrentYM));
    }

    //5 эелементов; 0 - стартовая глубина вложенности пикера
    public WebElement getCentralTHeadElement(int index) throws IllegalArgumentException {
        List<WebElement> tHeadList = driver.findElements(By.cssSelector("th.datepicker-switch"));
        if (index >= tHeadList.size() || index < 0) {
            log.error("Index {} is out of bounds for THeadElement list size {}", index, tHeadList.size());
        }
        return tHeadList.get(index);
    }

    public String getTHeadText() {
        return getTHeadElementForCurrentYM().getText();
    }

    public List<WebElement> getTBodyTrDayRows() {
        return driver.findElements
                (By.xpath("//tbody/tr[position() <= 6 and td[contains(@class, 'day')]]"));
    }

    //5 эелементов; 0 - стартовая глубина вложенности пикера
    public void clickLeftArrowByIndex(int index) throws IllegalArgumentException {

        List<WebElement> prevList = driver.findElements(By.cssSelector("tr > th[class = prev]"));
        if (index >= prevList.size() || index < 0) {
            log.error("Index {} is out of bounds for prev buttons list size {}", index, prevList.size());
            throw new IllegalArgumentException("Index is out of bounds for prev buttons list size");
        }
        prevList.get(index).click();
    }

    public void clickRightArrowByIndex(int index) throws IllegalArgumentException {
        List<WebElement> nextList = driver.findElements(By.cssSelector("tr > th[class = next]"));
        if (index >= nextList.size() || index < 0) {
            log.error("Index {} is out of bounds for next buttons list size {}", index, nextList.size());
            throw new IllegalArgumentException("Index is out of bounds for next buttons list size");
        }
        nextList.get(index).click();
    }

    public WebElement getFocusedMonth() {
        return driver.findElement(By.cssSelector(".month.focused"));
    }

    public WebElement getDayFromTheTable(String classOfTheDay, int day) throws IllegalArgumentException {
        if (!(classOfTheDay.equals("day") || classOfTheDay.equals("old.day") ||
                classOfTheDay.equals("new.day") || classOfTheDay.equals("active day"))) {
            log.error("classOfTheDay must be relevant to the <td> class name in the <tbody>");
            throw new IllegalArgumentException("classOfTheDay must be relevant to the <td> class name in the <tbody>");
        }
        if (day <= 0 || day > 31) {
            log.error("Illegal number for the day");
            throw new IllegalArgumentException("Illegal number for the day");
        }

        List<WebElement> rows = getTBodyTrDayRows();
        for (WebElement row : rows) {
            List<WebElement> dayClassElements = row.findElements(By.cssSelector("td." + classOfTheDay));
            for (WebElement dayClassElement : dayClassElements) {
                String dayTextNumber = dayClassElement.getText().trim();
                log.debug("day text number = {}", dayTextNumber);
                log.debug("attribute class: {}",  dayClassElement.getDomAttribute("class"));
                if (dayTextNumber.equals(String.valueOf(day)) &&
                        dayClassElement.getDomAttribute("class").equals(classOfTheDay)) {
                    log.debug("day founded");
                    return dayClassElement;
                }
            }
        }
         log.error("Day not found");
        return null;
    }

    public String getSelectedDate() {
        log.info("Текущая выбранная дата {}",
                        datePicker.findElement(By.cssSelector("[name = my-date]")).getDomProperty("value"));
        return   datePicker.findElement(By.cssSelector("[name = my-date]")).getDomProperty("value");
    }

    @FindBy(xpath = "//label[contains(text(), 'Example range')]")
    public WebElement rangeSlider;

    public WebElement getRangeSliderInput() {
        return driver.findElement(By.name("my-range"));
    }

    public String getSliderValue() {
        log.info("Current slider value: {}", getRangeSliderInput().getDomProperty("value"));
        return getRangeSliderInput().getDomProperty("value");
    }

    public void sliderArrowInputSender(int times, Keys side) throws IllegalArgumentException {
        if (times <= 0) {
            log.error("parameter times can't be a 0 or a negative number:  {} ", times);
            throw new IllegalArgumentException("times must be a positive number");
        }
        if (!(side.equals(Keys.ARROW_LEFT) || side.equals(Keys.ARROW_RIGHT))) {
            log.error("Side have to be ARROW_LEFT or ARROW_RIGHT: {}",side);
            throw new IllegalArgumentException("Side have to be ARROW_LEFT or ARROW_RIGHT");
        }
        for(int i = 0; i < times; i++) {
            getRangeSliderInput().sendKeys(side);
        }
    }
}








