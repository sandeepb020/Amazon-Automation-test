package amazonAutoTest;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObject.LandingPage;
import pageObject.LoginPage;
import resources.Base;

public class validateSuccessLogin extends Base {
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
	public void loginSuccess() throws IOException
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
		log.info("Testcase Executed");
		
	}
	
	@AfterTest
	public void teardown()
	{	log.info("Closing the browser");	
		driver.manage().deleteAllCookies();
		driver.close();		
	}
}
