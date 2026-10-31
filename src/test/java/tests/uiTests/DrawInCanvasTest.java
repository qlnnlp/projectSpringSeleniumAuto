package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.DrawInCanvasPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Feature("Взаимодействие с HTML5 Canvas")
public class DrawInCanvasTest extends BaseUiTest {

    @Test
    @Story("Рисование фигур на Canvas")
    @DisplayName("Проверка отрисовки линий, окружностей и обработка выхода курсора за границы холста")
    void shouldDrawShapesAndThrowExceptionWhenOutOfBounds() {
        DrawInCanvasPage page = new DrawInCanvasPage().open(appProperties.ui().baseUrl());

        // Позитивный сценарий: рисование геометрических фигур
        assertThatCode(() -> page.drawCircle(30, 10).drawLine(5, 10, 10))
                .as("Рисование в границах Canvas не должно приводить к ошибкам")
                .doesNotThrowAnyException();

        // Негативный сценарий: гарантированный выход координат курсора за верхний левый угол вьюпорта (< 0)
        assertThatThrownBy(() -> page.drawLine(1, -3000, -3000))
                .as("Движение курсора за пределы вьюпорта браузера должно вызывать MoveTargetOutOfBoundsException")
                .isInstanceOf(MoveTargetOutOfBoundsException.class);
    }
}