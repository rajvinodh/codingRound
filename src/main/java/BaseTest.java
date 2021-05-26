import com.sun.javafx.PlatformUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeClass
    void startBrowser() {
        setDriverPath();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeMethod
    void launchBrowser() {
        softAssert = new SoftAssert();
        driver.get("https://www.cleartrip.com/");
    }

    @AfterMethod
    void closeBrowser() {
        takeScreenshot();
        driver.close();
    }

    @AfterClass
    void stopBrowser() {
        driver.quit();
    }

    protected void takeScreenshot() {
        TakesScreenshot takesScreenshot = ((TakesScreenshot)driver);
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("./reports/" + System.currentTimeMillis() + ".png");
        try {
            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("Screenshot taken : " + destinationFile.getAbsolutePath());
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
}
