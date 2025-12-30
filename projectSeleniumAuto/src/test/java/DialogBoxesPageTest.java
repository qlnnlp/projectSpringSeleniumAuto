import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DialogBoxesPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class DialogBoxesPageTest {

    @Inject
    DialogBoxesPage dialogBoxesPage;

    @Test
    void dialogBoxesTest() {
        dialogBoxesPage.open();

        dialogBoxesPage.launchAlert();
        assertThat(dialogBoxesPage.getDBoxText())
                .isEqualTo("Hello world!");
        dialogBoxesPage.acceptDBox();

        dialogBoxesPage.launchConfirm();
        assertThat(dialogBoxesPage.getDBoxText())
                .isEqualTo("Is this correct?");
        dialogBoxesPage.dismissDBox();

        dialogBoxesPage.launchPrompt();
        dialogBoxesPage.sendKeysToDBox("IOV");
        assertThat(dialogBoxesPage.getDBoxText())
                .isEqualTo("Please enter your name");
        dialogBoxesPage.acceptDBox();
        assertThat(dialogBoxesPage.getTypedText())
                .contains("IOV");

        dialogBoxesPage.launchModal();
        assertThat(dialogBoxesPage.getTextFromModalBody())
                .containsIgnoringWhitespaces("body");
        dialogBoxesPage.pressSaveChangesModal();
        assertThat(dialogBoxesPage.getTextResultFromModal())
                .containsIgnoringCase("Save");

    }
}
