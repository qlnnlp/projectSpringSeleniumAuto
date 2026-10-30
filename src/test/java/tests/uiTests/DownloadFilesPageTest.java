package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.DownloadFilesPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Скачивание файлов")
public class DownloadFilesPageTest extends BaseUiTest {

    @Test
    @DisplayName("Проверка ссылок и скачивание файлов через HTTP во временную директорию")
    void shouldVerifyFileDownloads(@TempDir Path tempDir) throws IOException, InterruptedException {
        DownloadFilesPage page = new DownloadFilesPage().open(appProperties.ui().baseUrl());

        String pdfUrl = page.getFileHref("webdrivermanager.pdf");
        assertThat(pdfUrl).endsWith(".pdf");

        Path downloadedFile = tempDir.resolve("downloaded.pdf");
        page.downloadFileViaHttp(pdfUrl, downloadedFile);

        assertThat(Files.exists(downloadedFile)).isTrue();
        assertThat(Files.size(downloadedFile)).isGreaterThan(0);
    }
}