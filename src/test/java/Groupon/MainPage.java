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

    @FindBy(id = "featured-brand-box")
    WebElement featuredBrandBox;

    @FindBy(css = ".refinement")
    List<WebElement> searchSortByBrandAllOptions;

    @FindBy(css = "#pull-results .cui-udc-title")
    List<WebElement> searchResultTitles;

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public MainPage() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        driver.get("https://www.groupon.com/");

        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void pickMenuOption(By by) throws InterruptedException {

       try{
           driver.findElement(by).click();
       }catch (Exception e) {
           driver.findElement(By.id("nothx")).click();
           Thread.sleep(1500);
           driver.findElement(by).click();
       }
    }

    public void searchSortByBrand(String search, String brand) throws InterruptedException {

        mainSearchWindow.sendKeys(search);
        wait.until(ExpectedConditions.elementToBeClickable(mainSearchButton));

        mainSearchButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(featuredBrandBox));

        featuredBrandBox.click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".refinement"))));


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
