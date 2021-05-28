import com.sun.javafx.PlatformUtil;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class FlightBookingTest extends BaseTest {

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();

        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        List<WebElement> selectableDate = driver.findElements(By.xpath("//a[@class=\"ui-state-default \"]"));
        WebElement randomDate = selectableDate.get(new Random().nextInt(selectableDate.size()));
        String dateSelected = randomDate.getText();
        randomDate.click();
        System.out.println("Selected Date: " + dateSelected);

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        //verify that result appears for the provided journey search
        softAssert.assertTrue(isElementPresent(By.className("searchSummary")));

        softAssert.assertAll();
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
