package amazonAutoTest;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jdk.internal.org.jline.utils.Log;
import pageObject.LandingPage;
import pageObject.LoginPage;
import pageObject.WishListPage;
import resources.Base;

public class createWishList extends Base {
	public WebDriver driver;
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
	public void createWishListforUser() throws IOException
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
		Actions action = new Actions(driver);
		action.moveToElement(landingPage.moveToWishList()).build().perform();
		log.info("Mouse over executed");
		WishListPage wishListPage = landingPage.wishListLink();
		wishListPage.createListButton().click();
		log.info("Create Wish List Button Clicked");
		wishListPage.setListName().clear();
		log.info("Clearing default list name");
		wishListPage.setListName().sendKeys(prop.getProperty("wishListName"));
		log.info("Adding the wishlist's name");
		wishListPage.createList().click();
		log.info("WishList created");
		log.info("Testcase Executed");
	}
	
	@AfterTest
	public void teardown()
	{	log.info("Closing the browser");	
		driver.manage().deleteAllCookies();
		driver.close();		
	}
}
