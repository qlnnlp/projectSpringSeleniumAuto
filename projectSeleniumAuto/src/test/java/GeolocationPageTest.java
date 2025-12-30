
import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.GeolocationPage;

@ExtendWith(UIExtensions.class)
public class GeolocationPageTest {


    @Inject
    GeolocationPage geolocationPage;


    @Test
    void geolocationTest() {
        geolocationPage.open()
                .clickCoordinatesB();

       var location = geolocationPage.getCoordinates(3);
        System.out.println(location);

    }
}


