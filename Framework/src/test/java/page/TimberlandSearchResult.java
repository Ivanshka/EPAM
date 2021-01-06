package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Resolver;

import static util.Resolver.resolveTemplate;

public class TimberlandSearchResult extends AbstractPage {
    private static final String SIZE_TEMPLATE = "//select[@id=\"attr-size\"]/option[@value=\"%s\"]";
    private static final String COUNT_TEMPLATE = ".dk_options_inner > li:nth-child(%s)";

    @FindBy(xpath = "//a[text()=\"Add to Cart\"]")
    WebElement addToBagButton;

    @FindBy(xpath = "//div[@class=\"topnav-utility-cart-container hide-for-small-only\"]//span[@class=\"topnav" +
            "-cart-icon icon-shopcart\"]")
    WebElement openBagButton;

    @FindBy(xpath = "//*[contains(@id, \"dk_container_quantity_\")]")
    WebElement selectQuantity;

    @FindBy(xpath = "//div[@id='headerTopRight']/a[contains(@class,'searchButtonWrapper')]")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@id='SimpleSearchForm_SearchTerm']")
    private WebElement searchField;

    @Override
    protected TimberlandSearchResult openPage() {
        return this;
    }

    public TimberlandSearchResult(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public TimberlandSearchResult setSize(String size){
        waitUntilElementIsClickableAndClickAvoidModalWindow(By.xpath(Resolver.resolveTemplate(SIZE_TEMPLATE, size)));

        return this;
    }

    public TimberlandSearchResult addToCart() {
        waitUntilElementIsClickableAndClickAvoidModalWindow(addToBagButton);

        return this;
    }

    public TimberlandSearchResult setCountOfItems(String count) {
        waitUntilElementIsClickableAndClickAvoidModalWindow(selectQuantity);
        waitUntilElementIsClickable(By.cssSelector(resolveTemplate(COUNT_TEMPLATE, count))).click();

        return this;
    }
/*
    public TimberlandSearchResult setCountOfItems(int count) {
        waitUntilElementIsClickableAndClickAvoidModalWindow(selectQuantity);
        waitUntilElementIsClickable(By.cssSelector(resolveTemplate(COUNT_TEMPLATE, Integer.toString(count)))).click();

        return this;
    }
*/
    public TimberlandBagPage openCart(){
        waitUntilAjaxCompleted();
        waitUntilElementIsClickableAndClickAvoidModalWindow(openBagButton);

        return new TimberlandBagPage(driver);
    }

    public TimberlandSearchResult search(String request){
        waitUntilElementIsClickableAndClickAvoidModalWindow(searchButton);
        waitUntilVisibilityOf(searchField).sendKeys(request);
        searchField.sendKeys(Keys.ENTER);

        waitUntilAjaxCompleted();

        return new TimberlandSearchResult(driver);
    }
}