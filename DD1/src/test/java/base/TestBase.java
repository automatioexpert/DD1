package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.github.dockerjava.api.model.Config;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ExtentManager;

public class TestBase {

	/*
	 * WebDriver Properties Logs DB Excel Reader Mail ReportNG Extent Reports
	 * 
	 */

	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static ExtentReports rep= ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	

	@BeforeSuite
	public void setUp() {

		if (driver == null) {
			try {
				fis = new FileInputStream("src/test/resources/properties/config.propeties");
				
			} catch (FileNotFoundException e) {

			}
			try {
				prop.load(fis);
			} catch (IOException e) {

			}
			
			
			
			
		}

		
		if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()) {
			browser=System.getenv("browser");
			
			
		}
		
		else {
			browser = prop.getProperty("browser");
		}
		prop.setProperty("browser", browser);
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			System.out.println("Firefox is launching!!");
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			System.out.println("Firefox is launched..Thank God!!!");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicit.wait"))));
		driver.get(prop.getProperty("url"));
		System.out.println(prop.getProperty("browser"));
	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}
	}
	
	
	public void click(String locator) {
		driver.findElement(By.xpath(locator)).click();
		test.log(LogStatus.INFO, "Clicked on WebElement : "+locator);
	}
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
			
		}
		catch (NoSuchElementException e) {
			return false;
			
		}
	//	return false;
	}

}
