package amazonAutoTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jdk.internal.org.jline.utils.Log;
import pageObject.LandingPage;
import pageObject.LoginPage;
import pageObject.ProductPage;
import pageObject.WishListPage;
import resources.Base;

public class addProductstoCart extends Base {
	
	public WebDriver driver;
	public String productsCount;
	public Integer count;
	public Integer iterator=0;
	
	public static Logger log =LogManager.getLogger(Base.class.getName());
	
	@BeforeTest
	public void initialize() throws IOException
	{
	
		 driver =initializeDriver();
		 log.info("Driver is initialized");
			System.out.println(prop.getProperty("siteURL"));
		 driver.get(prop.getProperty("siteURL"));
		 log.info("Calling website URL");
	}
	
	@Test
	public void addItemsToCart() throws IOException
	{
		
		LandingPage landingPage =new LandingPage(driver);
		log.info("Home page loaded");
		LoginPage loginPage = landingPage.getLogin();
		log.info("Login page loaded");
		loginPage.getEmail().sendKeys(prop.getProperty("successLoginUsername"));
		log.info("Valid Username Provided");
		loginPage.emailSubmit().click();
		log.info("Username Submitted");
		loginPage.getPassword().sendKeys(prop.getProperty("successLoginPassword"));
		log.info("Valid Password Provided");
		loginPage.signIntoApp().click();
		log.info("Successfully Signed in");
		ProductPage products = landingPage.goToProducts();
		log.info("Moved to products category");
		
		List<WebElement> elements = products.addTocart();        
        productsCount = prop.getProperty("productsCount");      
        count = Integer.parseInt(productsCount);
      
        for(WebElement element : elements){ 
        	WebDriverWait wait = new WebDriverWait(driver,30);
        	wait.until(ExpectedConditions.visibilityOf(element));
        	element.click();
            iterator++;
            
            if(iterator >= count) {
            	break;
            }                        
        } 
        log.info("Products added to cart successfully");
        log.info("Testcase Executed");		

	}
	
	@AfterTest
	public void teardown()
	{	log.info("Closing the browser");	
		driver.manage().deleteAllCookies();
		driver.close();		
	}
}
