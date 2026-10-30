package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.RandomCalculatorPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Рандомный калькулятор (Random Calculator)")
public class RandomCalculatorPageTest extends BaseUiTest {

    @Test
    @DisplayName("Проверка выполнения вычисления на рандомном калькуляторе")
    void shouldAddNumbersOnRandomCalculator() {
        RandomCalculatorPage page = new RandomCalculatorPage().open(appProperties.ui().baseUrl());
        page.add(1, 2);

        String result = page.waitForAnyResult();
        assertThat(result)
                .as("На экране дисплея должен появиться результат вычисления")
                .isNotBlank();
    }
}