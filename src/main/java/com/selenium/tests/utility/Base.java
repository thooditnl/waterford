package com.selenium.tests.utility;

import com.selenium.tests.pages.HomePage;
import com.selenium.tests.pages.LandingPage;
import com.selenium.tests.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by THOODI on 3/11/2017.
 */


//@Listeners(LoggingListener.class)
public class Base {
    protected WebDriver driver=null;
    protected Logger logger = Logger.getLogger(String.valueOf(getClass()));
    protected Properties props = new Properties();
    protected HomePage homepage;
    protected LandingPage landingPage;
    protected LoginPage loginPage;

    @BeforeTest
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.store.demoqa.com");
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }



    public void GlobalData() throws IOException {
        final String LOCATORS_FILE_LOCATION = "./src/main/resources/Repository.properties";
        File file = new File(LOCATORS_FILE_LOCATION);
        FileInputStream fis = new FileInputStream(file);
        System.out.println("The Repository properties path is:" + fis);
        Properties props = new Properties();
        props.load(fis);
    }

    @FindBy(id="login")
    private static WebElement loginButton;

    public static void clickLogin(){
        loginButton.click();
    }

}
