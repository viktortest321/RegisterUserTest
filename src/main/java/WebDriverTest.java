import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class WebDriverTest {
    public static void main(String[] args) {

        // Point to your chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("Selenium WebDriver");
        search.submit();

        driver.quit();
    }
}
