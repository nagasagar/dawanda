package com.dawanda.tests;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import com.dawanda.pageobjects.AlmostTherePage;
import com.dawanda.pageobjects.LoginPage;
import com.dawanda.pageobjects.RegistrationPage;
import com.dawanda.pageobjects.UserHomePage;
import com.dawanda.pageobjects.WelcomePage;
import com.nagasagar.dawanda.BaseTest;


public class DawandaTest extends BaseTest {

	  
	  Properties prop = new Properties();
	
	 @Test
    public void RegistrationTest() throws Exception {
		  SoftAssert softAssert = new SoftAssert();
    	Random rand = new Random(); 
    	long num = rand.nextLong();
    	String usrname = "frontend-test-user"+num ;
    	
    	WelcomePage welcomepage = new WelcomePage(testURL);
    	RegistrationPage resistrationpage = welcomepage.clickRegisterButton();
    	
    	
    	 AlmostTherePage almostherepage = resistrationpage.enterFirstName("frontend")
    	.enterLastName("test-user")
    	.enterUserName(usrname)
    	.enterEmail("frontend-tests+-"+num+"@dawandamail.com")
    	.enterPassword("P@55w0rd")
    	.accept_termsNconditions()
    	.clickResgisterNow();
    	
    	String request_emailvalidation_text =almostherepage.get_email_hint_text();
    	// soft assert is used when test execution is to be continued after failure of any step
    	softAssert.assertTrue(request_emailvalidation_text.contains("frontend-tests+-"+num+"@dawandamail.com"), "Message to validate emails id doesnot contain entered email id");
    	
    	almostherepage.clickLogoutButton();
    	
    	//login again
    	welcomepage = new WelcomePage(testURL);
    	LoginPage loginpage = welcomepage.clickLoginButton();
    	
    	UserHomePage homepage = loginpage.enterUserName(usrname)
    	.enterPassword("P@55w0rd")
    	.clickLogin();
    	
    	String loggedinuser = homepage.getLoggedInUser();
    	// soft assert is used when test execution is to be continued after failure of any step
    	softAssert.assertTrue(loggedinuser.equalsIgnoreCase(usrname), "Loggedin user name is not displayed");
    	
    	homepage.clickLogoutButton();
    	
    	// at the end to mark test failure if any of the previous soft asserts had failed
   	 softAssert.assertAll();
    	
    }
    
    @Test
    public void RegistrationFormFieldsTest() throws Exception {
    	SoftAssert softAssert = new SoftAssert();
    	Random rand = new Random(); 
    	long num = rand.nextLong();
    	String usrname = "frontend-test-user"+num ;
    	
    	RegistrationPage resistrationpage = new RegistrationPage(testURL+"/account/register");
    	
    	
    	// submit empty form-fields
    	 resistrationpage.enterFirstName("")
    	.enterLastName("")
    	.enterUserName("")
    	.enterEmail("")
    	.enterPassword("")
    	//.accept_termsNconditions()
    	.clickResgisterNow_withknownErrors();
    	
    	 String[] expectedErrors = {"firstname","lastname","username","email","password","terms"};
    	 
    	 for(String errorField : expectedErrors)
    	 {
    		 boolean error_msg_displayed = false;
    		 List<WebElement> allerrormsgs = resistrationpage.getFormvalidation_msgs();
    		 for(WebElement e :allerrormsgs)
    		 {
    			if(e.getAttribute("data-bind-invalid-show").contains(errorField))
    			{
    				error_msg_displayed = true;
    				break;
    			}
    		 }
    		// soft assert is used when test execution is to be continued after failure of any step
    		 softAssert.assertTrue(error_msg_displayed, "Error Message not displayed when "+errorField+" is set blank");
    	 }
    	 
    	 // submit invalid email
    	 resistrationpage.enterFirstName("frontend")
     	.enterLastName("test-user")
     	.enterUserName(usrname)
     	.enterEmail("@dawandamail.com") //invalid email format
     	.enterPassword("P@55w0rd")
     	.accept_termsNconditions()
     	.clickResgisterNow_withknownErrors();
    	 //verify based on current language of dawanda website
    	 softAssert.assertEquals(resistrationpage.getEmail_Formvalidation_msgs(), prop.getProperty(language+"_invalid"),
    			"Expected Error messgae not dsplayed when invalid e-mail address is provided");
    	 
    	 // submit invalid password this step fails for nl website 
    	 // Voer aub minimaal %{minlenght} karakters in voor je wachtwoord - %{minlenght} not evaluated
    	 resistrationpage.enterFirstName("frontend")
      	.enterLastName("test-user")
      	.enterUserName(usrname)
      	.enterEmail(usrname+"@dawandamail.com") 
     	.enterPassword("P@t") //password length less than required (5)
      	.accept_termsNconditions()
      	.clickResgisterNow_withknownErrors();
    	 //verify based on current language of dawanda website
    	 softAssert.assertEquals(resistrationpage.getPassword_Formvalidation_msgs(), prop.getProperty(language+"_invalid_password"),
     			"Expected Error messgae not dsplayed when un-acceptable password is provided");
    	 
    	 // at the end to mark test failure if any of the previous soft asserts had failed
    	 softAssert.assertAll();
    }
    
    @BeforeTest
    public void loadProperties() throws Exception {
    	InputStream input = null;
    	try {
    		input = new FileInputStream("./src/test/resources/errormessages.properties");
    		prop.load(input);
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	} 
    }
}