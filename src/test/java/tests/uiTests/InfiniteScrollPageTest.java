package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.InfiniteScrollPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Динамическая подгрузка контента (Infinite Scroll)")
public class InfiniteScrollPageTest extends BaseUiTest {

    @Test
    @Story("Подгрузка новых параграфов при скролле")
    @DisplayName("Проверка увеличения количества элементов после последовательной прокрутки")
    void shouldLoadMoreParagraphsOnScroll() {
        InfiniteScrollPage page = new InfiniteScrollPage().open(appProperties.ui().baseUrl());

        int initialCount = page.getParagraphsCount();

        page.scrollDown(5);

        assertThat(page.getParagraphsCount())
                .as("Количество параграфов после 5 итераций скролла должно существенно возрасти")
                .isGreaterThan(initialCount);
    }
}