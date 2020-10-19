package toolsqa.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    public WebDriver driver;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "firstname")
    public WebElement firstNameField;

    @FindBy(id = "lastname")
    public WebElement lastNameField;

    @FindBy(id = "userName")
    public WebElement userNameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "register")
    public WebElement registerButton;

    @FindBy(id = "gotologin")
    public WebElement backToLoginButton;


    public void setFirstName(String firstName)
    {
        firstNameField.sendKeys(firstName);
    }
    public void setLastName(String lastName)
    {
        lastNameField.sendKeys(lastName);
    }
    public void setUserName(String userName)
    {
        userNameField.sendKeys(userName);
    }
    public void setPassword(String password)
    {
        passwordField.sendKeys(password);
    }

    public LoginPage clickBackToLoginPageButton()
    {
        backToLoginButton.click();
        return new LoginPage(driver);
    }
}
