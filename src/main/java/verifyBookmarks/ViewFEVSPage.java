package verifyBookmarks;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class ViewFEVSPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ViewFEVSPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openAndVerify() {
        /*
         * WebElement viewFEVS = wait.until(ExpectedConditions.elementToBeClickable(
         * By.xpath("//img[@alt='View FEVS']"))); // <-- adjust locator
         * viewFEVS.click();
         */
        driver.get("https://fedviewsd.connecthr.com/view-fevs");
        System.out.println("View FEVS Opened.");

        WebElement returnButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='Return to Selection Menu']]")));
        returnButton.click();
        System.out.println("Returned from View FEVS.");
    }
}
