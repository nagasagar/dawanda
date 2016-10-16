package com.dawanda.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagasagar.dawanda.BasePage;

public class AlmostTherePage extends BasePage{

    @FindBy(id="validate_email_page")
    WebElement pageidentifier_almostTherepage;

    
    @FindBy(id="validate_email_hint")
    WebElement validate_email_hint;
    
    @FindBy(css="div.header-user-avatar.logged-in-state")
    WebElement user_avatar;
    
    @FindBy(xpath="//a[@href='/account/logout']")
    WebElement logoutButton;
    
    public AlmostTherePage(String URL) throws Exception{
    	super(URL);
    	PageFactory.initElements(driver, this);
    	assertCurrentPage(pageidentifier_almostTherepage);
    }
    
    public AlmostTherePage() throws Exception{
    	super();
    	PageFactory.initElements(driver, this);
    	assertCurrentPage(pageidentifier_almostTherepage);
    }
    
   
    public String get_email_hint_text() throws Exception{
    	return validate_email_hint.getText();
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
