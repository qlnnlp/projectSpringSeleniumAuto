package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.MultilanguagePage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Интернационализация (i18n)")
public class MultilanguagePageTest extends BaseUiTest {

    @Test
    @Story("Проверка отображения локализованного текста")
    @DisplayName("Проверка соответствия текста страницы локализационному бандлу es-ES")
    void shouldDisplayLocalizedSpanishStrings() {
        MultilanguagePage page = new MultilanguagePage().open(appProperties.ui().baseUrl());

        assertThat(page.containsAllStringsForLocale(Locale.forLanguageTag("es-ES")))
                .as("Страница должна содержать перевод ключевых элементов на испанский язык")
                .isTrue();
    }
}