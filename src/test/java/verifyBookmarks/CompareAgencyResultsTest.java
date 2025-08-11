package verifyBookmarks;

import org.testng.annotations.Test;

public class CompareAgencyResultsTest extends BaseTest {
    @Test(priority = 2)
    public void testCompareAgencyResults() {
        test = ExtentManager.createTest("Compare Agency Results");
        CompareAgencyResultsPage page = new CompareAgencyResultsPage(driver, wait);
        page.openAndVerify();
        test.info("Opened and verified Compare Agency Results module");
    }
}
