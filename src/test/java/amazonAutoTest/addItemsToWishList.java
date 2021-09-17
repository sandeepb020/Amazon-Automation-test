package amazonAutoTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
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

public class addItemsToWishList extends Base {
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
	public void addItemsToList() throws IOException
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
		WishListPage wishListPage = landingPage.myListLink();
		log.info("Clicked on the created WishList link");
		wishListPage.addIdeas().click();
		log.info("Clicking link for adding list");
		
		//Get items for wishList
		String wishListProducts = prop.getProperty("wishListProducts");
		List<String> productList = Arrays.asList(wishListProducts.split("\\s*,\\s*"));
		
		ArrayList<String> listOfProducts = new ArrayList<String>(productList);
		for(int i=0;i<listOfProducts.size();i++)
		{			
			wishListPage.ideaInputs().clear();
			wishListPage.ideaInputs().click();
			wishListPage.ideaInputs().sendKeys(listOfProducts.get(i));
			wishListPage.ideaInputSubmit().click();
			log.info("Product"+ listOfProducts.get(i) +" Added");
		}
		log.info("Items Added");
		log.info("Testcase Executed");

	}
	
	@AfterTest
	public void teardown()
	{	log.info("Closing the browser");	
		driver.manage().deleteAllCookies();
		driver.close();		
	}
}
