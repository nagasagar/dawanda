package com.nagasagar.dawanda;

import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.Wait.WaitTimedOutException;



public class BasePage {
	
	protected WebDriver driver = DriverManager.getWebDriver(BaseTest.testBrowser);
	protected int explictWaitTimeout = 15;
	
    
    public BasePage() throws Exception {
        this( null);
    }
    
    public BasePage(final String url) throws Exception {
        Calendar start = Calendar.getInstance();
        start.setTime(new Date());
        long startTime = start.getTimeInMillis();
        driver = DriverManager.getWebDriver(BaseTest.testBrowser);

        if (url != null) {
        	driver.get(url);
        }
        waitForPageToLoad();
     	
        
               
        Calendar end = Calendar.getInstance();
        end.setTime(new Date());

        
        long endTime = end.getTimeInMillis();
        if ((endTime - startTime) / 1000 > 0) {
           // SeltafTestLogger.log("Page Opened in :" + (endTime - startTime) / 1000 + "seconds");
        }
    }
    
    @SuppressWarnings("deprecation")
	private void waitForPageToLoad() throws Exception {
        try {
            new Wait() {
                @Override
                public boolean until() {
                    try {
                        driver.switchTo().defaultContent();
                        return true;
                    } catch (UnhandledAlertException ex) {
                        try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    } catch (WebDriverException e) { }

                    return false;
                }
            }.wait(String.format("Timed out waiting for page to load"),
                15000);
        } catch (WaitTimedOutException ex) { }

        // populate page info
        try {
            //CapturePageSnapshot();
        } catch (Exception ex) {

            // ex.printStackTrace();
            throw ex;
        }
    }
    
    protected void assertCurrentPage(WebElement pageIdentifierElement) throws Exception {
		if(pageIdentifierElement!=null)
		{
			WebDriverWait wait = new WebDriverWait(driver, explictWaitTimeout);
	        wait.until(ExpectedConditions.visibilityOf(pageIdentifierElement));
		}
		
		
		if (pageIdentifierElement == null) { }
        else if (pageIdentifierElement.isDisplayed()) { }
        else {
            

            throw new Exception(getClass().getCanonicalName()
                    + " is not the current page.\nPageIdentifierElement " + pageIdentifierElement.toString()
                    + " is not found.");
        }

      
		
	}

}
