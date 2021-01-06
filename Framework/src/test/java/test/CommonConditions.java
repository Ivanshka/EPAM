package test;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.TestListener;

@Listeners(TestListener.class)
public class CommonConditions {
    protected WebDriver driver;

    @BeforeClass
    public void init(){
        driver = DriverSingleton.getInstance();
    }

    @AfterMethod(alwaysRun = true)
    public void close(){
        DriverSingleton.deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public void dispose(){
        DriverSingleton.closeDriver();
    }
}
