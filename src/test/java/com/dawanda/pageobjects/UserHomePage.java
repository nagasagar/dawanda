package com.dawanda.pageobjects;

import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagasagar.dawanda.BasePage;

public class UserHomePage extends BasePage{

    @FindBy(css="div.sidebar_content.mydawanda")
    WebElement pageidentifier_login_page;

    @FindBy(css="div.header-user-welcome.logged-in-state> span:nth-of-type(2)")
    WebElement username_greeting;
   
    @FindBy(css="div.header-user-avatar.logged-in-state")
    WebElement user_avatar;
    
    @FindBy(xpath="//a[@href='/account/logout']")
    WebElement logoutButton;

    

    public UserHomePage(String URL) throws Exception{
    	super(URL);
    	PageFactory.initElements(driver, this);
    	assertCurrentPage(pageidentifier_login_page);
    }
    
    public UserHomePage() throws Exception{
    	super();
    	PageFactory.initElements(driver, this);
    	assertCurrentPage(pageidentifier_login_page);
    }

    
    public String getLoggedInUser() throws Exception{
    	return username_greeting.getText();
    }

    public LogoutPage clickLogoutButton() throws Exception{
    	Actions action = new Actions(driver);
    	action.moveToElement(user_avatar).build().perform();
    	WebDriverWait wait = new WebDriverWait(driver, explictWaitTimeout);
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
    	return new LogoutPage();
    }
    

}
