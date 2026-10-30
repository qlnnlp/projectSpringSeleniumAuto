package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MultilanguagePage extends BasePage<MultilanguagePage> {

    private final By bodyContainer = By.tagName("body");

    @Override
    protected String getPagePath() {
        return "/multilanguage";
    }

    @Step("Получить весь видимый текст страницы")
    public String getPageBodyText() {
        return getText(bodyContainer);
    }

    @Step("Проверить наличие локализованных строк для локали {locale}")
    public boolean containsAllStringsForLocale(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("strings", locale);
        String bodyText = getPageBodyText();

        List<String> bundleValues = Collections.list(bundle.getKeys()).stream()
                .map(bundle::getString)
                .toList();

        return bundleValues.stream().allMatch(bodyText::contains);
    }
}