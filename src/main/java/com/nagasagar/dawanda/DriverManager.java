package com.nagasagar.dawanda;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;


public class DriverManager {
	private static ThreadLocal<WebDriver> driverSession = new ThreadLocal<WebDriver>();
    
	
	public static WebDriver getWebDriver(final String browser) {
        if (driverSession.get() == null) {
            try {
                createWebDriver(browser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driverSession.get();
    }
	
	private static void createWebDriver(String browser) throws Exception {
		WebDriver driver;
        System.out.println(Thread.currentThread() + ":" + new Date() + ":::Start creating web driver instance: "
                + browser);
        if (browser.equalsIgnoreCase("firefox")||browser.equalsIgnoreCase("*firefox")) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("chrome")||browser.equalsIgnoreCase("chrome")) {
        	ChromeDriverManager.getInstance().setup();
        	driver = new ChromeDriver();
        	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        	driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("iexplore")||browser.equalsIgnoreCase("*iexplore")) {
        	InternetExplorerDriverManager.getInstance().setup();
        	driver = new InternetExplorerDriver();
        	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        	driver.manage().window().maximize();
        } else {
            throw new RuntimeException("Unsupported browser" + browser);
        }

        System.out.println(Thread.currentThread() + ":" + new Date() + ":::Finish creating web driver instance: "
                + browser);

        driverSession.set(driver);
       
    }
	
	
	public static void cleanUp() {
		WebDriver driver = driverSession.get();
        if (driver != null) {
        	try {
                    driver.quit();
                } catch (WebDriverException ex) {
                    ex.printStackTrace();
                }
        	driver = null;
            }
        driverSession.remove();
    }

}
