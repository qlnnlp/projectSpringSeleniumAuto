package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.LongPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Прокрутка страниц (Page Scrolling)")
public class LongPageTest extends BaseUiTest {

    @Test
    @Story("Скролл длинной страницы к элементам")
    @DisplayName("Проверка прокрутки страницы к нижним элементам")
    void shouldScrollToBottomParagraphAndVerifyScrollPosition() {
        LongPage page = new LongPage().open(appProperties.ui().baseUrl());

        assertThat(page.getScrollY()).as("В начале страницы позиция скролла равна 0").isZero();

        page.scrollToLastParagraph();

        assertThat(page.getScrollY())
                .as("После вызова scrollToLastParagraph вертикальный скролл страницы должен увеличиться")
                .isGreaterThan(0);
    }
}