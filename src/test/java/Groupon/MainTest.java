package Groupon;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static Groupon.GrouponAidana.*;
import static Groupon.ThingsToDoPageNurlan.*;


public class MainTest {
    static WebDriver driver;
    static JavascriptExecutor js;
    static Properties prop;
    static TravelPage travel;
    static MainPage mp;

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        //Vussal
        prop = new Properties();
        FileInputStream fis = new FileInputStream("Args.properties");
        prop.load(fis);

        mp = new MainPage();

        driver = mp.driver;
        js = (JavascriptExecutor) driver;
        Thread.sleep(2000);
    }

    @Test
    public void calendarFuncDepAndRet() throws Exception {
        //Nazar
        mp.pickMenuOption(By.id("getaways-tab-link"));
        travel = new TravelPage(driver);
        travel.pickDate(prop.getProperty("depDateMonth"), prop.getProperty("depDateDay"), prop.getProperty("retDateMonth"), prop.getProperty("retDateDay"));

        Thread.sleep(2000);

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
        //Nazar
        
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
        //Nazar

        mp.pickMenuOption(By.id("getaways-tab-link"));
        travel = new TravelPage(driver);
        travel.pickCity("san antonio");

        String script = "return document.getElementsByClassName(\"ui-autocomplete-input\")[0].value;";
        String value = (String) js.executeScript(script);

        Assert.assertTrue(value.contains("San Antonio"));
    }

    @Test
    public void searchFunc() throws Exception {
        //Nazar

        mp.pickMenuOption(By.id("getaways-tab-link"));
        travel = new TravelPage(driver);
        travel.pickCity("san antonio");
        travel.pickDate(prop.getProperty("depDateMonth"), prop.getProperty("depDateDay"), prop.getProperty("retDateMonth"), prop.getProperty("retDateDay"));
        Thread.sleep(1000);
        travel.clickOnSearchIcon();
        Assert.assertTrue(travel.checkCity() < 5);
    }

    @Test
    public void searchSortByBrand() throws Exception {
        //Vusal

        mp.pickMenuOption(By.id("ls-search"));
        String brand = prop.getProperty("brandName");
        mp.searchSortByBrand("toys", brand);

        Thread.sleep(2000);
        Assert.assertTrue(mp.searchCheckResults(brand) <= 5);
    }




    @Test
    public void dealsOfTheDayVerPriceRange() throws InterruptedException {
        //Aidana Class
        Assert.assertTrue(GrouponAidana.verifyPriceRangeInDealsOfTheDay(driver));
    }

    @Test
    public void featuredLocationVer() throws InterruptedException {
        //Aidana Class
        Assert.assertTrue(GrouponAidana.locationVerificationInFeatured(driver));
    }

    @Test
    public void featuredPriceRangeVer() throws InterruptedException {
        //Aidana Class
        Assert.assertTrue(GrouponAidana.priceRangeVerificationInFeatured(driver,1,1000));
    }

    @Test
    public void kadirTests() throws InterruptedException {
        //Kadir Sarisu refer to class migth not work properly from here
        KadirSarisu.main(new String[0]);
    }



//    @Test
//    public void aigerimTests() throws InterruptedException {
//        //Aigerim Attanbekova refer to class migth not work properly from here
//        Aigerim.main(new String[0]);
//    }



    @AfterMethod
    public void shutDown() {
        driver.quit();
    }

}