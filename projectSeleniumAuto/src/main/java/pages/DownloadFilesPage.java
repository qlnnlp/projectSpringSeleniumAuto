package pages;

import annotations.Path;
import org.apache.commons.io.FileUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Path("/download")
public class DownloadFilesPage extends AbsBasePage<DownloadFilesPage> {

    public DownloadFilesPage(WebDriver driver) {
        super(driver);
    }

    // Selectors and methods

    @FindBy(xpath = "(//a)[2]")
   public WebElement webDLogo;

    @FindBy(xpath = "(//a)[3]")
   public WebElement webDPdf;

    @FindBy(xpath = "(//a)[4]")
   public WebElement seleniumJLogo;

    @FindBy(xpath = "(//a)[5]")
   public WebElement seleniumJPdf;

    public void downloadFiles(String link, File destination) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(link);

            client.execute(request, response -> {
                if (response.getEntity() != null) {
                    FileUtils.copyInputStreamToFile(
                            response.getEntity().getContent(), destination);
                }
                return null;
            });
        }
    }

    public void assertDownload(WebElement element, String fileName) throws IOException {
        File file = new File(".", fileName);
        try {
            String url = element.getAttribute("href");
            downloadFiles(url, file);
            assertThat(file).exists();
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}




