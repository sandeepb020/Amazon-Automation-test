package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
	
	public WebDriver driver;
	public ProductPage(WebDriver driver) {
		// TODO Auto-generated constructor stub		
		this.driver=driver;		
	}
	
	By addToCart = By.xpath("//input[@class='a-button-input']");
	
	public List<WebElement> addTocart() {
		return driver.findElements(addToCart);
	}
	
		
}
