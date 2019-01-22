package com.app.pages;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

		/*static - driver(Browser) instance is defined as static so it shares common memory across all objects means
		only on browser will be created*/
	
		static WebDriver driver; //WebDriver is an interface
		public String browser = "chrome"; //inplace of chrome we can use any browser we want to use
		
		//Constructor to launch browser and enter URL
		
		public BasePage(){
			if(driver == null){
			if (browser.equals("chrome")){
				System.setProperty("webdriver.chrome.driver", "/Users/arunkumarselvam/Documents/workspace/mySeleniumProj/src/test/resources/chromedriver");
				driver = new ChromeDriver();
			}else if(browser.equals("firefox")){
				System.setProperty("webdriver.gecko.driver", "/Users/arunkumarselvam/Documents/workspace/mySeleniumProj/src/test/resources/geckodriver");
				driver = new FirefoxDriver();
			}
			//driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("http://automationpractice.com/index.php");
			}
		}
		
		//Method to check if an element is present or not
		public boolean elementFound(WebElement element){
			boolean res = false;
			try {
				res = element.isDisplayed();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return res;	
		}
		
		//Method to check if an element is enabled or not
				public boolean elementEnabled(WebElement element){
					boolean res = false;
					try {
						res = element.isEnabled();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					return res;	
				}
		
		//Method to enter data into a text box
		public void setTest(WebElement element, String name){
			if(name != null) {
				element.clear();
				element.sendKeys(name);
			}
		}
		
		public void click(List<String> element){
			 ((WebElement) element).click();
		}
		
		public String getTxtAttribute(WebElement element){
			return element.getAttribute("value");
		}
		
		
		public String selectFromDropDown(WebElement element, String option){
			Select obj = new Select(element);
			obj.selectByVisibleText(option);
			return obj.getFirstSelectedOption().getText();
		}
		
		
		public boolean isElementVisible(WebElement element) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 90);
				wait.until(ExpectedConditions.visibilityOf(element));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		
		public void captureScreenshot(String name) {
			try {
				TakesScreenshot tk = (TakesScreenshot) driver;
				File file = tk.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(file, new File("/Users/arunkumarselvam/Desktop/automation_screenshot/screen2.png"));
				System.out.println("Screenshot taken successfully");
			} 
			catch (Exception e) {
				
				System.out.println(" ");
		}		
}
}

