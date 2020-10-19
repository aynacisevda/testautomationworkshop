package toolsqa.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "userName")
    public WebElement userNameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "login")
    public WebElement loginButton;

    @FindBy(id = "userName-value")
    public WebElement loggedInUserNameField;

    @FindBy(id = "gotoStore")
    public WebElement goToBookStoreButton;

    @FindBy(xpath = "//div[.='Welcome,Login in Book Store']")
    public WebElement loginPageHeader;

    public void setUserName(String userName)
    {
        userNameField.sendKeys(userName);
    }
    public void setPassword(String password)
    {
        passwordField.sendKeys(password);
    }

    public void logIn(){
        loginButton.click();
    }
    public String getLoggedInUserName()
    {
        String loggedInUserName = loggedInUserNameField.getText();
        return loggedInUserName;

    }
    public BookStorePage clickGoToBookStorePageButton()
    {
        goToBookStoreButton.click();
        return new BookStorePage(driver);
    }

    public boolean loginPageHeaderVisibility(WebElement loginPageHeaderElement){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(loginPageHeaderElement));
            return loginPageHeaderElement.isDisplayed();
        }
        catch (Exception exception)
        {
            System.out.println("Login Page Header is not visible exception is caught..!");
            return false;
        }
    }
}
