import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.LongPage;

@ExtendWith(UIExtensions.class)
public class LongPageTest {

    @Inject
    LongPage longPage;

    @Test
    void longPageTest() {
        longPage.open()
                .executeScrollScript();
        longPage.scrollToTheParagraph(longPage.firstParagraph);
        longPage.scrollToTheParagraph(longPage.lastParagraph);
    }

}
