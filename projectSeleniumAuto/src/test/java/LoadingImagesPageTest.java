import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.LoadingImagesPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class LoadingImagesPageTest {

    @Inject
    LoadingImagesPage loadingImagesPage;

    @Test
    void loadingImagesTest() {
        loadingImagesPage.open();

        var compassSrc = loadingImagesPage.getImgSrc(loadingImagesPage.compass);
        var calendarSrc = loadingImagesPage.getImgSrc(loadingImagesPage.calendar);
        var awardSrc = loadingImagesPage.getImgSrc(loadingImagesPage.award);
        var landscapeSrc = loadingImagesPage.getImgSrc(loadingImagesPage.landscape);

        assertThat(compassSrc)
                .isEqualTo("img/compass.png");
        assertThat(calendarSrc)
                .isEqualTo("img/calendar.png");
        assertThat(awardSrc)
                .isEqualTo("img/award.png");
        assertThat(landscapeSrc)
                .isEqualTo("img/landscape.png");
    }
}
