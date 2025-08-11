package verifyClearAll;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class ClearAllPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for your dropdown and Clear All button
    private By dropdownButton = By.id("select-category");
    private By dropdownOptions = By.xpath("//ul[@role='listbox']//li"); // MUI renders <li> items
    private By clearAllButtonLocator = By.xpath("//button[contains(text(), 'Clear All')]");

    public ClearAllPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Navigate to module
    public void navigateToModule() {
        driver.get("https://fedviewsd.connecthr.com/view-fevs");
    }

    // Open dropdown
    public void openDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButton)).click();
    }

    // Get all dropdown option elements
    public List<WebElement> getAllDropdownOptions() {
        openDropdown();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownOptions));
        return driver.findElements(dropdownOptions);
    }

    // Select option by visible text
    public void selectOption(String optionText) {
        openDropdown();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownOptions));
        for (WebElement option : driver.findElements(dropdownOptions)) {
            if (option.getText().trim().equalsIgnoreCase(optionText.trim())) {
                option.click();
                break;
            }
        }
    }

    // Check if Clear All button is displayed
    public boolean isClearAllButtonDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(clearAllButtonLocator));
            return driver.findElement(clearAllButtonLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Click Clear All
    public void clickClearAll() {
        wait.until(ExpectedConditions.elementToBeClickable(clearAllButtonLocator)).click();
    }
}
