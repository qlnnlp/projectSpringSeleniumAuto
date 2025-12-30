import com.github.javafaker.Faker;
import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DataTypesPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class DataTypesPageTest {

    @Inject
    DataTypesPage dataTypesPage;

    @Test
    void dataTypesTest() {
        dataTypesPage.open();
        Faker faker = new Faker();

        dataTypesPage.sendFake
                (dataTypesPage.inputFName,
                        () -> faker.name().firstName());

        dataTypesPage.sendFake
                (dataTypesPage.inputLName,
                        () -> faker.name().lastName());

        dataTypesPage.sendFake
                (dataTypesPage.inputAddress,
                        () -> faker.address().fullAddress());

        dataTypesPage.sendFake
                (dataTypesPage.inputZipCode,
                () -> faker.address().zipCode());

        dataTypesPage.sendFake
                (dataTypesPage.inputCity,
                        () -> faker.address().city());

        dataTypesPage.sendFake
                (dataTypesPage.inputCountry,
                        () -> faker.country().name());

        dataTypesPage.sendFake
                (dataTypesPage.inputEmail,
                        () -> faker.internet().emailAddress()
        );

        dataTypesPage.sendFake
                (dataTypesPage.inputPhoneNumber,
                        () -> faker.phoneNumber().phoneNumber());

        dataTypesPage.sendFake
                (dataTypesPage.inputJobPosition,
                        () -> faker.job().position());

        dataTypesPage.sendFake
                (dataTypesPage.inputCompany,
                        () -> faker.company().name());

        assertThat(dataTypesPage.getSuccessAlertElements())
                .isEmpty();

        dataTypesPage.submitClick();
        assertThat(dataTypesPage.getSuccessAlertElements())
                .hasSize(10);
    }
}
