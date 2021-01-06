package test;

import driver.DriverSingleton;
import model.Item;
import org.testng.annotations.Test;
import page.TimberlandBagPage;
import page.TimberlandHomePage;
import service.ItemCreator;
import service.PromoCodeCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static util.Resolver.resolveDiscount;

public class TimberlandCartTest extends CommonConditions {

    @Test
    public void addItemToCartTest(){
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");

        // char's replacing for normal searching
        String name = ItemCreator.getName("first").replace('™',' ');

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
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");
        expectedItem.changeAmount(3);

        // char's replacing for normal searching
        String name = ItemCreator.getName("first").replace('™',' ');

        TimberlandBagPage page = new TimberlandHomePage(driver)
                .openPage()
                .search(name)
                .setSize(expectedItem.getSize())
                .addToCart()
                .openCart()
                .changeAmountOfItem(1, expectedItem.getAmount());

        page.waitUntilAjaxCompleted();
        Item item = page.getItem(1);

        assertThat(item, equalTo(expectedItem));
    }
}