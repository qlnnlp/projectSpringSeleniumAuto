import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DropdownMenuPage;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(UIExtensions.class)
public class DropdownMenuPageTest {

    @Inject
   DropdownMenuPage dropdownMenuPage;

    @Test
    void dropdownMenuTest() {

        dropdownMenuPage.open();

        assertThat(dropdownMenuPage.dropdownLCMenu.isDisplayed())
                .isTrue();

        assertThat(dropdownMenuPage.dropdownRCMenu.isDisplayed())
                .isTrue();

        assertThat(dropdownMenuPage.dropdownDoubleCMenu.isDisplayed())
                .isTrue();

        dropdownMenuPage.selectDrDownItem("left", dropdownMenuPage.dropdownLCMenu, "Something else here");

        dropdownMenuPage.selectDrDownItem("right", dropdownMenuPage.dropdownRCMenu, "Action");
        dropdownMenuPage.selectDrDownItem("right", dropdownMenuPage.dropdownRCMenu, "Another action");
        dropdownMenuPage.selectDrDownItem("right", dropdownMenuPage.dropdownRCMenu, "Something else here");
        dropdownMenuPage.selectDrDownItem("right", dropdownMenuPage.dropdownRCMenu, "Separated link");
        dropdownMenuPage.selectDrDownItem("right", dropdownMenuPage.dropdownRCMenu, "Action");

        dropdownMenuPage.selectDrDownItem("double", dropdownMenuPage.dropdownDoubleCMenu, "Action");
        dropdownMenuPage.selectDrDownItem("double", dropdownMenuPage.dropdownDoubleCMenu, "Something else here");
        dropdownMenuPage.selectDrDownItem("double", dropdownMenuPage.dropdownDoubleCMenu, "Another action");
        dropdownMenuPage.selectDrDownItem("double", dropdownMenuPage.dropdownDoubleCMenu, "Separated link");
        dropdownMenuPage.selectDrDownItem("double", dropdownMenuPage.dropdownDoubleCMenu, "Action");

    }



}
