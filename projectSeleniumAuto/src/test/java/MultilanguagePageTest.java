import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MultilanguagePage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class MultilanguagePageTest {


    @Inject
    MultilanguagePage multilanguagePage;

    @Test
    void multiLanguageTest() {
        multilanguagePage.open();

        assertThat(multilanguagePage.isBundleInBodyText())
                .isTrue();
    }
}
