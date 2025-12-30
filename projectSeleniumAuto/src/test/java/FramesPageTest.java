import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.FramesPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class FramesPageTest {

    @Inject
    FramesPage framesPage;

    @Test
    void framesPageTest() {

        framesPage.open()
                .switchToFrame(framesPage.frame);

        var paragraphs = framesPage.getParagraphs();
        assertThat(paragraphs)
                .hasSize(20);

        var firstParagraph = framesPage.getTextFromParagraph(0);
        assertThat(firstParagraph)
                .startsWithIgnoringCase("Lorem ipsum");

        var secondParagraph = framesPage.getTextFromParagraph(1);
        assertThat(secondParagraph)
                .startsWithIgnoringCase("Nibh netus");

    }

}
