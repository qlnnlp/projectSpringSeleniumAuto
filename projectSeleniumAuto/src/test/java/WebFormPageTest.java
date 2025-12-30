
import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.MainPage;
import pages.WebFormPage;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(UIExtensions.class)
public class WebFormPageTest {

    private static final Logger log = LoggerFactory.getLogger(WebFormPageTest.class);

    @Inject
    MainPage mainPage;

    @Inject
    WebFormPage webFormPage;

    @Test
    void openWebFormFromMain() {
        mainPage.open()
                .click(mainPage.webForm);
        webFormPage
                .pageTitleShouldBe("Hands-On Selenium WebDriver with Java");
        assertThat(webFormPage.rowMain.isDisplayed());
    }

    @Test
    void leftColumnTest() {
        webFormPage
                .open()
                .type(webFormPage.textForm, "Мир вам!");
        assertThat(webFormPage.textForm.getDomProperty("value"))
                .isEqualTo("Мир вам!");

        webFormPage.textForm.clear();
        assertThat(webFormPage.textForm.getDomProperty("value"))
                .isEqualTo("");

        webFormPage.type(webFormPage.passwordForm, "123nN!");
        assertThat(webFormPage.passwordForm.getDomProperty("value"))
                .isEqualTo("123nN!");

        webFormPage.passwordForm.clear();
        assertThat(webFormPage.passwordForm.getDomProperty("value"))
                .isEqualTo("");

        webFormPage.type(webFormPage.textAreaForm,"TestForText123");
        assertThat(webFormPage.textAreaForm.getDomProperty("value"))
                .isEqualTo("TestForText123");

        webFormPage.textAreaForm.clear();
        assertThat(webFormPage.textAreaForm.getDomProperty("value"))
                .isEqualTo("");

        assertThat(webFormPage.disabledInputForm.isEnabled())
                .isFalse();
        try {
            webFormPage.disabledInputForm.sendKeys("cannot be send");
            Assertions.fail
                    ("Должно быть поймано исключение ElementNotInteractableException");
        } catch (ElementNotInteractableException e) {
            log.info("False positive for disabled field");
        }

        assertThat(webFormPage.readOnlyInputForm.isEnabled())
                .isTrue();
        assertThat(webFormPage.readOnlyInputForm.getDomAttribute("value"))
                .isEqualTo("Readonly input");
        try {
            webFormPage.readOnlyInputForm.sendKeys
                    ("can be send but do not change the value");
            assertThat(webFormPage.readOnlyInputForm.getDomProperty("value"))
                    .isEqualTo("Readonly input");
        } catch (ElementNotInteractableException e) {
            log.info("ElementNotInteractableException was caught - negative test succeeded");
        }

        webFormPage.returnToIndex.click();
        assertThat(mainPage.lowerRowCards.isDisplayed())
                .isTrue();
        assertThat(mainPage.upperRowCards.isDisplayed())
                .isTrue();
    }

    @Test
    void middleColumnTest() {
        webFormPage.open();

        assertThat(webFormPage.middleColumn.isDisplayed())
                .isTrue();

        assertThat(webFormPage.selectDropdown.findElements(By.tagName("option")))
                .hasSize(4);

        assertThat(webFormPage.getSelectDropdown().getFirstSelectedOption().getText())
                .isEqualTo("Open this select menu");

        assertThat(webFormPage.selectDropdown.getText())
                .isEqualToIgnoringWhitespace(webFormPage.selectDropdownText);

        assertThat(webFormPage.getSelectedOption("1").getText())
                .isEqualTo("One");

        assertThat(webFormPage.getSelectedOption("2").getText())
                .isEqualTo("Two");

        assertThat(webFormPage.getSelectedOption("3").getText())
                .isEqualTo("Three");

        assertThat(webFormPage.datalistDropdown.getText())
                .isEqualToIgnoringWhitespace("Dropdown (datalist)");

        assertThat(webFormPage.datalistDropdown.findElement(By.tagName("input"))
                .getDomAttribute("placeholder"))
                .isEqualTo(webFormPage.datalistPlaceholder);

        assertThat(webFormPage.datalistDropdown.findElements(By.tagName("option")))
                .hasSize(5);

        assertThat(webFormPage.datalistDropdown.findElements(By.tagName("option"))
                .stream()
                .map(e -> e.getDomAttribute("value")))
                .isEqualTo(webFormPage.datalistOptions);


        webFormPage.type(webFormPage.datalistDropdown.findElement(By.tagName("input")), "New City");
        assertThat(webFormPage.datalistDropdown
                .findElement(By.tagName("input")).getDomProperty("value"))
                .isEqualTo("New City");

        assertThat(webFormPage.fileInputContainer.getText())
                .isEqualTo("File input");

        assertThat(webFormPage.fileInputContainer
                .findElement(By.tagName("input"))
                .getDomProperty("value"))
                .isEqualTo("");

        webFormPage.fileInputContainer
                .findElement(By.tagName("input"))
                .sendKeys(webFormPage.filePathForInput);

        assertThat(webFormPage.fileInputContainer
                .findElement(By.tagName("input"))
                .getDomProperty("value"))
                .isNotEqualTo("");

        assertThat(webFormPage.checkedCheckbox.getText())
                .isEqualTo("Checked checkbox");


        assertThat(webFormPage.checkedCheckbox
                .findElement(By.tagName("input")).isSelected())
                .isTrue();

        webFormPage.checkedCheckbox.click();

        assertThat(webFormPage.checkedCheckbox
                .findElement(By.tagName("input")).isSelected())
                .isFalse();

        assertThat(webFormPage.defaultCheckbox.getText())
                .isEqualTo("Default checkbox");

        assertThat(webFormPage.defaultCheckbox
                .findElement(By.tagName("input")).isSelected())
                .isFalse();

        webFormPage.defaultCheckbox.click();

        assertThat(webFormPage.defaultCheckbox
                .findElement(By.tagName("input")).isSelected())
                .isTrue();

        assertThat(webFormPage.checkedRadio.getText())
                .isEqualTo("Checked radio");


        assertThat(webFormPage.checkedRadio
                .findElement(By.tagName("input")).isSelected())
                .isTrue();

        webFormPage.checkedRadio.click();

        assertThat(webFormPage.checkedRadio
                .findElement(By.tagName("input")).isSelected())
                .isTrue();


        assertThat(webFormPage.defaultRadio.getText())
                .isEqualTo("Default radio");

        assertThat(webFormPage.defaultRadio
                .findElement(By.tagName("input")).isSelected())
                .isFalse();

        webFormPage.defaultRadio.click();

        assertThat(webFormPage.defaultRadio
                .findElement(By.tagName("input")).isSelected())
                .isTrue();

        assertThat(webFormPage.checkedRadio
                .findElement(By.tagName("input")).isSelected())
                .isFalse();

        assertThat(webFormPage.button.getText())
                .isEqualTo("Submit");

        webFormPage.button.click();

        assertThat(webFormPage.isFormSubmittedDisplayed())
                .isTrue();

        assertThat(webFormPage.formSubmitted.getText())
                .isEqualTo("Form submitted");
  }

    @Test
    void rightColumnTest() {
        webFormPage.open();

            assertThat(webFormPage.colorPicker.getText())
                    .isEqualTo("Color picker");

            assertThat(webFormPage.colorPickerInput.getDomAttribute("value"))
                .isEqualTo("#563d7c");

            webFormPage.js.executeScript
         (webFormPage.script, webFormPage.colorPickerInput);

        assertThat(Color.fromString
                (webFormPage.colorPickerInput
                .getDomAttribute("value")))
                .isEqualTo(webFormPage.red);

        assertThat(webFormPage.datePicker.getText())
                .isEqualTo("Date picker");

        webFormPage.datePicker.click();

        assertThat(webFormPage.getTBodyTrDayRows().size())
                .isEqualTo(6);

        assertThat(webFormPage
                .getTHeadText()
                .equalsIgnoreCase
                        (webFormPage.currentMonth + " " + webFormPage.currentYear))
                .isTrue();

        webFormPage.clickLeftArrowByIndex(0);
        webFormPage.clickRightArrowByIndex(0);
        webFormPage.getTHeadElementForCurrentYM().click();
        webFormPage.clickLeftArrowByIndex(1);


        assertThat(Integer.parseInt
               (webFormPage.getCentralTHeadElement(1).getText()))
               .isEqualTo(webFormPage.currentYear - 1);

        assertThat(webFormPage.getFocusedMonth().getText())
                .isEqualToIgnoringCase(webFormPage.currentMonth.substring(0,3));

        webFormPage.getFocusedMonth().click();
        webFormPage.getDayFromTheTable("day", webFormPage.currentDay).click();

        assertThat(webFormPage.getSelectedDate())
                .isEqualTo(webFormPage.expectedDate);

        assertThat(webFormPage.rangeSlider.getText())
                .isEqualTo("Example range");

        var currentSliderValue = webFormPage.getSliderValue();
        webFormPage.sliderArrowInputSender(3, Keys.ARROW_LEFT);
        assertThat(currentSliderValue)
                .isNotEqualTo(webFormPage.getSliderValue());

        webFormPage.sliderArrowInputSender(10,Keys.ARROW_RIGHT);
        assertThat(webFormPage.getSliderValue())
                .isEqualTo("10");
    }
}


