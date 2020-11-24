import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class WebDriverTest {
    @Test
    void webDriverTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("https://www.timberland.com/shop/mens-axis-peak-packable-jacket-black-a2c9p001");

        new WebDriverWait(driver, 15).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));

        // закрываем всплывающую парашу
        driver.findElement(By.xpath("//*[@id=\"monetate_lightbox_contentMap\"]/area[1]")).click();
        // раскрываем список
        driver.findElement(By.xpath("//*[@id=\"attr-size\"]")).click();
        // выбираем нужный пункт
        driver.findElement(By.xpath("//*[@id=\"attr-size\"]/option[2]")).click();
        // жмем Add to cart
        driver.findElement(By.xpath("//*[@id=\"ecom-product-actions\"]/div[2]/a[2]")).click();
        String name = driver.findElement(By.xpath("//*[@id=\"master-container\"]/div/header/sect" +
                "ion/div/div[2]/article/div[4]/div/div/div[1]/div[2]/p[1]/a")).getText();

        driver.quit();
        Assert.assertEquals("MEN'S AXIS PEAK PACKABLE JACKET", name);
    }
}