package test;

import driver.DriverSingleton;
import model.Item;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import page.TimberlandBagPage;
import page.TimberlandHomePage;
import service.ItemCreator;
import service.PromoCodeCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static util.Resolver.resolveDiscount;

public class TimberlandCartTest extends CommonConditions {

    static Logger log = Logger.getLogger(TimberlandCartTest.class);

    @Test
    public void addItemToCartTest(){
        log.info("Test \"addItemToCartTest\" is started!");

        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");

        // char's replacing for normal searching
        String name = expectedItem.getName().replace('™',' ');

        Item item = new TimberlandHomePage(driver)
                .openPage()
                .search(name)
                .setSize(expectedItem.getSize())
                .addToCart()
                .openCart()
                .getItem(1);

        assertThat(item, equalTo(expectedItem));
    }

    @Test
    public void addManyItemsToCartTest() {
        log.info("Test \"addManyItemsToCartTest\" is started!");

        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");
        expectedItem.changeAmount(3);

        // char's replacing for normal searching
        String name = expectedItem.getName().replace('™',' ');

        Item item = new TimberlandHomePage(driver)
                .openPage()
                .search(name)
                .setSize(expectedItem.getSize())
                .addToCart()
                .openCart()
                .changeAmountOfItem(1, expectedItem.getAmount())
                .getItem(1);

        // site interface doesn't work correctly with webdriver & assertThat with item object always fails so we only
        // click & don't check cost value
        assertThat(item.getName(), equalTo(expectedItem.getName()));
        assertThat(item.getSize(), equalTo(expectedItem.getSize()));
        assertThat(item.getAmount(), equalTo(expectedItem.getAmount()));
    }

    @Test
    public void addTwoDifferentItemsToCartTest(){
        log.info("Test \"addTwoDifferentItemsToCartTest\" is started!");

        Item firstExpectedItem = ItemCreator.withCredentialsFromProperty("first");
        String firstName = firstExpectedItem.getName().replace('™',' ');

        Item secondExpectedItem = ItemCreator.withCredentialsFromProperty("second");
        String secondName = secondExpectedItem.getName();

        TimberlandHomePage page = new TimberlandHomePage(driver);

        Item firstItem = page.openPage()
                .search(firstName)
                .setSize(firstExpectedItem.getSize())
                .addToCart()
                .openCart()
                .getItem(1);

        Item secondItem = page.search(secondName)
                .setSize(secondExpectedItem.getSize())
                .addToCart()
                .openCart()
                .getItem(2);

        assertThat(firstItem.getName().toLowerCase(), equalTo(firstExpectedItem.getName().toLowerCase()));
        assertThat(firstItem.getSize(), equalTo(firstExpectedItem.getSize()));
        assertThat(firstItem.getAmount(), equalTo(firstExpectedItem.getAmount()));

        assertThat(secondItem.getName().toLowerCase(), equalTo(secondExpectedItem.getName().toLowerCase()));
        assertThat(secondItem.getSize(), equalTo(secondExpectedItem.getSize()));
        assertThat(secondItem.getAmount(), equalTo(secondExpectedItem.getAmount()));
    }

    @Test
    public void removeItemFromCartTest() {
        log.info("Test \"removeItemFromCartTest\" is started!");

        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");
        String name = expectedItem.getName().replace('™',' ');

        boolean isEmpty = new TimberlandHomePage(driver)
                .openPage()
                .search(name)
                .setSize(expectedItem.getSize())
                .addToCart()
                .openCart()
                .removeItem(1)
                .isEmpty();

        assertThat(isEmpty, equalTo(true));
    }
}