package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.MouseOverPage;
import ui.pages.MouseOverPage.ImageCard;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("События мыши (Hover / MouseOver)")
public class MouseOverPageTest extends BaseUiTest {

    @Test
    @Story("Отображение подписей к изображениям при наведении")
    @DisplayName("Проверка появления всплывающего текста при hover-эффекте для всех 4 карточек")
    void shouldDisplayCaptionsOnMouseHover() {
        MouseOverPage page = new MouseOverPage().open(appProperties.ui().baseUrl());

        page.hoverOver(ImageCard.COMPASS);
        assertThat(page.getCaptionText(ImageCard.COMPASS)).isEqualTo("Compass");

        page.hoverOver(ImageCard.AWARD);
        assertThat(page.getCaptionText(ImageCard.AWARD)).isEqualTo("Award");

        page.hoverOver(ImageCard.CALENDAR);
        assertThat(page.getCaptionText(ImageCard.CALENDAR)).isEqualTo("Calendar");

        page.hoverOver(ImageCard.LANDSCAPE);
        assertThat(page.getCaptionText(ImageCard.LANDSCAPE)).isEqualTo("Landscape");
    }
}