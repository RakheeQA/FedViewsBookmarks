package verifyBookmarks;

import org.testng.annotations.Test;

public class ViewAgencyResultsTest extends BaseTest {
    @Test(priority = 1)
    public void testViewAgencyResults() {
        test = ExtentManager.createTest("View Agency Results");
        ViewAgencyResultsPage page = new ViewAgencyResultsPage(driver, wait);
        page.openAndVerify();
        test.info("Opened and verified View Agency Results module");
    }
}
