package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
	
	public WebDriver driver;
	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub		
		this.driver=driver;		
	}
	
	By subTotal = By.xpath("//div[@data-name='Subtotals']//span//span");
	By productPrice = By.cssSelector("span[class*='a-size-base a-color-base sc-price sc-white-space-nowrap sc-product-price']");
	
	public List<WebElement> productPrice() {
		return driver.findElements(productPrice);
	}
	
	public WebElement subTotal() {
		return driver.findElement(subTotal);
	}
	
		
}
