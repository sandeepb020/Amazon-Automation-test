package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WishListPage {

	public WebDriver driver;
	public WishListPage(WebDriver driver) {
		// TODO Auto-generated constructor stub		
		this.driver=driver;		
	}
	
	
	
	By createListButton = By.cssSelector("[id='createList']");
	By listName = By.cssSelector("input[id='list-name']");
	By createListFinalButton = By.xpath("//span[contains(@data-action,'create-list-submit')]//span//span//input");
	By myListLink = By.xpath("//div[contains(@class,'nav-panel')]//a");	
	By addIdea = By.xpath("//a[contains(text(),'+Add Idea to List')]");
	By ideaInput = By.xpath("//input[@id='wfaTextInput']");	
	By ideaInputSubmit = By.xpath("//input[@id='wfa-note-add-button']");
	
	public WebElement createListButton() {
		return driver.findElement(createListButton);
	}
	
	public WebElement setListName() {
		return driver.findElement(listName);
	}
	
	public WebElement createList() {
		return driver.findElement(createListFinalButton);
	}
	
	public WebElement myListLink() {
		return driver.findElement(myListLink);
	}
	
	public WebElement ideaInputs() {
		return driver.findElement(ideaInput);
	}
	
	public WebElement ideaInputSubmit() {
		return driver.findElement(ideaInputSubmit);
	}
	
	public WebElement addIdeas() {
		return driver.findElement(addIdea);
	}
}
