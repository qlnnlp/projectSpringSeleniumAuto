import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MouseOverPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class MouseOverPageTest {

    @Inject
    MouseOverPage mouseOverPage;

    @Test
    void mouseOverTest() {

        mouseOverPage.open()
                .moveToImg(mouseOverPage.imgCompass);
        assertThat(mouseOverPage.getCaption("Compass"))
                .isEqualTo("Compass");

        mouseOverPage.moveToImg(mouseOverPage.imgAward);
        assertThat(mouseOverPage.getCaption("Award"))
                .isEqualTo("Award");

        mouseOverPage.moveToImg(mouseOverPage.imgCalendar);
        assertThat(mouseOverPage.getCaption("Calendar"))
                .isEqualTo("Calendar");

        mouseOverPage.moveToImg(mouseOverPage.imgLandscape);
        assertThat(mouseOverPage.getCaption("Landscape"))
                .isEqualTo("Landscape");
    }
}
