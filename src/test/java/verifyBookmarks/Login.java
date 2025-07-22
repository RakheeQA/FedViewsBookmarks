package verifyBookmarks;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class Login {
	public static WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Starting Test On Edge Browser");
		// WebDriverManager.edgedriver().setup();
		System.setProperty("webdriver.edge.driver", "C:\\Users\\govstrive2\\edgedriver_win64\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void testBk() {
		// throws InterruptedException
		// String baseUrl = "https://fedviewsd.connecthr.com/";

		// EDGE BROWSER

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// driver.get(baseUrl);

		driver.get("https://fedviewsd.connecthr.com/");
		// Wait for page to load completely
		/*
		 * new WebDriverWait(driver, Duration.ofSeconds(30)).until(webDriver ->
		 * ((JavascriptExecutor) webDriver)
		 * .executeScript("return document.readyState").equals("complete"));
		 * System.out.println("********Page title is: " + driver.getTitle());
		 * Thread.sleep(5000);
		 * 
		 * // Wait for the login form to load
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		 * 
		 * // Perform Login
		 * driver.findElement(By.id("username")).sendKeys("testqa1@govstrive.com");//
		 * email
		 * driver.findElement(By.id("password")).sendKeys("Pineapple@12345");// password
		 * driver.findElements(By.cssSelector(".btn.btn-primary.new-btn-design")).get(0)
		 * .click();// login button
		 * driver.findElements(By.cssSelector(".btn.btn-light.new-btn-design")).get(0).
		 * click();// continue button
		 * System.out.
		 * println("****************Clicked Continue. Waiting for loader to disappear..."
		 * );
		 */
		// SELECT AGENCY AND SURVEY

		// VERIFY PAGE TITLE
		// String testTitle = "FedViews ? GovStrive";
		// String originalTitle = driver.getTitle();
		// Assert.assertEquals(originalTitle, testTitle);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
		System.out.println("Finished Test On Edge Browser");
	}
}