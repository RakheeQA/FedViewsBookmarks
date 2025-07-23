package verifyBookmarks;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.Area;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.aventstack.extentreports.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FocusAreaSmokeTest {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public ExtentTest test;

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Starting Test On Edge Browser");
		// WebDriverManager.edgedriver().setup();
		System.setProperty("webdriver.edge.driver", "C:\\Users\\govstrive2\\edgedriver_win64\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();

		System.out.println("✅ Calling ExtentManager.createTest()");
		test = ExtentManager.createTest("Focus Area, Agency Current Survey, Bookmarks Verification");
		System.out.println("✅ Test created successfully in ExtentManager");
	}

	@Test
	public void f() throws InterruptedException {
		String baseUrl = "https://fedviewsd.connecthr.com/";
		System.out.println("Navigating to: " + baseUrl);
		driver.get(baseUrl);

		wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		new WebDriverWait(driver, Duration.ofSeconds(30)).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
		System.out.println("********Page title is: " + driver.getTitle());
		// ((JavascriptExecutor)
		// driver).executeScript("document.body.style.zoom='75%'");

		Thread.sleep(5000);

		// Wait for the login form to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

		// Perform Login
		driver.findElement(By.id("username")).sendKeys("testqa1@govstrive.com");// email
		driver.findElement(By.id("password")).sendKeys("Pineapple@12345");// password
		driver.findElements(By.cssSelector(".btn.btn-primary.new-btn-design")).get(0)
				.click();// login button
		driver.findElements(By.cssSelector(".btn.btn-light.new-btn-design")).get(0).click();// continue button
		System.out.println("****************Clicked Continue. Waiting for loader to disappear...");

		// SELECT AGENCY
		System.out.println("*****************Agency Selected");
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("select-agency_unit")));
		dropdown.click();

		// Step 2: Wait for and click the option "Internal"
		WebElement agencyOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//li[normalize-space()='Internal Revenue Service']")));
		agencyOption.click();
		// Locate and click the "Current Survey" element
		WebElement currentSurvey = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//p[normalize-space()='Current Survey']")));
		currentSurvey.click();
		System.out.println("*****************Current Survey Selected");

		WebElement launchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[.//span[normalize-space()='Launch']]")));
		launchButton.click();
		System.out.println("*****************Launch button clicked");

		/*
		 * try {
		 * navigatetoFA();
		 * } catch (IOException e) {
		 * e.printStackTrace(); // or test.fail("File copy failed: " + e.getMessage());
		 * }
		 */
		// Go to Focus Area Page
		driver.get("https://fedviewsd.connecthr.com/focus-area");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("select-year_start"))).click();

		// Fetch all years from dropdown
		// remove List<WebElement> yearOptions =
		// wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
		// By.xpath("//ul[contains(@class,'MuiList-root')]/li")));

		// Wait and capture all year options from the floating list (only once)
		List<WebElement> yearOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//ul[contains(@class,'MuiList-root')]/li[normalize-space()]")));

		// Extract all year texts into a list first (to avoid
		// StaleElementReferenceException later)
		List<String> allYears = new ArrayList<>();
		for (WebElement yearOption : yearOptions) {
			String year = yearOption.getText().trim();
			if (!year.isEmpty()) {
				allYears.add(year);
			}
		}

		// Loop through each year
		for (String year : allYears) {
			System.out.println("▶️ Running test for year: " + year);
			try {
				// Reload the page for a clean state
				driver.get("https://fedviewsd.connecthr.com/focus-area");
				Thread.sleep(5000);

				// Reopen dropdown after page reload
				wait.until(ExpectedConditions.elementToBeClickable(By.id("select-year_start"))).click();

				// Select the year from the floating list
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//li[normalize-space()='" + year + "']"))).click();

				// Click the "Next" buttons (twice, as per your flow)
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']"))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']"))).click();

				// Run the actual test for selected year
				navigatetoFA(year);
			} catch (Exception e) {
				test.fail("⚠️ Test failed for year " + year + ": " + e.getMessage());
				e.printStackTrace();
			}
		}

	}

	public void navigatetoFA(String year) throws IOException {
		System.out.println("*********In focus areas : Veryfing presence of elements on page");

		/*
		 * driver.get("https://fedviewsd.connecthr.com/focus-area");
		 * // Step 1: Click the dropdown
		 * wait.until(ExpectedConditions.elementToBeClickable(By.id("select-year_start")
		 * )).click();
		 * 
		 * // Step 2: Wait for 2022 in the list (rendered in a floating list) and click
		 * wait.until(ExpectedConditions.elementToBeClickable(
		 * // By.xpath("//li[normalize-space()='2022']"))).click();
		 * By.xpath("//li[normalize-space()='" + year + "']"))).click();
		 * 
		 * // Click the Next button
		 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//span[text()='Next']"))).click();
		 * 
		 * // Step 3: Wait for and click the Next button
		 * WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(
		 * By.xpath("//span[text()='Next']")));
		 * nextButton.click();
		 */
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// HEADERS
		WebElement yearHeaderPg = null;
		WebElement yearHeaderBk = null;

		// BUTTONS
		WebElement bookmarksButtonPg = null;
		WebElement bookmarksButtonBk = null;
		WebElement ActionTrackerButton = null;
		WebElement returnButton = null;
		WebElement ActionPlanningResourcesButton = null;
		WebElement FocusAreaMethodologyButton = null;
		WebElement clearButton = null;
		// IMAGES
		WebElement imgHighImpactLowScore = null;
		WebElement imgHighImpactHighScore = null;
		// TEXT
		WebElement govFramworkText = null;
		WebElement empEngText = null;
		WebElement OPMText = null;

		// Wait for the <h1> with exact text "Year Selected: 2022"
		try {
			yearHeaderPg = wait.until(
					ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//h1[contains(text(), 'Year Selected')]")));
			System.out.println("Element text: " + yearHeaderPg.getText());

			test.pass("Selected year : visible as " + yearHeaderPg.getText());
		} catch (Exception e) {
			System.out.println("Selected year not visible");
			test.fail("Test failed: " + e.getMessage());
		}

		// CHECK PRESENCE of BOOKMARKS BUTTON

		try {
			bookmarksButtonPg = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//button[@title='Bookmarks' or .//span[contains(text(), 'Bookmarks')]]")));
			test.info(
					"Focus Area Scatter Graph Page, Agency ,Current Survey View :  Verification of presence of elements on page ");
			System.out.println("************ Bookmarks button is present.");
			// test.log(Status.PASS, "Successfully navigated and found bookmarks on Focus
			// Areas");
			test.pass("Bookmarks Button : Button is visible");
		} catch (Exception e) {
			System.out.println("Bookmarks Button : Button is NOT visible");
			test.fail("Test failed: " + e.getMessage());
		}
		// CHECK PRESENCE of 'Action Tracker' BUTTON
		try {
			ActionTrackerButton = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//button[.//span[text()='Action Tracker']]")));
			System.out.println("***********Action Tracker button is present.");

			test.pass("Action Tracker Button : Button is visible");
		} catch (Exception e) {
			System.out.println("Action Tracker Button : Button is visible NOT ");
			test.fail("Test failed: " + e.getMessage());
		}
		// CHECK PRESENCE of 'Return to Selection Menu' BUTTON
		try {
			returnButton = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//button[.//span[text()='Return to Selection Menu']]")));
			System.out.println("***********Return button is present.");

			test.pass("Return to Selection Menu Button : Button is visible");
		} catch (Exception e) {
			System.out.println("Return to Selection Menu Button : Button is visible NOT ");
			test.fail("Test failed: " + e.getMessage());
		}
		// CHECK PRESENCE of 'Action Planning Resources' BUTTON
		try {
			ActionPlanningResourcesButton = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//button[.//span[text()='Action Planning Resources']]")));
			System.out.println("***********Action Planning Resources button is present.");

			test.pass("Action Planning Resources Button : Button is visible");
		} catch (Exception e) {
			System.out.println("Action Planning Resources Button : Button is visible NOT ");
			test.fail("Test failed: " + e.getMessage());
		}

		// CHECK PRESENCE of 'Focus Area Methodology' BUTTON
		try {
			FocusAreaMethodologyButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//span[text()='Focus Area Methodology']")));
			System.out.println("***********>Focus Area Methodology button is present.");

			test.pass(">Focus Area Methodology Button : Button is visible");
		} catch (Exception e) {
			System.out.println(">Focus Area Methodology Button : Button is NOT  visible");
			test.fail("Test failed: " + e.getMessage());
		}

		// CHECK PRESENCE of Clear BUTTON

		try {

			clearButton = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//button[.//span[text()='Clear']]")));
			// test.info("Verification of Focus Area Scatter Graph Page");
			System.out.println("***********Clear button is present.");
			// test.log(Status.PASS, "Successfully navigated and found bookmarks on Focus
			// Areas");
			test.pass("Clear Button : Button is visible");
		} catch (Exception e) {
			System.out.println("Clear Button : Button is NOT visible");
			test.fail("Test failed: " + e.getMessage());
		}
		// CHECK PRESENCE OF IMAGES
		// HIGH IMPACT, LOW IMPACT
		try {
			imgHighImpactLowScore = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector("img[alt='high impact low score'][src*='HighImpactLowScore.png']")));
			System.out.println("HighImpactLowScore : Image is visible");
			test.pass("High Impact Low Score : Image is visible");
		} catch (Exception e) {
			System.out.println("High Impact Low Score.... : Image is NOT visible");
			test.fail("Test failed: " + e.getMessage());
		}

		// HIGH IMPACT, LOW IMPACT
		try {
			imgHighImpactHighScore = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector("img[alt='high impact high score'][src*='HighImpactHighScore.png']")));
			System.out.println("HighImpactHighScore : Image is visible");
			test.pass("High Impact High Score : Image is visible");
		} catch (Exception e) {
			System.out.println("High Impact High Score.... : Image is NOT visible");
			test.fail("Test failed: " + e.getMessage());
		}
		// CHECK EMP ENGANGEMENT GRAPH
		// ✅ Wait for the element to be visible
		By elementLocator = By.id("print-title-employee-engagement-analysis");
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

		// Step 2: Take screenshot
		File originalScreenshot = element.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(originalScreenshot, new File("EmpEngGraph_pagel.png"));
		} catch (IOException e) {
			e.printStackTrace(); // or log it
		}
		// CHECK PRESENCE OF 'GOVSTRIVE FRAMEWORK ...TEXT'

		try {
			govFramworkText = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.id("print-top-text")));
			System.out.println("GOVSTRIVE FRAMEWORK.... : Text is visible");
			test.pass("GOVSTRIVE FRAMEWORK... : Text is visible");
		} catch (Exception e) {
			System.out.println("GOVSTRIVE FRAMEWORK.... : Text is NOT visible");
			test.fail("Test failed: " + e.getMessage());
		}
		// CHECK PRESENCE OF 'EMPLOYEE ENGANGEMENT ...TEXT'

		try {
			empEngText = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.id("note-Engagement")));
			System.out.println("EMPLOYEE ENGANGEMENT .... : Text is visible");
			test.pass("EMPLOYEE ENGANGEMENT ... : Text is visible");
		} catch (Exception e) {
			System.out.println("EMPLOYEE ENGANGEMENT .... : Text is NOT visible");
			test.fail("Test failed: " + e.getMessage());
		}

		// CHECK PRESENCE OF 'OPM ...TEXT'

		try {
			OPMText = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("p.MuiTypography-root-159.MuiTypography-body2-167.MuiTypography-alignCenter-182")));
			System.out.println("OPM .... : Text is visible");
			test.pass("OPM ... : Text is visible");
		} catch (Exception e) {
			System.out.println("OPM .... : Text is NOT visible");
			test.fail("Test failed: " + e.getMessage());
		}

		// CHECK PRESENCE OF 'FEVSQUESTIONAIRE ...TEXT'

		try {
			OPMText = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("span[class*='MuiButton-label']")));
			System.out.println("FEVS QUESTIONAIRE .... : Text is visible");
			test.pass("FEVS QUESTIONAIRE ... : Text is visible");
		} catch (Exception e) {
			System.out.println("FEVS QUESTIONAIRE .... : Text is NOT visible");
			test.fail("Test failed: " + e.getMessage());
		}

		// BOOKMARK PAGE
		System.out.println("*********In focus areas : Veryfing presence of elements on bookmark page");
		// Bookmark : bk_fa_11
		driver.get("https://fedviewsd.connecthr.com/focus-area?bookmark=490");

		// Wait for the <h1> with exact text "Year Selected: 2022"
		try {
			yearHeaderBk = wait.until(
					ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//h1[contains(text(), 'Year Selected')]")));
			System.out.println("Element text: " + yearHeaderBk.getText());

			test.pass("Selected year : visible as " + yearHeaderBk.getText());
		} catch (Exception e) {
			System.out.println("Selected year not visible");
			test.fail("Test failed: " + e.getMessage());
		}

		// CHECK PRESENCE of BOOKMARKS BUTTON
		try {
			bookmarksButtonBk = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//button[@title='Bookmarks' or .//span[contains(text(), 'Bookmarks')]]")));
			test.info(
					"Focus Area Scatter Graph Page, Agency ,Current Survey View :  Verification of presence of elements on Bookmark page [bk_fa_1]");
			System.out.println("************ Bookmarks button is present.");
			// test.log(Status.PASS, "Successfully navigated and found bookmarks on Focus
			// Areas");
			test.pass("Bookmarks Button : Button is visible");
		} catch (Exception e) {
			System.out.println("Bookmarks Button : Button is NOT visible");
			test.fail("Test failed: " + e.getMessage());
		}

		// LOGGING : COMPARE PAGE AND BOOKMARK PAGE
		// VERIFY HEADER
		if (yearHeaderPg.equals(yearHeaderBk)) {
			test.pass("Selected year is not same on bookmark page");
			System.out.println("Both elements refer to the same DOM element.");
		} else {
			test.fail("Test failed: Selected year is not same on bookmark page");
			System.out.println("Elements are different.");
		}
		// VERIFY BUTTONS
		// VERIFY IMAGES
		// VERIFY GRAPH
		// VERIFY TEXT

	}

	@AfterMethod
	public void afterMethod() {
		/*
		 * if (driver != null) {
		 * driver.quit();
		 * }
		 */
		System.out.println("*******Finished Test On Edge Browser");

		ExtentManager.flushReports();
	}
}
