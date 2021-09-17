package amazonAutoTest;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jdk.internal.org.jline.utils.Log;
import pageObject.CartPage;
import pageObject.LandingPage;
import pageObject.LoginPage;
import pageObject.ProductPage;
import pageObject.WishListPage;
import resources.Base;

public class assertCartItems extends Base {
	
	public WebDriver driver;
	public Integer sum = 0;
	public Integer subTotal;
	
	public static Logger log =LogManager.getLogger(Base.class.getName());
	
	@BeforeTest
	public void initialize() throws IOException
	{
	
		 driver =initializeDriver();
		 log.info("Driver is initialized");
		 driver.get(prop.getProperty("siteURL"));
		 log.info("Calling website URL");
	}
	
	@Test
	public void assertCart() throws IOException, ParseException
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
		landingPage.moveToCart().click();
		log.info("Moved to Cart");
		CartPage cartPage = new CartPage(driver);
		
		subTotal = (Integer) NumberFormat.getNumberInstance(Locale.UK).parse(cartPage.subTotal().getText().trim()).intValue();

		List<WebElement> elements = cartPage.productPrice();

        for(WebElement element : elements){ 
        	        
        	sum = sum + (Integer) NumberFormat.getNumberInstance(Locale.UK).parse(element.getText().trim()).intValue();        	
        } 

        log.info("Price of Products summed up");        
        Assert.assertEquals(sum, subTotal);
        log.info("Test case Executed");
	}
	
	@AfterTest
	public void teardown()
	{	log.info("Closing the browser");	
		driver.manage().deleteAllCookies();
		driver.close();		
	}
}
