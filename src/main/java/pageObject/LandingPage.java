package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

	public WebDriver driver;
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub		
		this.driver=driver;		
	}
	
	By loginLink=By.cssSelector("[id='nav-link-accountList']");
	By wishListLink=By.cssSelector("a[href*=createList]");
	By myListLink = By.xpath("//div[contains(@class,'nav-panel')]//a");
	By productTab = By.xpath("//a[contains(text(),'Pantry')]");
	By cart = By.xpath("//a[@id='nav-cart']");
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public LoginPage getLogin()
	{
		 driver.findElement(loginLink).click();
		 LoginPage lp=new LoginPage(driver);
		 return lp;
	}
	
	public WebElement moveToWishList() {
		return driver.findElement(loginLink);
	}
	
	public WebElement moveToCart() {
		return driver.findElement(cart);
	}
	
	public WishListPage wishListLink() {		
		driver.findElement(wishListLink).click();
		WishListPage wishList = new WishListPage(driver);
		return wishList;
	}
	
	public WishListPage myListLink() {		
		driver.findElement(myListLink).click();
		WishListPage wishList = new WishListPage(driver);
		return wishList;
	}
	
	public ProductPage goToProducts() {		
		driver.findElement(productTab).click();
		ProductPage products = new ProductPage(driver);
		return products;
	}
		
}
