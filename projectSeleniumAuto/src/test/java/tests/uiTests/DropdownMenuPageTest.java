package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.DropdownMenuPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Взаимодействие с выпадающими меню")
public class DropdownMenuPageTest extends BaseUiTest {

    @Test
    @Story("Выбор пунктов через различные клики мыши")
    @DisplayName("Проверка работы обычного, контекстного (ПКМ) и двойного клика в меню")
    void shouldSelectItemsUsingDifferentMouseActions() {
        DropdownMenuPage page = new DropdownMenuPage().open(appProperties.ui().baseUrl());

        assertThat(page.areAllDropdownButtonsVisible())
                .as("Все три кнопки меню должны отображаться")
                .isTrue();

        page.selectFromLeftClickDropdown("Something else here")
                .selectFromContextMenu("Action")
                .selectFromContextMenu("Another action")
                .selectFromContextMenu("Separated link")
                .selectFromDoubleClickMenu("Action")
                .selectFromDoubleClickMenu("Something else here")
                .selectFromDoubleClickMenu("Separated link");
    }
}