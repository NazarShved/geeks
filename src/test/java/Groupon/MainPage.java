package Groupon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class MainPage {
    //Vusal
    //New Change

    @FindBy(id = "ls-search")
    WebElement mainSearchWindow;

    @FindBy(id ="ls-header-search-button")
    WebElement mainSearchButton;

    @FindBy(id = "brand-arrow")
    WebElement featuredBrandBox;

    @FindBy(css = ".refinement")
    List<WebElement> searchSortByBrandAllOptions;

    @FindBy(css = "#pull-results .cui-udc-title")
    List<WebElement> searchResultTitles;

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public MainPage(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        driver.get("https://www.groupon.com/");

        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void pickMenuOption(By by){

       try{
           wait.until(ExpectedConditions.visibilityOfElementLocated(by));
           driver.findElement(by).click();
       }catch (Exception e) {
           wait.until(ExpectedConditions.visibilityOfElementLocated(by));
           driver.findElement(By.id("nothx")).click();
           if (driver.findElements(By.id("nothx1")).size() == 0) driver.findElement(by).click();
       }
    }

    public void searchSortByBrand(String search, String brand) throws InterruptedException {

        mainSearchWindow.sendKeys(search);

        wait.until(ExpectedConditions.elementToBeClickable(mainSearchButton));
        mainSearchButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("iam-msg")));
        featuredBrandBox.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".refinement")));


        for (int i = 0; i < searchSortByBrandAllOptions.size(); i++) {
            if (searchSortByBrandAllOptions.get(i).getText().toLowerCase().contains(brand.toLowerCase())) {
                searchSortByBrandAllOptions.get(i).click();
                break;
            }
        }
    }

    public int searchCheckResults(String check){

        int countMis = 0;
        for(WebElement el : searchResultTitles){
            if(!el.getText().toLowerCase().contains(check.toLowerCase())) countMis++;
        }
        return countMis;
    }

}
