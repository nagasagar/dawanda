package com.nagasagar.dawanda;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;

public class BaseTest {
	
	static String testBrowser = null ; 
	public String testURL = null ;
	public String language = null ;
	
	@BeforeSuite(alwaysRun = true)
    public void beforeTestSuite(final ITestContext testContext) throws IOException {
		if (testContext != null) {
            Map<String, String> testParameters = testContext.getSuite().getXmlSuite().getParameters();
            testBrowser = testParameters.get("browser");
        }
        
    }
	
	@BeforeTest(alwaysRun = true)
	 public void beforeTest(final ITestContext testContext, final XmlTest xmlTest) {
		Map<String, String> testParameters = xmlTest.getTestParameters();
        if(testParameters.get("browser")!=null)
        	testBrowser = testParameters.get("browser");
        String bro = System.getProperty("browser");
        if(bro!=null)
        	testBrowser = bro;
        if(testBrowser==null)
        	testBrowser="firefox"; // default if testbrowser is not set
        language = System.getProperty("language");
        if(language!=null && "en,es,de,nl".contains(language))
        {
        	testURL = testContext.getSuite().getXmlSuite().getParameters().get(language+"_appURL");
        }
        else
        {
        	language ="en";
        	testURL = testContext.getSuite().getXmlSuite().getParameters().get("en_appURL");
        }
    }
	
	@AfterSuite(alwaysRun = true)
    public void afterTestSuite() throws IOException {
		DriverManager.cleanUp();
    }

}
