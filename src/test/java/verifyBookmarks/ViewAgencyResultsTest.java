package verifyBookmarks;

import java.util.List;

import org.testng.annotations.Test;

public class ViewAgencyResultsTest extends BaseTest {
    @Test(priority = 1)
    public void testViewAgencyResults() {
        test = ExtentManager.createTest("View Agency Results");
        ViewAgencyResultsPage page = new ViewAgencyResultsPage(driver, wait);
        BookmarkComparator comparator1 = new BookmarkComparator(driver);
        // page.openAndVerify();
        test.info("Opened and verified View Agency Results module");

        // Step 1: Navigate to Live Page and capture elements
        page.navigateToLivePage();
        test.info("Navigated to Live Page");
        List<String> liveData = comparator1.capturePageElements();
        test.info("Captured elements from Live Page");

        // Step 2: Navigate to Bookmark Page and capture elements
        page.navigateToBookmark();
        test.info("Navigated to Bookmark Page");
        List<String> bookmarkData = comparator1.capturePageElements();
        test.info("Captured elements from Bookmark Page");

        // Step 3: Compare both and log to report
        boolean result = comparator1.compare(liveData, bookmarkData, test);
        if (result) {
            test.pass("✅ Bookmark matches the Live Page elements.");
        } else {
            test.fail("❌ Bookmark does not match Live Page elements. See differences above.");
        }

        // Step 4: Open and verify module UI (optional)
        page.openAndVerify();
        test.info("✅ View Agency Results module opened and verified.");
    }

}
