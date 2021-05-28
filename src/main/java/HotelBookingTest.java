import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest extends BaseTest {

    @Test
    public void shouldBeAbleToSearchForHotels() {
        driver.findElement(By.linkText("Hotels")).click();

        driver.findElement(By.id("Tags")).sendKeys("Indiranagar, Bangalore");

        new Select(driver.findElement(By.id("travellersOnhome"))).selectByVisibleText("2 rooms, 4 adults");
        driver.findElement(By.id("SearchHotelsButton")).click();
    }

}
