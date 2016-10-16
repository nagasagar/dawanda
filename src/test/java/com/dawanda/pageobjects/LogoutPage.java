package com.dawanda.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagasagar.dawanda.BasePage;

public class LogoutPage extends BasePage{

    @FindBy(className="bye-img")
    WebElement pageidentifier_logoutpage;

    

    
    public LogoutPage(String URL) throws Exception{
    	super(URL);
    	PageFactory.initElements(driver, this);
    	assertCurrentPage(pageidentifier_logoutpage);
    }
    
    public LogoutPage() throws Exception{
    	super();
    	PageFactory.initElements(driver, this);
    	assertCurrentPage(pageidentifier_logoutpage);
    }
    
   
    

}
