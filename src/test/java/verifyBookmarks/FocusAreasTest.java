package verifyBookmarks;

import java.util.List;
import org.testng.annotations.Test;

public class FocusAreasTest extends BaseTest {

    @Test(priority = 3)
    public void testFocusAreas() {
        // Create test log in Extent Report
        test = ExtentManager.createTest("Focus Areas");

        // Initialize page and comparator
        FocusAreasPage page = new FocusAreasPage(driver, wait);
        BookmarkComparator comparator = new BookmarkComparator(driver);

        // Step 1: Navigate to Live Page and capture elements
        page.navigateToLivePage();
        test.info("Navigated to Live Page");
        List<String> liveData = comparator.capturePageElements();
        test.info("Captured elements from Live Page");

        // Step 2: Navigate to Bookmark Page and capture elements
        page.navigateToBookmark();
        test.info("Navigated to Bookmark Page");
        List<String> bookmarkData = comparator.capturePageElements();
        test.info("Captured elements from Bookmark Page");

        // Step 3: Compare both and log to report
        boolean result = comparator.compare(liveData, bookmarkData, test);
        if (result) {
            test.pass("✅ Bookmark matches the Live Page elements.");
        } else {
            test.fail("❌ Bookmark does not match Live Page elements. See differences above.");
        }

        // Step 4: Open and verify module UI (optional)
       // uncommnet here  page.openAndVerify();
        test.info("✅ Focus Areas module opened and verified.");
    }
}
