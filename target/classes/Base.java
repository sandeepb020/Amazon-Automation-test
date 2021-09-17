package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {

	public  WebDriver driver;
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException {
		String propFile = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.propeties";
		prop = new Properties();
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		String browser = prop.getProperty("browser");
		String chromeDriver = prop.getProperty("chromedriver");
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",chromeDriver);
			driver = new ChromeDriver();
			
		} else if(browser.equals("firefox")) {
			//for Firefox Driver
		} else {
			//for IE Driver
		}
		driver.manage().window().maximize();	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
	}
	
	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;
	}
}
