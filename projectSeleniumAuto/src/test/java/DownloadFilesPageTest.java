import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import pages.DownloadFilesPage;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class DownloadFilesPageTest {


    @Inject
    DownloadFilesPage downloadFilesPage;

    @Test
    void downloadFilesTest() throws IOException {
        downloadFilesPage.open();

        downloadFilesPage.assertDownload
                (downloadFilesPage.webDLogo, "webdrivermanager.png");
        downloadFilesPage.assertDownload
                (downloadFilesPage.webDPdf, "webdrivermanager.pdf");
        downloadFilesPage.assertDownload
                (downloadFilesPage.seleniumJLogo, "s.png");
    }

    @Test
    void downloadFilesTempDirTest(@TempDir Path tempDir) throws IOException {
        downloadFilesPage.open();

        var seleniumJPdfFile = tempDir.resolve("seleniumJ.pdf").toFile();
        var fullUrl = downloadFilesPage.seleniumJPdf.getAttribute("href");
        downloadFilesPage.downloadFiles(fullUrl, seleniumJPdfFile);

        assertThat(seleniumJPdfFile)
                .exists();
    }
}
