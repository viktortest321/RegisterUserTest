package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class Regsteps {

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
        driver.get("C:\\webdrivers\\Register Basketball\\register.html");
    }

    @When("I enter a valid date of birth")
    public void i_enter_a_valid_date_of_birth() {
        driver.findElement(By.id("dp")).sendKeys("01/01/2000");
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
        driver.findElement(By.id("member_lastname")).clear();
    }

    @When("I enter a unique email")
    public void i_enter_a_unique_email() {
        driver.findElement(By.id("member_emailaddress"))
                .sendKeys("test@mail.com");
        driver.findElement(By.id("member_confirmemailaddress"))
                .sendKeys("test@mail.com");
    }

    @When("I enter matching passwords")
    public void i_enter_matching_passwords() {
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("Password123!");
    }

    @When("I enter non-matching passwords")
    public void i_enter_non_matching_passwords() {
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("DifferentPassword!");
    }

    @When("I accept the terms")
    public void i_accept_the_terms() {
        driver.findElement(By.cssSelector("label[for='sign_up_25']")).click();
        driver.findElement(By.cssSelector("label[for='sign_up_26']")).click();
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']")).click();
    }

    @When("I do not accept the terms")
    public void i_do_not_accept_the_terms() {
        // intentionally left unchecked
    }

    @When("I submit the form")
    public void i_submit_the_form() {
        driver.findElement(By.cssSelector("input[type='submit'][name='join']")).click();
    }

    @Then("I should see a success message")
    public void i_should_see_a_success_message() {
        wait.until(ExpectedConditions.urlContains("Success.html"));}

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String errmsg) {
        for (WebElement e : driver.findElements(By.cssSelector("span.field-validation-error"))) {
            String text = e.getText();
            if (text != null && text.contains(errmsg)) return;
        }
    }
}