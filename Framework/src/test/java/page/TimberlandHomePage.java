package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TimberlandHomePage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://timberland.com/";

    @FindBy(xpath = "//span[@class=\"icon-search hide-for-small-only\"]")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@aria-label=\"Search\"]")
    private WebElement searchField;

    public TimberlandHomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public TimberlandHomePage openPage(){
        driver.navigate().to(HOMEPAGE_URL);
        waitUntilAjaxCompleted();

        return this;
    }

    public TimberlandSearchResult search(String request){
        waitUntilElementIsClickableAndClickAvoidModalWindow(searchButton);
        waitUntilVisibilityOf(searchField).sendKeys(request);
        searchField.sendKeys(Keys.ENTER);

        return new TimberlandSearchResult(driver);
    }
}
