package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

public class RandomCalculatorPage extends BasePage<RandomCalculatorPage> {

    private final By screen = By.className("screen");

    @Override
    protected String getPagePath() {
        return "/random-calculator";
    }

    @Step("Нажать клавишу '{key}'")
    public RandomCalculatorPage pressKey(char key) {
        By keyLocator = By.xpath("//span[contains(@class, 'btn') and normalize-space()='%s']".formatted(key));
        click(keyLocator);
        return this;
    }

    @Step("Сложить числа {a} и {b}")
    public RandomCalculatorPage add(int a, int b) {
        pressKey(Character.forDigit(a, 10));
        pressKey('+');
        pressKey(Character.forDigit(b, 10));
        pressKey('=');
        return this;
    }

    @Step("Дождаться отображения результата вычисления на дисплее")
    public String waitForAnyResult() {
        return wait.withTimeout(Duration.ofSeconds(15))
                .until(d -> {
                    String text = d.findElement(screen).getText().trim();
                    return !text.isEmpty() ? text : null;
                });
    }
}