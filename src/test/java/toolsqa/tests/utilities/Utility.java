package toolsqa.tests.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utility {

    private WebDriver driver;

    public Utility(WebDriver driver){
        this.driver = driver;
    }

    public void pageScrollDown(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)", "");
    }

    public void byPassCaptcha(){
        WebElement captcha = driver.findElement(By.cssSelector("iframe"));
        driver.switchTo().frame(captcha);
        WebElement captchaCheckBox = driver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
        captchaCheckBox.click();
        driver.switchTo().defaultContent();
        WebElement captchaMenu = driver.findElement(By.cssSelector("body > div:nth-child(4) > div:nth-child(4) > iframe"));
        driver.switchTo().frame(captchaMenu);
        WebElement captchaAudioButton = driver.findElement(By.id("recaptcha-audio-button"));
        captchaAudioButton.click();
        driver.findElement(By.id("reset-button")).click();
        driver.switchTo().defaultContent();
    }

}
