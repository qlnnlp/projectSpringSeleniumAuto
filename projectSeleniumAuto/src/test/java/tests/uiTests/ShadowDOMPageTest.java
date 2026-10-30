package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.ShadowDOMPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Изоляция элементов (Shadow DOM)")
public class ShadowDOMPageTest extends BaseUiTest {

    @Test
    @DisplayName("Чтение текста изнутри Shadow Root")
    void shouldReadTextInsideShadowRoot() {
        ShadowDOMPage page = new ShadowDOMPage().open(appProperties.ui().baseUrl());
        assertThat(page.getShadowParagraphText()).isEqualTo("Hello Shadow DOM");
    }
}