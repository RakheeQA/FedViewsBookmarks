package verifyBookmarks;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class CompareAgencyResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CompareAgencyResultsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openAndVerify() {
        /*
         * WebElement compareResults =
         * wait.until(ExpectedConditions.elementToBeClickable(
         * By.xpath("//img[@alt='Compare Agency Results']"))); // <-- adjust locator
         * compareResults.click();
         */
        driver.get("https://fedviewsd.connecthr.com/compare-view");

        System.out.println("Compare Agency Results Opened.");

        WebElement returnButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='Return to Selection Menu']]")));
        returnButton.click();
        System.out.println("Returned from Compare Agency Results.");
    }
}
