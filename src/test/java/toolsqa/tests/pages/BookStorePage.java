package toolsqa.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookStorePage {

    public WebDriver driver;
    public BookStorePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".main-header")
    public WebElement bookStorePageHeader;

    @FindBy(id = "searchBox")
    public WebElement searchBar;

    @FindBy(id = "see-book-You Don't Know JS")
    public WebElement searchedBook;

    @FindBy(xpath = "//a[.=\"You Don't Know JS\"]")
    public WebElement searchedBookDetailsArea;

    @FindBy(id ="addNewRecordButton")
    public WebElement backToBookStorePageButton;

    @FindBy(id = "submit")
    public WebElement logOutButton;

    public boolean bookStorePageVisibility(WebElement bookStorePageHeaderElement){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(bookStorePageHeaderElement));
            return bookStorePageHeaderElement.isDisplayed();
        }
        catch (Exception exception)
        {
            System.out.println("Book Store Page Header element is not visible exception is caught..!");
            return false;
        }
    }

    public String getBookStorePageHeader()
    {
        return bookStorePageHeader.getText();
    }

    public void searchBook(String bookName){
        searchBar.sendKeys(bookName);
    }

    public boolean searchedBookVisibility(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(searchedBook));
        return searchedBook.isDisplayed();
    }

    public void goToSearchedBookDetails(){
        searchedBookDetailsArea.click();
    }

    public boolean backToBookStorePageButtonVisibility(WebElement element){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        }
        catch (Exception exception)
        {
            System.out.println("BackToBookStorePageButton element is not visible exception is caught..!");
            return false;
        }
    }

    public LoginPage logOut()
    {
        logOutButton.click();
        return new LoginPage(driver);
    }

}
