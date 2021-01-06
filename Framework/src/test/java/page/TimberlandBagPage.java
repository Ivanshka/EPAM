package page;

import model.Item;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import util.Resolver;

import java.util.List;

import static util.Resolver.resolveCost;
import static util.Resolver.resolveTemplate;

public class TimberlandBagPage extends AbstractPage {
    public static final String HOMEPAGE_URL = "https://timberland.com/";

    String itemNameTemplate = "//div[%s]/div[2]/div[1]/a/h3";
    String numberOfItemTemplate = "//div[contains(@class,'product_title')]/h3/*[contains(@id, \"OrderItemDetailsf_div_2_\")]";
    String itemDeleteTemplate = "//*[@id=\"WC_OrderItemDetailsf_links_2_%d\"]";
    String itemCostTemplate = "//div[%s]/div[2]/div[3]/p";
    String itemSizeTemplate = "//div[%s]/div[2]/div[1]/dl/dd[1]";
    String countOfItemTemplate = "//div[%s]/div[2]/div[2]/form/select";

    @FindBy(xpath = "//div[@id='headerTopRight']/a[contains(@class,'searchButtonWrapper')]")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@id='SimpleSearchForm_SearchTerm']")
    private WebElement searchField;

    @FindBy(id = "WC_SingleShipmentOrderTotalsSummary_td_2")
    private WebElement subtotalCost;

    @FindBy(id = "promoCodeRegion_Label")
    private WebElement promoCodeLabel;

    @FindBy(id = "promoCode")
    private WebElement promoCodeField;

    @FindBy(id = "WC_SingleShipmentOrderTotalsSummary_td_13")
    private WebElement estimatedTotalCost;

    @FindBy(id = "WC_SingleShipmentOrderTotalsSummary_td_8")
    private WebElement shippingCost;

    @FindBy(id = "guestShopperContinue")
    private WebElement proceedToSecureCheckout;

    @Override
    protected TimberlandBagPage openPage() {
        return this;
    }

    public TimberlandBagPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public TimberlandSearchResult search(String request){
        waitUntilElementIsClickableAndClickAvoidModalWindow(searchButton);
        waitUntilVisibilityOf(searchField).sendKeys(request);
        searchField.sendKeys(Keys.ENTER);

        return new TimberlandSearchResult(driver);
    }

    public Item getItem(int number) {
        number++;
        String itemName = waitUntilPresenceOfElement(By.xpath(resolveTemplate(itemNameTemplate, number))).getText();
        String itemCost = driver.findElements(By.xpath(resolveTemplate(itemCostTemplate, number))).get(0).getText();
        String itemSize = driver.findElements(By.xpath(resolveTemplate(itemSizeTemplate, number))).get(0).getText();
        WebElement itemCount = driver.findElement(By.xpath(resolveTemplate(countOfItemTemplate, number)));

        Select select = new Select(itemCount);

        int cost = Resolver.resolveCost(itemCost);
        int amount = Resolver.resolveInt(select.getFirstSelectedOption().getText());

        return Item.of(itemName, itemSize, cost, amount);
    }

    public TimberlandBagPage changeAmountOfItem(int itemNumber, int amountOfItem){
        itemNumber++;
        WebElement itemCount = waitUntilPresenceOfElement(By.xpath(resolveTemplate(countOfItemTemplate, itemNumber)));
        WebElement itemCost = waitUntilPresenceOfElement(By.xpath(resolveTemplate(itemCostTemplate, itemNumber)));
        WebElement needCount = waitUntilElementIsClickable(By.xpath(resolveTemplate(countOfItemTemplate, itemNumber) +
                resolveTemplate("/option[%s]", amountOfItem)));
        String costValue = itemCost.getText();
        itemCount.click();
        needCount.click();

        waitUntilFieldIsChanged(itemCost, costValue);
        return this;
    }

    public TimberlandBagPage enterPromoCode(String promoCode, int numberOfPromoItem){
        waitUntilVisibilityOf(promoCodeLabel).click();
        waitUntilVisibilityOf(promoCodeField).sendKeys(promoCode);
        WebElement itemCost = driver.findElements(By.xpath(resolveTemplate(itemCostTemplate, numberOfPromoItem)))
                .get(numberOfPromoItem - 1);

        String startValue = itemCost.getText();

        promoCodeField.sendKeys(Keys.ENTER);
        waitUntilFieldIsChanged(itemCost, startValue);

        return this;
    }

    public TimberlandBagPage removeItem(int number){
        WebElement removeButton = waitUntilPresenceOfElement(By.xpath(resolveTemplate(itemDeleteTemplate, number)));

        removeButton.click();

        return this;
    }

    /*
    public CalvinKleinOrderPage proceedPurchase(){
        waitUntilElementIsClickable(proceedToSecureCheckout).click();

        return new CalvinKleinOrderPage(driver);
    }
    */

    public boolean isEmpty(){
        waitUntilAjaxCompleted();
        List<WebElement> items = driver.findElements(By.xpath(numberOfItemTemplate));

        return items.isEmpty();
    }

    public int getSubtotalCost(){
        return resolveCost(subtotalCost.getText());
    }

    public int getEstimatedTotalCost(){
        return resolveCost(estimatedTotalCost.getText());
    }

    public int getShippingCost(){
        return resolveCost(shippingCost.getText());
    }
}