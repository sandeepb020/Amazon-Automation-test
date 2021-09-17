package amazonAutoTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

public class validateFailedLogin extends Base {
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
	public void loginFailed() throws IOException
	{
		LandingPage landingPage =new LandingPage(driver);
		log.info("Home page loaded");
		LoginPage loginPage = landingPage.getLogin();
		log.info("Login page loaded");
		loginPage.getEmail().sendKeys(prop.getProperty("successLoginUsername"));
		log.info("Valid Username Provided");
		loginPage.emailSubmit().click();
		log.info("Username Submitted");
		loginPage.getPassword().sendKeys(prop.getProperty("wrongLoginPassword"));
		log.info("Wrong Password Provided");
		loginPage.signIntoApp().click();
		log.info("Signed in requested");
		
		List<String> response = Arrays.asList(prop.getProperty("loginFailedResponseMessage1"), prop.getProperty("loginFailedResponseMessage2"));
		boolean result = response.contains(loginPage.signinResponse().getText());
		
		System.out.println(result);
		Assert.assertTrue(result);
		log.info("Testcase Executed");		
		 
	}
	
	@AfterTest
	public void teardown()
	{	log.info("Closing the browser");	
		driver.manage().deleteAllCookies();
		driver.close();		
	}
}
