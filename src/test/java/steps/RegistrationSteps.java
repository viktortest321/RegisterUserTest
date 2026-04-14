package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class RegistrationSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I open the registration page")
    public void i_open_the_registration_page() {
        driver.get("C:\\webdrivers\\mockpage.html");
    }

    @When("I enter a valid first name")
    public void i_enter_a_valid_first_name() {
        driver.findElement(By.id("member_firstname")).sendKeys("Test");
    }

    @When("I enter a valid last name")
    public void i_enter_a_valid_last_name() {
        driver.findElement(By.id("member_lastname")).sendKeys("User");
    }

    @When("I leave the last name empty")
    public void i_leave_the_last_name_empty() {
        driver.findElement(By.id("member_lastname")).sendKeys("");
    }

    @When("I enter a unique email")
    public void i_enter_a_unique_email() {
        driver.findElement(By.id("member_emailaddress"))
                .sendKeys("test" + System.currentTimeMillis() + "@mail.com");
    }

    @When("I enter matching passwords")
    public void i_enter_matching_passwords() {
        driver.findElement(By.id("member_password")).sendKeys("Password123!");
        driver.findElement(By.id("member_confirm_password")).sendKeys("Password123!");
    }

    @When("I enter non-matching passwords")
    public void i_enter_non_matching_passwords() {
        driver.findElement(By.id("member_password")).sendKeys("Password123!");
        driver.findElement(By.id("member_confirm_password")).sendKeys("DifferentPassword!");
    }

    @When("I accept the terms")
    public void i_accept_the_terms() {
        driver.findElement(By.id("member_terms")).click();
    }

    @When("I do not accept the terms")
    public void i_do_not_accept_the_terms() {
        // intentionally left unchecked
    }

    @When("I submit the form")
    public void i_submit_the_form() {
        driver.findElement(By.id("submit")).click();
    }

    @Then("I should see a success message")
    public void i_should_see_a_success_message() {
        WebElement success = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("successBox"))
        );
        assert success.isDisplayed();
    }

    @Then("I should see the error message {string}")
    public void i_should_see_the_error_message(String expectedMessage) {
        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("errorBox"))
        );
        assert error.getText().equals(expectedMessage);
    }
}

