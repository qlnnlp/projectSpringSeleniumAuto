package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.LoadingImagesPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Асинхронная загрузка изображений")
public class LoadingImagesPageTest extends BaseUiTest {

    @Test
    @DisplayName("Ожидание и проверка асинхронно подгруженных картинок")
    void shouldWaitForImagesToLoad() {
        LoadingImagesPage page = new LoadingImagesPage().open(appProperties.ui().baseUrl());

        assertThat(page.getLoadedImageSrc("compass")).isEqualTo("img/compass.png");
        assertThat(page.getLoadedImageSrc("calendar")).isEqualTo("img/calendar.png");
        assertThat(page.getLoadedImageSrc("award")).isEqualTo("img/award.png");
        assertThat(page.getLoadedImageSrc("landscape")).isEqualTo("img/landscape.png");
    }
}