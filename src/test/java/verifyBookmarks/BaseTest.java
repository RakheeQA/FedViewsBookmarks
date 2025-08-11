package verifyBookmarks;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        extent = ExtentManager.getExtentReports();
        System.out.println("Extent report initialized");

        // Launch browser only once
        System.setProperty("webdriver.edge.driver", "C:\\Users\\govstrive2\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Login only once
        loginToApplication();
    }

    private void loginToApplication() {
        driver.get("https://fedviewsd.connecthr.com/");
        wait.until(wd -> ((JavascriptExecutor) wd)
                .executeScript("return document.readyState").equals("complete"));

        driver.findElement(By.id("username")).sendKeys("testqa1@govstrive.com");
        driver.findElement(By.id("password")).sendKeys("Pineapple@12345");
        driver.findElements(By.cssSelector(".btn.btn-primary.new-btn-design")).get(0).click();
        driver.findElements(By.cssSelector(".btn.btn-light.new-btn-design")).get(0).click();

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("select-agency_unit")));
        dropdown.click();
        WebElement agencyOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[normalize-space()='Internal Revenue Service']")));
        agencyOption.click();

        WebElement currentSurvey = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[normalize-space()='Current Survey']")));
        currentSurvey.click();

        WebElement launchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[normalize-space()='Launch']]")));
        launchButton.click();

        System.out.println("Login + Agency selection completed.");
    }

    @AfterMethod(alwaysRun = true)
    public void logTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("***********If No 1");
            test.pass(result.getMethod().getMethodName() + " passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String path = ScreenshotUtility.captureScreenshot(driver, result.getMethod().getMethodName());
            System.out.println("***************If No 2");
            test.fail(result.getThrowable()).addScreenCaptureFromPath(path);
        } else if (result.getStatus() == ITestResult.SKIP) {
            System.out.println("***************If No 3");
            test.skip("Test Skipped: " + result.getMethod().getMethodName());
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        if (driver != null)
            // driver.quit();
            ExtentManager.flushReports();
        System.out.println("Extent report flushed and browser closed");
    }
}
