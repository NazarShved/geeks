package Groupon;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class TravelNazar {
    static WebDriver driver;
    static JavascriptExecutor js;
    static Properties prop;
    static TravelPage travel;
    static MainPage mp;
    static WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("Args.properties");
        prop.load(fis);
        mp = new MainPage();
        driver = mp.driver;
        js = (JavascriptExecutor) driver;
        this.wait = mp.wait;
    }

    @Test
    public void calendarFuncDepAndRet() throws Exception {
        //As a user I want to be able to pick my desired departure and return dates.

        mp.pickMenuOption(By.id("getaways-tab-link"));
        travel = new TravelPage(driver);
        travel.pickDate(prop.getProperty("depDateMonth"), prop.getProperty("depDateDay"), prop.getProperty("retDateMonth"), prop.getProperty("retDateDay"));

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".total-nights")));

        String script = "return document.getElementsByClassName(\"icon-selection-box instrument\")[1].textContent;";
        String value = (String) js.executeScript(script);
        value = value.toLowerCase();

        String checkMonth1 = prop.getProperty("depDateMonth").toLowerCase().substring(0, 3);
        String checkMonth2 = prop.getProperty("retDateMonth").toLowerCase().substring(0, 3);

        Assert.assertTrue(value.contains(checkMonth1) && value.contains(prop.getProperty("depDateDay")) &&
                value.contains(checkMonth2) && value.contains(prop.getProperty("retDateDay")));
    }

    @Test
    public void calendarFuncOnlyDep() throws Exception {
        //As a user I want to be able to pick my desired departure date.
        
        mp.pickMenuOption(By.id("getaways-tab-link"));
        travel = new TravelPage(driver);
        travel.select(By.xpath("(//div[@class = 'icon-selection-box instrument'])[2]/span"));
        travel.pickDate(prop.getProperty("depDateMonth"), prop.getProperty("depDateDay"));

        String script = "return document.getElementsByClassName(\"icon-selection-box instrument\")[1].textContent;";
        String value = (String) js.executeScript(script);

        value = value.toLowerCase();
        String checkValue = prop.getProperty("depDateMonth").toLowerCase().substring(0, 3);
        Thread.sleep(1000);

        Assert.assertTrue(value.contains(checkValue) && value.contains(prop.getProperty("depDateDay").toLowerCase()));
    }

    @Test
    public void enterCityWithAutoSug() throws Exception {
        //As a user I want to recive suggestions while I'm entering my City in the search box.

        mp.pickMenuOption(By.id("getaways-tab-link"));
        travel = new TravelPage(driver);
        travel.pickCity("san antonio");

        String script = "return document.getElementsByClassName(\"ui-autocomplete-input\")[0].value;";
        String value = (String) js.executeScript(script);

        Assert.assertTrue(value.contains("San Antonio"));
    }

    @Test
    public void TravelSearchFunc() throws Exception {
        //As a user Iwant to be able to search for hotels ,by my desired city, that are avilable at my desired date.

        mp.pickMenuOption(By.id("getaways-tab-link"));
        travel = new TravelPage(driver);
        travel.pickCity("san antonio");
        travel.pickDate(prop.getProperty("depDateMonth"), prop.getProperty("depDateDay"), prop.getProperty("retDateMonth"), prop.getProperty("retDateDay"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".total-nights")));
        travel.clickOnSearchIcon();
        Assert.assertTrue(travel.checkCity() < 5);
    }

    @Test
    public void searchSortByBrand() throws Exception {
   //As a user I want to be able to sort my search results by the brand name

        mp.pickMenuOption(By.id("ls-search"));
        String brand = prop.getProperty("brandName");
        mp.searchSortByBrand("toys", brand);
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("(//span[@class = 'featured-title c-txt-white'])[2]")),brand));
        Assert.assertTrue(mp.searchCheckResults(brand) <= 5);
    }

    @Test
    public void sortByPriceSlider() throws InterruptedException {
        //As a user I want to be able to see the price changes when I drag the price slider.

        mp.pickMenuOption(By.id("things-to-do-tab-link"));
        Assert.assertTrue(mp.priceRangeSliderCheck());
    }

    @AfterMethod
    public void shutDown() {
        driver.quit();
    }

}