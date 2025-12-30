import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SlowCalculatorPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class SlowCalculatorPageTest {

    @Inject
    SlowCalculatorPage slowCalculatorPage;

    @Test
    void SlowCalculatorTest() {
        slowCalculatorPage.open();

        slowCalculatorPage.performSimpleOperationInt(1,3,  slowCalculatorPage::pressPlus);
        assertThat(slowCalculatorPage.getScreenNumber(4))
                .isEqualTo(4);

        slowCalculatorPage.pressClear();

        slowCalculatorPage.performSimpleOperationInt(9,5, slowCalculatorPage::pressMultiply);
        assertThat(slowCalculatorPage.getScreenNumber(45))
                .isEqualTo(45);

        slowCalculatorPage.pressClear();

        slowCalculatorPage.performSimpleOperationInt(8,4, slowCalculatorPage::pressDivide);
        assertThat(slowCalculatorPage.getScreenNumber(2))
                .isEqualTo(2);

        slowCalculatorPage.performSimpleOperationInt(1,1, slowCalculatorPage::pressMinus);
        assertThat(slowCalculatorPage.getScreenNumber(20))
                .isEqualTo(20);


    }
}
