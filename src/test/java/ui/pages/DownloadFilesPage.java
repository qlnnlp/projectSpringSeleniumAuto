package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class DownloadFilesPage extends BasePage<DownloadFilesPage> {

    @Override
    protected String getPagePath() {
        return "/download";
    }

    public String getFileHref(String hrefSubstring) {
        // getAttribute("href") возвращает полный абсолютный URL вместе с https://
        return find(By.cssSelector("a[href*='" + hrefSubstring + "']")).getAttribute("href");
    }

    @Step("Скачать файл по URL '{fileUrl}' в путь '{destination}'")
    public Path downloadFileViaHttp(String fileUrl, Path destination) throws IOException, InterruptedException {
        String absoluteUrl = fileUrl.startsWith("http")
                ? fileUrl
                : driver.getCurrentUrl().replaceAll("/[^/]*$", "/") + fileUrl;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(absoluteUrl)).build();
        client.send(request, HttpResponse.BodyHandlers.ofFile(destination));
        return destination;
    }
}