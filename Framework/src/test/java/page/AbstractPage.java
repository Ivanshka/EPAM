package page;

import model.Item;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected final int WAIT_TIMEOUT_SECONDS = 120;

    protected WebDriver driver;

    protected abstract AbstractPage openPage();

    protected AbstractPage(WebDriver driver){
        this.driver = driver;
    }

    public WebElement waitUntilPresenceOfElement(By location){
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(location));
    }

    public WebElement waitUntilVisibilityOf(WebElement element){
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitUntilElementIsClickable(By location){
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(location));
    }

    public void waitUntilElementIsClickableAndClickAvoidModalWindow(By location){
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.elementToBeClickable(location)).click();
        } catch (ElementClickInterceptedException exception){
            driver.navigate().refresh();
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.elementToBeClickable(location)).click();
        }
    }

    public WebElement waitUntilElementIsClickable(WebElement element){
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilElementIsClickableAndClickAvoidModalWindow(WebElement element){
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException exception){
            driver.navigate().refresh();
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.elementToBeClickable(element)).click();
        }
    }

    public void waitUntilFieldIsChanged(WebElement element, String startValue){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, startValue)));
    }

    public void waitUntilAjaxCompleted(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(jQueryAJAXCompleted());
    }

    protected static ExpectedCondition<Boolean> jQueryAJAXCompleted(){
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver){
                return (Boolean) ((JavascriptExecutor)
                        driver).executeScript("return (window.jQuery != null) && (jQuery.active == 0);");
            }
        };
    }
}
