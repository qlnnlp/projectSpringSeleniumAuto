package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.FramesPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Фреймы (Frames)")
public class FramesPageTest extends BaseUiTest {

    @Test
    @DisplayName("Проверка чтения параграфов из Frame")
    void shouldReadContentInsideFrame() {
        FramesPage page = new FramesPage().open(appProperties.ui().baseUrl());

        List<String> textList = page.getParagraphsFromFrame();
        assertThat(textList).hasSize(20);
        assertThat(textList.get(0)).startsWithIgnoringCase("Lorem ipsum");
        assertThat(textList.get(1)).startsWithIgnoringCase("Nibh netus");
    }
}