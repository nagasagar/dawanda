package com.dawanda.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagasagar.dawanda.BasePage;

public class RegistrationPage extends BasePage{

    @FindBy(id="registration_page")
    WebElement pageidentifier_registraionpage;

    
    @FindBy(id="firstname")
    WebElement firstname;
    
    @FindBy(id="lastname")
    WebElement lastname;

    @FindBy(id="username")
    WebElement username;
    
    @FindBy(id="email")
    WebElement email;
  
    @FindBy(id="password")
    WebElement password;
    
    @FindBy(id="accept_privacy")
    WebElement accept_privacy;
    
    
    @FindBy(id="register_submit")
    WebElement registernowButton;
    
    @FindBy(css="div.validation-msg.invalid")
	List<WebElement> formvalidation_msgs;
    
    @FindBy(css="div.validation-msg.invalid[data-bind-invalid-show*=email]")
   	WebElement email_formvalidation_msg;
    
    @FindBy(css="div.validation-msg.invalid[data-bind-invalid-show*=password]")
   	WebElement password_formvalidation_msg;

    @FindBy(xpath="//div[@class='cc_banner-wrapper ']//a[@href='#null']")
    WebElement hide_cookiesinfomsg;

    public RegistrationPage(String URL) throws Exception{
    	super(URL);
    	PageFactory.initElements(driver, this);
    	assertCurrentPage(pageidentifier_registraionpage);
    }
    
    public RegistrationPage() throws Exception{
    	super();
    	PageFactory.initElements(driver, this);
    	assertCurrentPage(pageidentifier_registraionpage);
    }

    public RegistrationPage enterFirstName(String fname) throws Exception{
    	firstname.clear();
    	firstname.sendKeys(fname);
    	return this;
    }
    
    public RegistrationPage enterLastName(String lname) throws Exception{
    	lastname.clear();
    	lastname.sendKeys(lname);
    	return this;
    }
    
    public RegistrationPage enterUserName(String usrname) throws Exception{
    	username.clear();
    	username.sendKeys(usrname);
    	return this;
    }
    
    public RegistrationPage enterEmail(String emailId) throws Exception{
    	email.clear();
    	email.sendKeys(emailId);
    	return this;
    }
    
    public RegistrationPage enterPassword(String pwd) throws Exception{
    	password.clear();
    	password.sendKeys(pwd);
    	return this;
    }

    public RegistrationPage accept_termsNconditions() throws Exception{
    	accept_privacy.click();
    	return this;
    }

    public RegistrationPage clickResgisterNow_withknownErrors() throws Exception{
    	registernowButton.click();
    	return this;
    }

    public AlmostTherePage clickResgisterNow() throws Exception{
    	registernowButton.click();
    	return new AlmostTherePage();
    }

	public List<WebElement> getFormvalidation_msgs() {
		return formvalidation_msgs;
	}
	
	public String getEmail_Formvalidation_msgs() {
		return email_formvalidation_msg.getText();
	}
	
	public String getPassword_Formvalidation_msgs() {
		return password_formvalidation_msg.getText();
	}

    public RegistrationPage clickhideCokiesMessageButton() throws Exception{
    	Thread.sleep(1000);
    	WebDriverWait wait = new WebDriverWait(driver, explictWaitTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(hide_cookiesinfomsg));
    	hide_cookiesinfomsg.click();
    	return this;
    }
    

}
