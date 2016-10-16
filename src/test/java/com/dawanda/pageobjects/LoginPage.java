package com.dawanda.pageobjects;

import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagasagar.dawanda.BasePage;

public class LoginPage extends BasePage{

    @FindBy(id="login_page")
    WebElement pageidentifier_login_page;

    @FindBy(id="username")
    WebElement username;
   
    @FindBy(id="login_credentials_password")
    WebElement password;

    
    @FindBy(id="login_submit")
    WebElement loginButton;

    

    public LoginPage(String URL) throws Exception{
    	super(URL);
    	PageFactory.initElements(driver, this);
    	assertCurrentPage(pageidentifier_login_page);
    }
    
    public LoginPage() throws Exception{
    	super();
    	PageFactory.initElements(driver, this);
    	assertCurrentPage(pageidentifier_login_page);
    }

    
    public LoginPage enterUserName(String usrname) throws Exception{
    	username.clear();
    	username.sendKeys(usrname);
    	return this;
    }
    
    
    public LoginPage enterPassword(String pwd) throws Exception{
    	password.clear();
    	password.sendKeys(pwd);
    	return this;
    }


    public UserHomePage clickLogin() throws Exception{
    	loginButton.click();
    	return new UserHomePage();
    }
    

}
