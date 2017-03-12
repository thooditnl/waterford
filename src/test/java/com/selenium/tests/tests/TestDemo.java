package com.selenium.tests.tests;

import com.selenium.tests.pages.HomePage;
import com.selenium.tests.pages.LoginPage;
import com.selenium.tests.utility.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by THOODI on 3/11/2017.
 */
public class TestDemo extends Base {


    @Test
    public void test1() throws InterruptedException {
        homepage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        String loginPageTitle = homepage.getAccountText();
        Thread.sleep(3000);
        Assert.assertTrue(loginPageTitle.toLowerCase().contains("home"),"Not Equal");


        homepage.clickOnMyaccount();

        loginPage.loginAccount(props.getProperty("userName"),props.getProperty("passWord"));
    }

}


  /*  @FindBy(xpath="//a[text() = \"Home\"]")
    private WebElement homeButton;

    @FindBy(xpath="//a[text() = \"Product Category\"]")
    private WebElement productCategory;

    @FindBy(className = "account_icon")
    private WebElement myaccount;
*/