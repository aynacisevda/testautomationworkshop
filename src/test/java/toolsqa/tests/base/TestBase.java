package toolsqa.tests.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.remote.BrowserType.*;

public class TestBase {

    private WebDriver driver;

    protected WebDriver setBrowser(String browserType)
    {
        if (driver == null)
            switch (browserType){
                case CHROME:
                    System.setProperty("webdriver.chrome.driver","src\\test\\resources\\drivers\\chromedriver.exe");
                    System.out.println("Launching Chrome Browser");
                    ChromeOptions options = new ChromeOptions();
                    //To download crx file use http://crxextractor.com/
                    options.addExtensions(new File("src\\test\\resources\\extensions\\resolve_captcha_ext.crx"));
                    driver = new ChromeDriver(options);
                    //If you don't want to bypass captcha use webDriver Manager for chrome browser too.
                    //WebDriverManager.chromedriver().setup();
                    break;
                case IE:
                    System.out.println("Launching IE Browser");
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case FIREFOX:
                    System.out.println("Launching Firefox Browser");
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalStateException("Unexpected browser value: " + browserType);
            }
        return driver;
    }

    protected WebDriver goToPage(String URL) {
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        try {
            driver.get(URL);
        }
        catch (Exception exception){
            System.out.println("TimeOut Exception is caught : Home Page is not loaded within 20 seconds!" + exception.getMessage());
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    protected WebElement waitForElement(WebElement element, long timeoutsInSeconds)
    {  try {
        WebDriverWait wait = new WebDriverWait(driver, timeoutsInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
          }
          catch (Exception exception){
              System.out.println("Element is not found or visible exception" + exception.getMessage());
              return null;
         }
    }

    protected void quitDriver() {
        driver.quit();
    }
}
