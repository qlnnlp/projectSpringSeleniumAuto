package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.GetUserMediaPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("WebRTC и захват медиа-устройств")
public class UserMediaPageTest extends BaseUiTest {

    @Test
    @DisplayName("Захват виртуального видеоустройства")
    void shouldCaptureVideoDevice() {
        GetUserMediaPage page = new GetUserMediaPage().open(appProperties.ui().baseUrl());
        page.startMediaStream();

        assertThat(page.getDeviceName())
                .as("Должно определиться эмулированное видеоустройство")
                .contains("fake_device_0");
    }
}