package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.IFramePage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Встроенные фреймы (IFrames)")
public class IFramePageTest extends BaseUiTest {

    @Test
    @DisplayName("Проверка чтения параграфов из IFrame")
    void shouldReadContentInsideIFrame() {
        IFramePage page = new IFramePage().open(appProperties.ui().baseUrl());

        List<String> textList = page.getParagraphsFromIFrame();
        assertThat(textList).hasSize(20);
        assertThat(textList.get(0)).startsWithIgnoringCase("Lorem ipsum");
    }
}