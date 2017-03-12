package com.selenium.tests.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by THOODI on 3/11/2017.
 */
public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//a[text() = \"Home\"]")
    private WebElement homeButton;

    @FindBy(xpath="//a[text() = \"Product Category\"]")
    private WebElement productCategory;

    @FindBy(className = "account_icon")
    private WebElement myaccount;

    //Get the title of myaccount Page
    public String getAccountText(){
        return myaccount.getText();
    }


    public void clickOnMyaccount(){
        myaccount.click();

    }


  /*  public String anyElementContainingText(String text)
    {
        return String.format("/*//*[contains(text(),'%s')]", text);
    }*/

}
