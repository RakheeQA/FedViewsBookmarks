package verifyBookmarks;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class ViewAgencyResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ViewAgencyResultsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openAndVerify() {
        WebElement viewResults = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//img[@alt='View Analysis by Question, Index, or Key Trends Over Time']")));
        viewResults.click();
        System.out.println("View Agency Results Opened.");

        WebElement returnButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='Return to Selection Menu']]")));
        returnButton.click();
        System.out.println("Returned from View Agency Results.");
    }

    public void navigateToLivePage() {
        String liveUrl = "https://fedviewsd.connecthr.com/view-agency";
        driver.get(liveUrl);
        System.out.println("Navigated to live page: " + liveUrl);
    }

    // Navigates to the bookmark page (saved snapshot)
    public void navigateToBookmark() {
        // String bookmarkUrl =
        // "https://fedviewsd.connecthr.com/focus-area?bookmark=490";
        String bookmarkUrl = "https://fedviewsd.connecthr.com/view-agency?bookmark=472";
        driver.get(bookmarkUrl);
        System.out.println("Navigated to bookmark page: " + bookmarkUrl);
    }
}
