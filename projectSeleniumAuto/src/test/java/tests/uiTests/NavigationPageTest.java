package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.MainPage;
import ui.pages.NavigationPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Пагинация и навигация")
public class NavigationPageTest extends BaseUiTest {

    @Test
    @Story("Переход по страницам пагинации и проверка блокировок кнопок")
    @DisplayName("Проверка перехода между страницами 1, 2, 3 и состояния кнопок Previous/Next")
    void shouldNavigateThroughPagesAndValidateControls() {
        NavigationPage page = new NavigationPage().open(appProperties.ui().baseUrl());

        assertThat(page.getTitleHeaderText()).isEqualTo("Navigation example");
        assertThat(page.getLeadText()).contains("Lorem ipsum");
        assertThat(page.getBackToIndexHref()).isEqualTo("index.html");

        // Проверяем, что на 1-й странице 'Previous' отключен
        assertThat(page.getPagination().isPageDisabled("Previous")).isTrue();

        // Переходим на 3-ю страницу
        page.getPagination().clickPageNumber(3);

        // На 3-й странице 'Next' отключен, а 'Previous' активен
        assertThat(page.getPagination().isPageDisabled("Next")).isTrue();
        assertThat(page.getPagination().isPageDisabled("Previous")).isFalse();

        MainPage mainPage = page.returnToMain();
        assertThat(mainPage.getChaptersCount()).isEqualTo(6);
    }
}