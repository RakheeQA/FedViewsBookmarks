package verifyClearAll;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import verifyBookmarks.BaseTest; // using  existing BaseTest
import verifyBookmarks.ExtentManager;

public class ClearAllTest extends BaseTest {

    @Test
    public void testClearAllButtonPresenceForAllDropdownOptions() {
        test = ExtentManager.createTest("Clear All Button Presence Test");

        ClearAllPage clearAllPage = new ClearAllPage(driver, wait);

        // Step 1: Navigate to module
        clearAllPage.navigateToModule();
        test.info("Navigated to Clear All module");

        // Step 2: Get dropdown options
        List<WebElement> options = clearAllPage.getAllDropdownOptions();
        test.info("Found " + options.size() + " dropdown options");

        // Step 3: Loop through options and verify Clear All button
        for (WebElement option : options) {
            String optionText = option.getText().trim();
            if (optionText.isEmpty())
                continue; // skip empty options if any

            test.info("Testing option: " + optionText);
            clearAllPage.selectOption(optionText);

            boolean isDisplayed = clearAllPage.isClearAllButtonDisplayed();
            Assert.assertTrue(isDisplayed, "Clear All button NOT displayed for: " + optionText);
            test.pass("Clear All button is displayed for: " + optionText);
            System.out.println("Clear All button is displayed for: " + optionText);
        }
    }
}
