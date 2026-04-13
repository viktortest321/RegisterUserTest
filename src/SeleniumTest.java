import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        driver.findElement(By.id("member_firstname")).sendKeys("Test");
        driver.findElement(By.id("member_lastname")).sendKeys("User");
        driver.findElement(By.id("member_emailaddress")).sendKeys("testuser" + System.currentTimeMillis() + "@mail.com");
        driver.findElement(By.id("member_password")).sendKeys("Password123!");
        driver.findElement(By.id("member_confirm_password")).sendKeys("Password123!");
        driver.findElement(By.id("member_terms")).click();
        driver.findElement(By.id("submit")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        driver.quit();
}
}