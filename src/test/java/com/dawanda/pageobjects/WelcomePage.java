package com.dawanda.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagasagar.dawanda.BasePage;

public class WelcomePage extends BasePage{

    @FindBy(xpath="//a[@href='/account/login']")
    WebElement pageidentifier_welcomepage;

    
    @FindBy(xpath="//a[@href='/account/register']")
    WebElement registerButton;
    
    @FindBy(xpath="//a[@href='/account/login']")
    WebElement loginButton;
  
    @FindBy(xpath="//div[@class='cc_banner-wrapper ']//a[@href='#null']")
    WebElement hide_cookiesinfomsg;

    

    public WelcomePage(String URL) throws Exception{
    	super(URL);
    	PageFactory.initElements(driver, this);
    	assertCurrentPage(pageidentifier_welcomepage);
    }
    
    public WelcomePage() throws Exception{
    	super();
    	PageFactory.initElements(driver, this);
    	assertCurrentPage(pageidentifier_welcomepage);
    }


    public RegistrationPage clickRegisterButton() throws Exception{
    	Actions action = new Actions(driver);
    	action.moveToElement(pageidentifier_welcomepage).build().perform();
    	WebDriverWait wait = new WebDriverWait(driver, explictWaitTimeout);
        wait.until(ExpectedConditions.visibilityOf(registerButton));
    	registerButton.click();
    	return new RegistrationPage();
    }
    
    public LoginPage clickLoginButton() throws Exception{
    	Actions action = new Actions(driver);
    	action.moveToElement(pageidentifier_welcomepage).build().perform();
    	WebDriverWait wait = new WebDriverWait(driver, explictWaitTimeout);
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
    	return new LoginPage();
    }
    
    public WelcomePage clickhideCokiesMessageButton() throws Exception{
    	Thread.sleep(1000);
    	WebDriverWait wait = new WebDriverWait(driver, explictWaitTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(hide_cookiesinfomsg));
    	hide_cookiesinfomsg.click();
    	return this;
    }

}
