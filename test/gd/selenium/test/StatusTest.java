package gd.selenium.test;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class StatusTest {
	
	
	@Test
	public void getRemoteStatusTest(){
		
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.com");
		driver.quit();
		System.out.println(driver.toString());
		driver.get("https://www.google.com");
		driver.quit();
		System.out.println(driver.toString());
		
	}

}
