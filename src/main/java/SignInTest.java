import com.sun.javafx.PlatformUtil;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();

        driver.switchTo().frame("modal_window");
        driver.findElement(By.id("signInButton")).click();

        String errorMessage = driver.findElement(By.id("errors1")).getText();
        driver.switchTo().defaultContent();
        softAssert.assertTrue(errorMessage.contains("There were errors in your submission"));

        softAssert.assertAll();;
    }
}
