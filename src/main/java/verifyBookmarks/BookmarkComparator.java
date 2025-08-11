package verifyBookmarks;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class BookmarkComparator {

    WebDriver driver;

    public BookmarkComparator(WebDriver driver) {
        this.driver = driver;
    }

    // Extract buttons, links, and full page text
    public List<String> capturePageElements() {
        List<String> elements = new ArrayList<>();

        // Extract button details
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        for (int i = 0; i < buttons.size(); i++) {
            String text = buttons.get(i).getText().trim();
            String id = buttons.get(i).getAttribute("id");
            elements.add("Button[" + i + "]: Text=" + text + ", ID=" + id);
        }

        // Extract link details
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (int i = 0; i < links.size(); i++) {
            String text = links.get(i).getText().trim();
            String href = links.get(i).getAttribute("href");
            elements.add("Link[" + i + "]: Text=" + text + ", Href=" + href);
        }

        // Extract normalized page text
        String bodyText = driver.findElement(By.tagName("body")).getText().trim();
        String normalizedText = bodyText.replaceAll("\\s+", " ");
        elements.add("FullText=" + normalizedText);

        // Print elements to console or report
        for (String element : elements) {
            System.out.println("####_$$$$$_@@@@ : " + element); // Or log to Extent Report
        }

        return elements;
    }

    // Compare two pages and log result to Extent Report
    public boolean compare(List<String> original, List<String> bookmark, ExtentTest test) {
        test.info("🔍 Comparing Live and Bookmark pages...");

        if (original.equals(bookmark)) {
            test.pass("✅ Pages match exactly.");
            return true;
        } else {
            test.fail("❌ Pages do not match. Showing differences:");

            Set<String> onlyInOriginal = new LinkedHashSet<>(original);
            onlyInOriginal.removeAll(bookmark);

            Set<String> onlyInBookmark = new LinkedHashSet<>(bookmark);
            onlyInBookmark.removeAll(original);

            if (!onlyInOriginal.isEmpty()) {
                test.info("🔷 Present only in Live Page:");
                for (String line : onlyInOriginal) {
                    test.info("  " + line);
                }
            }

            if (!onlyInBookmark.isEmpty()) {
                test.info("🔶 Present only in Bookmark Page:");
                for (String line : onlyInBookmark) {
                    test.info("  " + line);
                }
            }

            return false;
        }
    }
}
