package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class SlowCalculatorPage extends BasePage<SlowCalculatorPage> {

    private final By screen = By.className("screen");

    @Override
    protected String getPagePath() {
        return "/slow-calculator";
    }

    @Step("Нажать клавишу '{key}' на калькуляторе")
    public SlowCalculatorPage pressKey(char key) {
        String keyText = switch (key) {
            case '/' -> "÷";
            case '*' -> "x";
            default -> String.valueOf(key);
        };
        By keyLocator = By.xpath("//span[contains(@class, 'btn') and normalize-space()='%s']".formatted(keyText));
        click(keyLocator);
        return this;
    }

    @Step("Выполнить вычисление: {expression}")
    public SlowCalculatorPage calculate(String expression) {
        for (char ch : expression.toCharArray()) {
            pressKey(ch);
        }
        pressKey('=');
        return this;
    }

    @Step("Сбросить калькулятор (Clear)")
    public SlowCalculatorPage clear() {
        pressKey('C');
        return this;
    }

    @Step("Дождаться результата '{expectedValue}' на дисплее")
    public int waitForResult(int expectedValue) {
        wait.withTimeout(Duration.ofSeconds(12))
                .until(ExpectedConditions.textToBePresentInElementLocated(screen, String.valueOf(expectedValue)));
        return Integer.parseInt(getText(screen));
    }
}