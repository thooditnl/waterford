package com.selenium.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.selenium.tests.utility.Base.clickLogin;

/**
 * Created by THOODI on 3/11/2017.
 */
public class LoginPage {
   protected WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

                @FindBy(id = "log")
                private WebElement userName;

                @FindBy(id="pwd")
                private WebElement passWord;

                /*@FindBy(id="login")
                private WebElement loginButton;
*/
                @FindBy(linkText = "Register")
                private WebElement registerLink;

                @FindBy(linkText = "Log in")
                private WebElement loginLink;


    public void loginAccount(String user, String pass) {
        userName.sendKeys(user);
        passWord.sendKeys(pass);
        clickLogin();
    }

}

