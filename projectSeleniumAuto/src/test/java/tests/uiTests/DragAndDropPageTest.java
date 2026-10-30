package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.DragAndDropPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Сложные пользовательские взаимодействия")
public class DragAndDropPageTest extends BaseUiTest {

    @Test
    @Story("Перетаскивание элементов (Drag and Drop)")
    @DisplayName("Проверка смещения элемента по координатам и перемещения в целевой блок")
    void shouldDragAndDropByOffsetAndToTarget() {
        DragAndDropPage page = new DragAndDropPage().open(appProperties.ui().baseUrl());

        Point initialLocation = page.getDraggableLocation();

        // Смещение элемента вправо и вниз, затем возврат в исходную точку
        page.dragByOffset(100, 0);
        assertThat(page.getDraggableLocation())
                .as("Координаты должны измениться после первого смещения")
                .isNotEqualTo(initialLocation);

        page.dragByOffset(0, 100)
                .dragByOffset(-100, 0)
                .dragByOffset(0, -100);

        assertThat(page.getDraggableLocation())
                .as("Элемент должен вернуться в исходные координаты")
                .isEqualTo(initialLocation);

        // Перетаскивание напрямую в целевую панель
        page.dragToTarget();
        assertThat(page.getDraggableLocation())
                .as("Элемент должен находиться в координатах целевой панели")
                .isEqualTo(page.getTargetLocation());
    }
}