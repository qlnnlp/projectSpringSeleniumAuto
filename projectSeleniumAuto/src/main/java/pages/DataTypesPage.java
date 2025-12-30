package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;


@Path("/data-types")
public class DataTypesPage extends AbsBasePage<DataTypesPage>{

    public DataTypesPage(WebDriver driver) {
        super(driver);
    }

    // Selectors and methods

    @FindBy(css = "[name = first-name]")
   public WebElement inputFName;

    @FindBy(css = "[name = last-name]")
   public WebElement inputLName;

    @FindBy(css = "[name = address]")
    public WebElement inputAddress;

    @FindBy(css = "[name = zip-code]")
    public WebElement inputZipCode;

    @FindBy(css = "[name = city]")
    public WebElement inputCity;

    @FindBy(css = "[name = country]")
    public WebElement inputCountry;

    @FindBy(css = "[name = e-mail]")
    public WebElement inputEmail;

    @FindBy(css = "[name = phone]")
    public WebElement inputPhoneNumber;

    @FindBy(css = "[name = job-position]")
    public WebElement inputJobPosition;

    @FindBy(css = "[name = company]")
    public WebElement inputCompany;

    @FindBy(css = "[type = submit]")
    private WebElement submitB;





    public void sendFake(WebElement element, Supplier<String> fakeData) {
     String data = fakeData.get();
       type(element, data);
       assertThat(element.getDomProperty("value"))
               .isNotBlank();
    }

    public List<WebElement> getSuccessAlertElements() {
        return driver.findElements(By.className("alert-success"));
    }

    public void submitClick() {
        click(submitB);
    }
}
