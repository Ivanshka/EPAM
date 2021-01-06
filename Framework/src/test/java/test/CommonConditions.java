package test;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;
import util.TestListener;

@Listeners(TestListener.class)
public class CommonConditions {
    protected WebDriver driver;
    Logger log;

    @BeforeClass
    public void init(){
        driver = DriverSingleton.getInstance();
        log = Logger.getLogger(CommonConditions.class);
        if (driver == null) {
            log.fatal("WebDriver couldn't initialize!");
        } else {
            log.info("WebDriver is initialized.");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void close(){
        DriverSingleton.deleteAllCookies();
        log.info("Cookies was cleared.");
    }

    //@AfterClass(alwaysRun = true)
    public void dispose(){
        DriverSingleton.closeDriver();
        log.info("WebDriver was closed.");
    }
}
