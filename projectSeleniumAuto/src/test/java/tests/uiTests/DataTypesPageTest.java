package tests.uiTests;

import models.ui.UserDataForm;
import tests.baseTests.BaseUiTest;
import ui.pages.DataTypesPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Валидация типов данных и заполнение формы")
public class DataTypesPageTest extends BaseUiTest {

    @Test
    @Story("Заполнение формы сгенерированным DTO объектом")
    @DisplayName("Успешная отправка формы с 10 полями и проверка 10 зеленых алертов")
    void shouldFillAllFieldsUsingDtoAndSubmitSuccessfully() {
        UserDataForm formData = UserDataForm.generateRandom();

        DataTypesPage page = new DataTypesPage()
                .open(appProperties.ui().baseUrl());

        assertThat(page.getSuccessAlertsCount())
                .as("До отправки формы алерты отсутствуют")
                .isZero();

        page.fillForm(formData).submit();

        assertThat(page.getSuccessAlertsCount())
                .as("После отправки все 10 полей должны быть помечены как успешные (alert-success)")
                .isEqualTo(10);
    }
}