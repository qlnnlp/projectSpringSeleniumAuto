import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.IFramePage;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(UIExtensions.class)
public class IFramePageTest {

    @Inject
    IFramePage iFramePage;

    @Test
    void iFramePageTest() {
        iFramePage.open()
                .switchToIFrame(iFramePage.iframe);

        var paragraphs = iFramePage.getParagraphs();
        assertThat(paragraphs)
                .hasSize(20);

        var firstParagraph = iFramePage.getTextFromParagraph(0);
        assertThat(firstParagraph)
                .startsWithIgnoringCase("Lorem ipsum");

        var secondParagraph = iFramePage.getTextFromParagraph(1);
        assertThat(secondParagraph)
                .startsWithIgnoringCase("Nibh netus");
    }
}
