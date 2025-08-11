package verifyBookmarks;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class FocusAreasPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public FocusAreasPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openAndVerify() {
        /*
         * WebElement focusAreas = wait.until(ExpectedConditions.elementToBeClickable(
         * By.xpath("//img[@alt='Focus Areas']"))); // <-- adjust locator
         * focusAreas.click();
         */
        driver.get("https://fedviewsd.connecthr.com/focus-area");
        System.out.println("Focus Areas Opened.");

        WebElement returnButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='Return to Selection Menu']]")));
        returnButton.click();
        System.out.println("Returned from Focus Areas.");
    }
}
