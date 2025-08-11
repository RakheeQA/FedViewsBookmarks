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
}
