package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public WebDriver driver;
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub		
		this.driver=driver;		
	}
	
	By email=By.cssSelector("[type='email']");
	By emailSubmit=By.cssSelector("[type='submit']");
	By password=By.cssSelector("[type='password']");
	By signIn=By.cssSelector("[id='signInSubmit']");
	By response=By.cssSelector("[class='a-list-item']");
	
	
	public WebElement getEmail() {
		return driver.findElement(email);
	}
	
	public WebElement emailSubmit() {
		return driver.findElement(emailSubmit);
	}
	
	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement signIntoApp() {
		return driver.findElement(signIn);
	}
	
	public WebElement signinResponse() {
		return driver.findElement(response);
	}
}
