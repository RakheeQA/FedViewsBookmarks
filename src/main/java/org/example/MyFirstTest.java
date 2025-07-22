package org.example;

import java.time.Duration;
//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyFirstTest {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.edgedriver().setup(); // Auto-downloads EdgeDriver
        // EdgeOptions options6 = new EdgeOptions();
        // options6.addArguments("--headless=new"); // Use `new` headless mode for
        // better compatibility
        // options6.addArguments("--disable-gpu"); // Recommended for Windows

        WebDriver driver = new EdgeDriver(); // Use EdgeDriver instead of ChromeDriver

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://fedchartd.connecthr.com/");
        // Wait for page to load completely
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        System.out.println("Page title is: " + driver.getTitle());
        Thread.sleep(5000);

        // Wait for the login form to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

        // Perform Login
        driver.findElement(By.id("username")).sendKeys("rudresh051@govstrive.com");// email
        driver.findElement(By.id("password")).sendKeys("Rudresh1008387#");// password
        driver.findElements(By.cssSelector(".btn.btn-primary.new-btn-design")).get(0).click();// login button
        driver.findElements(By.cssSelector(".btn.btn-light.new-btn-design")).get(0).click();// continue button
        System.out.println("Clicked Continue. Waiting for loader to disappear...");
        // Wait for the progress bar to disappear
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(
        // By.cssSelector(".MuiLinearProgress-root")
        // ));
        // System.out.println("Loader disappeared");
        // ((JavascriptExecutor)
        // driver).executeScript("document.body.style.zoom='0.7'"); // 70% zoom

        // driver.findElement(By.id("left_menu_nav")).click(); // Click on hamburger
        // menu
        // driver.findElement(By.id("push_ping_icon")).click(); // Click on pin button
        // using ID
        // driver.findElement(By.xpath("//span[normalize-space(text())='CENTERS FOR
        // DISEASE CONTROL AND PREVENTION']")).click();// click on agency name

        // Thread.sleep(2000);
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".MuiLinearProgress-root")));
        // // wait for loader to disappear

        // // Navigate to 14
        // WebElement toggler99 = driver.findElement(By.xpath("//span[text()='99-CENTERS
        // FOR DISEASE CONTROL AND
        // PREVENTION']/preceding-sibling::a[contains(@class,'jqtree-toggler')]"));
        // toggler99.click();
        // Thread.sleep(1000); // wait for child nodes to load
        // // Expand the node by clicking the toggler
        // driver.findElement(By.xpath("//span[text()='14-OFFICE OF STRATEGIC
        // PARTNERSHIP']/preceding-sibling::a")).click();
        // // Optional: wait a bit for expansion
        // Thread.sleep(1000);
        // // Then click the actual span
        // // driver.findElement(By.xpath("//span[text()='14-OFFICE OF STRATEGIC
        // PARTNERSHIP']")).click();
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".MuiLinearProgress-root")));
        // // wait for loader to disappear
        // // Navigate to 1401
        // // Click on 1401
        // driver.findElement(By.xpath("//span[text()='01-PLANNING AND
        // POLICY']")).click();
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".MuiLinearProgress-root")));
        // // wait for loader to disappear

        // for (int i = 1; i <= 500; i++) {
        // // Modal Form
        // driver.findElement(By.xpath("//button[@class='btn btn-primary
        // govstrive-button1']")).click(); // click on Add/Remove position at navbar
        // driver.findElement(By.xpath("//button[text()='Add New Position']")).click();
        // // click on add new position button for modal

        // // Occ series
        // WebElement occSeriesInput =
        // wait.until(ExpectedConditions.presenceOfElementLocated(
        // By.xpath("(//div[@class='row form-group'])[3]//input")
        // ));
        // ((JavascriptExecutor)
        // driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
        // occSeriesInput);
        // occSeriesInput.sendKeys("1111");

        // // Career ladder position
        // // Step 1: Get all similar elements and select the 16th (Career Ladder)
        // List<WebElement> dropdowns =
        // driver.findElements(By.cssSelector(".search-wrapper.searchWrapper.singleSelect"));
        // WebElement careerLadderDropdown = dropdowns.get(15); // 15th element (0-based
        // indexing)
        // // Step 2: Click to open the dropdown
        // ((JavascriptExecutor)
        // driver).executeScript("arguments[0].scrollIntoView(true);",
        // careerLadderDropdown);
        // careerLadderDropdown.click();
        // Thread.sleep(500); // Wait for dropdown to load
        // // Step 3: Find and click the "No" option
        // List<WebElement> options = driver.findElements(By.cssSelector(".option")); //
        // Update this if needed
        // for (WebElement option : options) {
        // if (option.getText().trim().equalsIgnoreCase("No")) {
        // option.click();
        // break;
        // }
        // }
        // Thread.sleep(3000);

        // // Grade
        // // Get all matching elements
        // List<WebElement> gradeContainers =
        // driver.findElements(By.cssSelector(".search-wrapper.searchWrapper.singleSelect"));
        // // Select the 17th one (index 16, since Java lists are 0-based)
        // WebElement gradeContainer = gradeContainers.get(16);
        // // Step 2: Scroll the grade field into view
        // ((JavascriptExecutor)
        // driver).executeScript("arguments[0].scrollIntoView(true);", gradeContainer);
        // Thread.sleep(500); // Optional pause for UI animation
        // // Step 3: Click on the dropdown to activate it
        // gradeContainer.click();
        // Thread.sleep(500); // Wait for dropdown to render
        // // Step 4: Select "00" from the dropdown
        // // This assumes that dropdown options are now visible in the DOM
        // List<WebElement> options1 =
        // driver.findElements(By.cssSelector(".multiselect-container .option"));

        // for (WebElement option : options1) {
        // if (option.getText().trim().equals("00")) {
        // option.click();
        // break;
        // }
        // }
        // Thread.sleep(3000);

        // // Select Yes in HAR
        // // Get all matching elements
        // List<WebElement> harContainers =
        // driver.findElements(By.cssSelector(".search-wrapper.searchWrapper.singleSelect"));
        // // Select the 18th one (index 17, since Java lists are 0-based)
        // WebElement harContainer = gradeContainers.get(17);
        // // Step 2: Scroll the grade field into view
        // ((JavascriptExecutor)
        // driver).executeScript("arguments[0].scrollIntoView(true);", harContainer);
        // Thread.sleep(500); // Optional pause for UI animation
        // // Step 3: Click on the dropdown to activate it
        // harContainer.click();
        // Thread.sleep(500); // Wait for dropdown to render
        // // Step 4: Select "Yes" from the dropdown
        // // This assumes that dropdown options are now visible in the DOM
        // List<WebElement> options2 =
        // driver.findElements(By.cssSelector(".multiselect-container .option"));
        // for (WebElement option : options2) {
        // if (option.getText().trim().equals("Yes")) {
        // option.click();
        // break;
        // }
        // }
        // Thread.sleep(3000);

        // // Budget variables
        // // Step 1: Click the dropdown input to show options
        // WebElement inputBox =
        // driver.findElement(By.id("budgetVariableSelect_input"));
        // inputBox.click();
        // // Step 2: Wait for the options list to be visible
        // wait.until(ExpectedConditions.visibilityOfElementLocated(
        // By.cssSelector("#budgetVariableSelect .optionListContainer ul.optionContainer
        // li.option"))
        // );
        // // Step 3: Select the specific value
        // List<WebElement> options3 = driver.findElements(
        // By.cssSelector("#budgetVariableSelect .optionListContainer ul.optionContainer
        // li.option")
        // );
        // for (WebElement option : options3) {
        // if (option.getText().trim().equals("AC00,104,AN001")) {
        // ((JavascriptExecutor)
        // driver).executeScript("arguments[0].scrollIntoView(true);", option);
        // ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        // // Use JS click for robustness
        // break;
        // }
        // }
        // Thread.sleep(3000);

        // // Click on Confirm and submit button
        // WebElement confirmButton =
        // wait.until(ExpectedConditions.elementToBeClickable(
        // By.xpath("//button[contains(text(),'Confirm') and
        // contains(text(),'Submit')]")
        // ));
        // confirmButton.click();
        // Thread.sleep(3000);
        // WebElement proceedButton =
        // wait.until(ExpectedConditions.elementToBeClickable(
        // By.xpath("//button[text()='Yes, Proceed']")));
        // proceedButton.click();
        // Thread.sleep(3000);
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".MuiLinearProgress-root")));
        // // wait for loader to disappear

        // // Click on view PD button
        // WebElement viewPdButton = wait.until(ExpectedConditions.elementToBeClickable(
        // By.xpath("//div[@class='innerTitleWrapper']/span[text()='View PD']")));
        // viewPdButton.click();
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".MuiLinearProgress-root")));
        // // wait for loader to disappear

        // // select occ series
        // // Step 1: Click the visible input to open dropdown
        // WebElement occSeriesInput1 =
        // driver.findElement(By.cssSelector("input.select-search-box__search"));
        // occSeriesInput1.click();
        // // Step 2: Wait for options container to be visible
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.select-search-box__options")));
        // // Step 3: Find the option with data-value='0201'
        // WebElement occOption =
        // driver.findElement(By.cssSelector("li.select-search-box__option[data-value='0201']"));
        // // Step 4: Scroll into view and click using JS (bypassing React synthetic
        // events)
        // ((JavascriptExecutor)
        // driver).executeScript("arguments[0].scrollIntoView(true);", occOption);
        // ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
        // occOption);

        // // click search button
        // List<WebElement> searchButtons =
        // driver.findElements(By.cssSelector("button.btn.btn-primary.new-btn-design"));
        // searchButtons.get(2).click(); // Index 2 for the 3rd button
        // List<WebElement> searchButtons1 =
        // wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
        // By.cssSelector("button.btn.btn-primary.new-btn-design")
        // ));
        // searchButtons1.get(2).click();
        // Thread.sleep(3000);

        // // click on PD attachment
        // WebElement icon =
        // wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("tr.pdDataRows")))
        // .get(2)
        // .findElement(By.cssSelector("td:first-child svg"));
        // // Scroll into view using JavaScript
        // ((JavascriptExecutor)
        // driver).executeScript("arguments[0].scrollIntoView(true);", icon);
        // // Locate the first row's first column (attachment icon span)
        // WebElement attachmentIcon =
        // driver.findElement(By.xpath("//tr[@class='pdDataRows'][1]/td[1]"));
        // // Click the icon
        // attachmentIcon.click();
        // // Wait for 3 seconds
        // Thread.sleep(7000);

        // // "No or Yes Modal"
        // try {
        // // Try to find and click the "No" button if it appears
        // WebElement noButton =
        // wait.until(ExpectedConditions.visibilityOfElementLocated(
        // By.xpath("//button[text()='No' and contains(@class, 'btn-light')]")
        // ));
        // ((JavascriptExecutor)
        // driver).executeScript("arguments[0].scrollIntoView(true);", noButton);
        // noButton.click();
        // System.out.println("Clicked on 'No' button.");
        // } catch (TimeoutException e) {
        // // "No" button did not appear — continue to next step
        // System.out.println("'No' button not found, skipping it.");
        // }
        // // Click the "Attach & Close" button in all cases
        // WebElement attachAndClose =
        // wait.until(ExpectedConditions.elementToBeClickable(
        // By.xpath("//button[contains(text(), 'Attach') and contains(@class,
        // 'btn-primary')]")
        // ));
        // ((JavascriptExecutor)
        // driver).executeScript("arguments[0].scrollIntoView(true);", attachAndClose);
        // attachAndClose.click();
        // System.out.println("Clicked on 'Attach & Close' button.");
        // Thread.sleep(3000);

        // // Back to HAR modal
        // JavascriptExecutor js = (JavascriptExecutor) driver;
        // // 1. Click on the dropdown and select the first non-empty option
        // WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
        // By.name("REASON")
        // ));
        // js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
        // dropdown.click();

        // Select reasonSelect = new Select(dropdown);
        // reasonSelect.selectByIndex(1); // Select the first non-empty option
        // System.out.println("Selected first option in dropdown.");

        // // 2. Click on "Complete & Submit" button
        // WebElement completeSubmit =
        // wait.until(ExpectedConditions.elementToBeClickable(
        // By.xpath("//button[contains(text(), 'Complete') and contains(text(),
        // 'Submit')]")
        // ));
        // js.executeScript("arguments[0].scrollIntoView(true);", completeSubmit);
        // completeSubmit.click();
        // System.out.println("Clicked on 'Complete & Submit' button.");
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(
        // By.cssSelector(".MuiLinearProgress-root")
        // ));

        // // 3. Click on "Yes, Proceed" button
        // WebElement yesProceed = wait.until(ExpectedConditions.elementToBeClickable(
        // By.xpath("//button[contains(text(), 'Yes') and contains(text(), 'Proceed')]")
        // ));
        // js.executeScript("arguments[0].scrollIntoView(true);", yesProceed);
        // yesProceed.click();
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(
        // By.cssSelector(".MuiLinearProgress-root")
        // ));
        // System.out.println("Clicked on 'Yes, Proceed' button.");

        // WebElement noPrintLaterBtn =
        // wait.until(ExpectedConditions.elementToBeClickable(
        // By.xpath("//button[contains(text(), 'No, Print Later')]")
        // ));
        // js.executeScript("arguments[0].scrollIntoView(true);", noPrintLaterBtn);
        // noPrintLaterBtn.click();
        // System.out.println("Clicked on 'No, Print Later' button.");

        // Thread.sleep(10000);
        // }

        // Logout
        driver.findElement(By.xpath("//a[@href='/Home/Logout']")).click();
        driver.quit();
    }
}
