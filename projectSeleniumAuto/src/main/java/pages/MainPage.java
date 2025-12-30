package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    //selectors

@FindBy(css = "div.col.col-10 > h5")
public WebElement practiceSite;

    @FindBy(className = "img-fluid")
    public WebElement imgFluid;

    @FindBy(css = " div.col.col-2 > a")
    public WebElement imgFluidLink;

    @FindBy(className = "lead")
    public WebElement leadInfoWithHTexts;
    public String leadText = "This site contains a collection of sample web pages to be tested with Selenium WebDriver. Check out the O'Reilly book and the source code on GitHub.";

    @FindBy(css = "div > p > a:nth-child(2)")
    public WebElement leadBookLink;
    public String leadBookValue = "https://www.oreilly.com/library/view/hands-on-selenium-webdriver/9781098109998/";

    @FindBy(css = "div > p > a:nth-child(3)")
    public WebElement leadGitHubLink;
    public String leadGitHubValue = "https://github.com/bonigarcia/selenium-webdriver-java";

    @FindBy(xpath = "//div/div[4]")
    public WebElement upperRowCards;

    @FindBy(xpath = "//div/div[5]")
    public WebElement lowerRowCards;

    @FindBy(css = "div>div:nth-child(4)>div:nth-child(1)")
    public WebElement chapter3Card;
    public String chapter3CardText = "Chapter 3. WebDriver Fundamentals\n" +
            "Web form Navigation Dropdown menu Mouse over Drag and drop Draw in canvas Loading images Slow calculator";

    @FindBy(css = "div>div:nth-child(4)>div:nth-child(2)")
    public WebElement chapter4Card;
    public String chapter4CardText = "Chapter 4. Browser-Agnostic Features\n" +
            "Long page Infinite scroll Shadow DOM Cookies Frames IFrames Dialog boxes Web storage";

    @FindBy(css = "div>div:nth-child(4)>div:nth-child(3)")
    public WebElement chapter5Card;
    public String chapter5CardText = "Chapter 5. Browser-Specific Manipulation\n" +
            "Geolocation Notifications Get user media Multilanguage Console logs";

    @FindBy(css = "div>div:nth-child(5)>div:nth-child(1)")
    public WebElement chapter7Card;
    public String chapter7CardText = "Chapter 7. The Page Object Model (POM)\n" +
    "Login form Slow login";

    @FindBy(css = "div>div:nth-child(5)>div:nth-child(2)")
    public WebElement chapter8Card;
    public String chapter8CardText = "Chapter 8. Testing Framework Specifics\n" +
    "Random calculator";

    @FindBy(css = "div>div:nth-child(5)>div:nth-child(3)")
    public WebElement chapter9Card;
    public String chapter9CardText = "Chapter 9. Third-Party Integrations\n" +
    "Download files A/B Testing Data types";

    @FindBy(css = "body > footer")
    public WebElement footer;
    public String footerLink = "https://bonigarcia.dev/";
    public String footerCopyRight = "Copyright © 2021-2025 Boni García";


    // chapter 3 links

    @FindBy(linkText = "Web form")
    public WebElement webForm;

    @FindBy(linkText = "Navigation")
    public WebElement navigation;

    @FindBy(linkText = "Dropdown menu")
    public WebElement dropDownMenu;

    @FindBy(linkText = "Mouse over")
    public WebElement mouseOver;

    @FindBy(linkText = "Drag and rop")
    public WebElement dragAndDrop;

    @FindBy(linkText = "Draw in canvas")
    public WebElement drawInCanvas;

    @FindBy(linkText = "Loading images")
    public WebElement loadingImages;

    @FindBy(linkText = "Slow calculator")
    public WebElement slowCalculator;



}
