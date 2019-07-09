package Groupon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        Thread.sleep(1000);
        PageFactory.initElements(driver, this);
    }

    public void pickMenuOption(By by) throws InterruptedException {
        try {
            Thread.sleep(1000);
            driver.findElement(by).click();
        }catch (Exception e){
            driver.findElement(By.id("nothx")).click();
            Thread.sleep(1500);
            driver.findElement(by).click();
        }
        Thread.sleep(2000);
    }

    public void searchSortByBrand(String search, String brand) throws InterruptedException {

        mainSearchWindow.sendKeys(search);
        mainSearchButton.click();
        Thread.sleep(5000);
        featuredBrandBox.click();
        Thread.sleep(3000);

        for (int i = 0; i < searchSortByBrandAllOptions.size(); i++) {
            if (searchSortByBrandAllOptions.get(i).getText().toLowerCase().contains(brand)) {
                Thread.sleep(500);
                searchSortByBrandAllOptions.get(i).click();
                break;
            }
        }
    }

    public int searchCheckResults(String check){

        int countMis = 0;
        for(WebElement el : searchResultTitles){
            System.out.println(el.getText());
            if(!el.getText().toLowerCase().contains(check)) countMis++;
        }
        return countMis;
    }

}
