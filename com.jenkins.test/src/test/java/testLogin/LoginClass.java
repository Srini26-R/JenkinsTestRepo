package testLogin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginClass {

	private WebDriver driver = null;

	@BeforeSuite
	public void browserSetUp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void navigateToUrl() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@Test(priority = 2)
	public void login() {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Test(priority = 3)
	public void homePageHeaderVerification() {
		String headerText = driver.findElement(By.xpath("//h6[contains(@class,'topbar-header')]")).getText();
		if (headerText.equals("Dashboard")) {
			System.out.println("Header validation passed");
		} else {
			System.out.println("Header validation failed!");
		}
	}

	@AfterSuite
	public void closeBrowser() {
		driver.close();
	}

}