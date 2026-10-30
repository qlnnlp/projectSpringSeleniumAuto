package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.SlowCalculatorPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Медленный калькулятор (Slow Calculator)")
public class SlowCalculatorPageTest extends BaseUiTest {

    @Test
    @DisplayName("Выполнение математических операций с задержкой")
    void shouldPerformCalculationsWithDelay() {
        SlowCalculatorPage page = new SlowCalculatorPage().open(appProperties.ui().baseUrl());

        page.calculate("1+3");
        assertThat(page.waitForResult(4)).isEqualTo(4);

        page.clear().calculate("9*5");
        assertThat(page.waitForResult(45)).isEqualTo(45);

        page.clear().calculate("8/4");
        assertThat(page.waitForResult(2)).isEqualTo(2);
    }
}