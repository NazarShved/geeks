package Groupon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        wait = new WebDriverWait(driver, 12);
    }

    public void pickMenuOption(By by) throws InterruptedException {

       try{
           wait.until(ExpectedConditions.elementToBeClickable(by));
           driver.findElement(by).click();
       }catch (Exception e) {
           System.out.println(e.getMessage());
          if(driver.findElements(By.id("nothx")).size() > 0) driver.findElement(By.id("nothx")).click();

          Thread.sleep(2000);
          driver.findElement(by).click();


       }
    }

    public void searchSortByBrand(String search, String brand){

        wait.until(ExpectedConditions.elementToBeClickable(By.id("iam-msg")));
        mainSearchWindow.sendKeys(search);

        mainSearchButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("iam-msg")));
        WebElement fBrandBox = wait.until(ExpectedConditions.elementToBeClickable(featuredBrandBox));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("cui-shell-info")));

            fBrandBox.click();
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

    public void mainSearchChangeCity(String city){
        driver.findElement(By.id("ls-location")).clear();
        driver.findElement(By.xpath("//*[@id=\"ls-location\"]")).sendKeys(city + Keys.ENTER);
    }

    public boolean priceRangeSliderCheck(){

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("cui-shell-info")));

            wait.until(ExpectedConditions.elementToBeClickable(By.id("rangeFilters-arrow"))).click();

            //Thread.sleep(1500);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//*[@class = 'input-range__slider'])[1]")));
            Actions act = new Actions(driver);
            WebElement slider1 = driver.findElement(By.xpath("(//*[@class = 'input-range__slider'])[1]"));

            js = (JavascriptExecutor)driver;
            String script = "return document.getElementsByTagName(\"input\")[5].value;";
            String value = "";
            for(int i = 1; i < 40; i++) {
                act.moveToElement(slider1).clickAndHold().moveByOffset(5, 0).build().perform();
                String tempValue = (String)js.executeScript(script);
                if(tempValue.equals(value)) return false;
            }

            return true;
    }

}
