package verifyBookmarks;

import org.testng.annotations.Test;

public class FocusAreasTest extends BaseTest {
    @Test(priority = 3)
    public void testFocusAreas() {
        test = ExtentManager.createTest("Focus Areas");
        FocusAreasPage page = new FocusAreasPage(driver, wait);
        page.openAndVerify();
        test.info("Opened and verified Focus Areas module");
    }
}
