package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.GeolocationPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Геолокация")
public class GeolocationPageTest extends BaseUiTest {

    @Test
    @DisplayName("Получение координат геолокации")
    void shouldFetchGeolocationCoordinates() {
        GeolocationPage page = new GeolocationPage().open(appProperties.ui().baseUrl());
        page.requestCoordinates();

        assertThat(page.getCoordinatesText())
                .as("Блок координат должен вернуть статус или значения")
                .isNotBlank();
    }
}