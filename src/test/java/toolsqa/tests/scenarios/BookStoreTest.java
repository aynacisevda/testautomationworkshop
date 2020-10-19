package toolsqa.tests.scenarios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import toolsqa.tests.base.TestBase;
import toolsqa.tests.pages.BookStorePage;
import toolsqa.tests.pages.LoginPage;
import toolsqa.tests.pages.RegisterPage;
import toolsqa.tests.utilities.Utility;

public class BookStoreTest extends TestBase{

    public WebDriver driver;
    public RegisterPage registerPage;
    public LoginPage loginPage;
    public BookStorePage bookStorePage;
    public Utility utility;

    public void initPageObjects(WebDriver driver){
        registerPage = PageFactory.initElements(driver, RegisterPage.class);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        bookStorePage = PageFactory.initElements(driver,BookStorePage.class);
    }

    @BeforeSuite
    public void setUp(){
        driver = setBrowser("chrome");
        initPageObjects(driver);
        utility = new Utility(driver);
        goToPage("https://demoqa.com/register");
    }
    @Test (priority = 0)
    public void verifyRegistration(){
        registerPage.setFirstName("firstName");
        registerPage.setLastName("lastName");
        registerPage.setUserName("userName");
        registerPage.setPassword("password");
        utility.pageScrollDown();
        utility.byPassCaptcha();
        registerPage.clickBackToLoginPageButton();
        Assert.assertTrue(loginPage.loginPageHeaderVisibility(loginPage.loginPageHeader));
        System.out.println("You are on login page" + loginPage.loginPageHeader.getText());
    }

    @Test (priority = 1)
    public void verifyUserIsLoggedIn(){
        loginPage.setUserName("userName");
        loginPage.setPassword("password");
        loginPage.logIn();
        waitForElement(loginPage.loggedInUserNameField,3000);
        Assert.assertEquals(loginPage.getLoggedInUserName(),"username");
        System.out.println(loginPage.getLoggedInUserName() + " is logged in successfully");
    }

    @Test (priority = 2)
    public void verifyBookStorePageIsOpened() throws InterruptedException {
        utility.pageScrollDown();
        Thread.sleep(3000);
        loginPage.clickGoToBookStorePageButton();
        Assert.assertTrue(bookStorePage.bookStorePageVisibility(bookStorePage.bookStorePageHeader));
        System.out.println(bookStorePage.getBookStorePageHeader() + " page is opened in successfully");
    }

    @Test (priority = 3)
    public void verifySearchedBookIsDisplayed(){
        bookStorePage.searchBook("You");
        Assert.assertTrue(bookStorePage.searchedBookVisibility());
        System.out.println("Searched book is displayed successfully");
    }

    @Test (priority = 4)
    public void verifyDetailOfSearchedBookIsDisplayed()
    {
       bookStorePage.goToSearchedBookDetails();
       Assert.assertTrue(bookStorePage.backToBookStorePageButtonVisibility(bookStorePage.backToBookStorePageButton));
       System.out.println("Searched book's detail is displayed successfully");
    }

    @Test (priority = 5)
    public void verifyUserIsLoggedOut()
    {
        bookStorePage.logOut();
        Assert.assertTrue(loginPage.loginPageHeaderVisibility(loginPage.loginPageHeader));
        System.out.println("User is logged out successfully");
    }

   @AfterSuite
    public void tearDown()
    {
        quitDriver();
    }
}
