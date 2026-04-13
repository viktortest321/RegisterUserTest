import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestMockPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private void openMockPage() {
        driver.get("C:\\webdrivers\\mockpage.html");
    }

    // ---------------------------------------------------------
    // TEST 1: Skapa användare (success)
    // ---------------------------------------------------------
    @Test
    public void createUserSuccessfully() {
        openMockPage();

        driver.findElement(By.id("member_firstname")).sendKeys("Test");
        driver.findElement(By.id("member_lastname")).sendKeys("User");
        driver.findElement(By.id("member_emailaddress"))
                .sendKeys("test" + System.currentTimeMillis() + "@mail.com");

        driver.findElement(By.id("member_password")).sendKeys("Password123!");
        driver.findElement(By.id("member_confirm_password")).sendKeys("Password123!");
        driver.findElement(By.id("member_terms")).click();

        driver.findElement(By.id("submit")).click();

        WebElement successBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("successBox"))
        );

        assert(successBox.isDisplayed());
    }

    // ---------------------------------------------------------
    // TEST 2: Saknar efternamn
    // ---------------------------------------------------------
    @Test
    public void createUserWithoutLastName() {
        openMockPage();

        driver.findElement(By.id("member_firstname")).sendKeys("Test");
        driver.findElement(By.id("member_lastname")).sendKeys(""); // lämnas tomt
        driver.findElement(By.id("member_emailaddress"))
                .sendKeys("test" + System.currentTimeMillis() + "@mail.com");

        driver.findElement(By.id("member_password")).sendKeys("Password123!");
        driver.findElement(By.id("member_confirm_password")).sendKeys("Password123!");
        driver.findElement(By.id("member_terms")).click();

        driver.findElement(By.id("submit")).click();

        WebElement errorBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("errorBox"))
        );

        assert(errorBox.getText().equals("Last Name is required"));
    }

    // ---------------------------------------------------------
    // TEST 3: Lösenorden matchar inte
    // ---------------------------------------------------------
    @Test
    public void passwordMismatchShowsError() {
        openMockPage();

        driver.findElement(By.id("member_firstname")).sendKeys("Test");
        driver.findElement(By.id("member_lastname")).sendKeys("User");
        driver.findElement(By.id("member_emailaddress"))
                .sendKeys("test" + System.currentTimeMillis() + "@mail.com");

        driver.findElement(By.id("member_password")).sendKeys("Password123!");
        driver.findElement(By.id("member_confirm_password")).sendKeys("DifferentPassword!");

        driver.findElement(By.id("member_terms")).click();
        driver.findElement(By.id("submit")).click();

        WebElement errorBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("errorBox"))
        );

        assert(errorBox.getText().equals("Passwords do not match"));
    }

    // ---------------------------------------------------------
    // TEST 4: Villkoren ej godkända
    // ---------------------------------------------------------
    @Test
    public void termsNotAcceptedShowsError() {
        openMockPage();

        driver.findElement(By.id("member_firstname")).sendKeys("Test");
        driver.findElement(By.id("member_lastname")).sendKeys("User");
        driver.findElement(By.id("member_emailaddress"))
                .sendKeys("test" + System.currentTimeMillis() + "@mail.com");

        driver.findElement(By.id("member_password")).sendKeys("Password123!");
        driver.findElement(By.id("member_confirm_password")).sendKeys("Password123!");

        // OBS: Vi klickar INTE i villkoren
        driver.findElement(By.id("submit")).click();

        WebElement errorBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("errorBox"))
        );

        assert(errorBox.getText().equals("You must accept the terms"));
    }
}

