package tests.uiTests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.baseTests.BaseUiTest;
import ui.pages.DialogBoxesPage;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Всплывающие окна и диалоги")
public class DialogBoxesPageTest extends BaseUiTest {

    @Test
    @Story("Нативные JS диалоги и модальные окна")
    @DisplayName("Проверка Alert, Confirm, Prompt и компонента Modal")
    void shouldHandleAllDialogBoxTypes() {
        DialogBoxesPage page = new DialogBoxesPage().open(appProperties.ui().baseUrl());

        // Alert
        String alertText = page.triggerAlertAndAccept();
        assertThat(alertText).isEqualTo("Hello world!");

        // Confirm
        String confirmText = page.triggerConfirmAndDismiss();
        assertThat(confirmText).isEqualTo("Is this correct?");

        // Prompt
        String promptHeader = page.triggerPromptAndAccept("QA Architect");
        assertThat(promptHeader).isEqualTo("Please enter your name");
        assertThat(page.getPromptResultText()).contains("QA Architect");

        // Modal Component
        page.openModal();
        assertThat(page.getModal().isModalOpen()).isTrue();
        assertThat(page.getModal().getBodyText()).containsIgnoringCase("modal body");
        page.getModal().saveChanges();
        assertThat(page.getModalResultText()).containsIgnoringCase("Save");
    }
}