package tests.uiTests;

import org.assertj.core.api.SoftAssertions;
import tests.baseTests.BaseUiTest;
import ui.pages.MainPage;
import ui.pages.WebFormPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Интерактивная Веб-Форма")
public class WebFormPageTest extends BaseUiTest {

    @Test
    @Story("Текстовые поля и ограничения ввода (Левая колонка)")
    @DisplayName("Проверка ввода текста, пароля, textarea, а также валидация disabled и readonly полей")
    void shouldHandleTextInputsAndFieldRestrictions() {
        WebFormPage page = new WebFormPage().open(appProperties.ui().baseUrl());

        page.fillTextInput("Тестовый текст")
                .fillPassword("Secr3t!")
                .fillTextarea("Длинный комментарий в текстовую область");

        assertThat(page.getTextInputValue()).isEqualTo("Тестовый текст");
        assertThat(page.getPasswordValue()).isEqualTo("Secr3t!");
        assertThat(page.getTextareaValue()).isEqualTo("Длинный комментарий в текстовую область");

        assertThat(page.isDisabledInputEnabled())
                .as("Поле disabled не должно быть активно для взаимодействия")
                .isFalse();

        assertThat(page.isReadonlyInputEditable())
                .as("Поле readonly не должно быть доступно для редактирования")
                .isFalse();

        assertThat(page.getReadonlyInputValue())
                .isEqualTo("Readonly input");

        MainPage mainPage = page.returnToMain();
        assertThat(mainPage.getChaptersCount()).isEqualTo(6);
    }

    @Test
    @Story("Выпадающие списки, загрузка файлов и радиокнопки (Центральная колонка)")
    @DisplayName("Проверка Select, Datalist, загрузки файла, переключения checkbox/radio и отправки формы")
    void shouldHandleSelectsFilesAndTogglesThenSubmit() {
        WebFormPage page = new WebFormPage().open(appProperties.ui().baseUrl());

        // 1. Выполняем все действия пользователя с формой
        page.selectDropdownByValue("2");
        page.fillDatalist("New York");
        page.uploadTempFile("test-report.txt", "Sample automation file content");
        page.toggleDefaultCheckbox();
        page.selectDefaultRadio();

        // 2. Мягко валидируем ВСЕ состояния формы в одном блоке
        SoftAssertions.assertSoftly(softly -> {

            softly.assertThat(page.getSelectedDropdownText())
                    .as("Выбранное значение в Select")
                    .isEqualTo("Two");

            softly.assertThat(page.getDatalistOptions())
                    .as("Доступные опции в Datalist")
                    .containsAll(List.of("San Francisco", "New York", "Seattle", "Los Angeles", "Chicago"));

            softly.assertThat(page.getUploadedFileName())
                    .as("Инпут загрузки файла должен содержать имя файла")
                    .contains("test-report.txt");

            softly.assertThat(page.isDefaultCheckboxSelected())
                    .as("Чекбокс по умолчанию после клика")
                    .isTrue();

            softly.assertThat(page.isCheckedRadioSelected())
                    .as("Изначально выбранная радиокнопка должна сброситься")
                    .isFalse();

            softly.assertThat(page.isDefaultRadioSelected())
                    .as("Радиокнопка по умолчанию после клика")
                    .isTrue();
        });

        // 3. Отправка формы логически отделена, так как это следующее состояние страницы
        page.submitForm();
        assertThat(page.isFormSubmittedMessageDisplayed())
                .as("После нажатия Submit должно отображаться подтверждение отправки")
                .isTrue();
    }

    @Test
    @Story("Специальные элементы управления (Правая колонка)")
    @DisplayName("Проверка ColorPicker, DatePicker и RangeSlider")
    void shouldHandleColorDateAndSliders() {
        WebFormPage page = new WebFormPage().open(appProperties.ui().baseUrl());

        page.setColorViaJs("#ff0000");
        assertThat(page.getColorPickerValue()).isEqualTo("#ff0000");

        page.setDate("10/25/2025");
        assertThat(page.getDatePickerValue()).isEqualTo("10/25/2025");

        String initialValue = page.getRangeSliderValue();
        page.adjustRangeSlider(Keys.ARROW_RIGHT, 3);
        assertThat(page.getRangeSliderValue())
                .as("Значение слайдера должно увеличиться")
                .isNotEqualTo(initialValue);
    }
}