package verifyBookmarks;

import org.testng.annotations.Test;

public class ViewFEVSTest extends BaseTest {
    @Test(priority = 4)
    public void testViewFEVS() {
        test = ExtentManager.createTest("View FEVS");
        ViewFEVSPage page = new ViewFEVSPage(driver, wait);
        page.openAndVerify();
        test.info("Opened and verified View FEVS module");
    }
}
