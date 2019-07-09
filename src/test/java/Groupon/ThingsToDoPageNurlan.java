package Groupon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ThingsToDoPageNurlan {

    WebDriver driver;


    // Geeks, Nurlan. As User I should able choose Things TO Do Module, set up Price arrangement, Location,Popularity,
    // Rating and Low to High.

    @Test(priority = 1)
    public void test1() throws InterruptedException{

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        grouponThingsToDo(driver);
        String category = driver.findElement(By.id("pull-page-header-title")).getText();
        Assert.assertEquals(category,"Things To Do", "Verification of category is Failed");


        grouponThingsToDo(driver);
        Thread.sleep(1000);

        grouponThingsToDoSetUpPrice(driver, "100");
        Thread.sleep(800);

        grouponThingsToDoSetUpLocationToMagnificentMile(driver);
        Thread.sleep(800);

        grouponThingsToDoPopularityByTopSeller(driver);
        Thread.sleep(800);

        grouponThingsToDoSetUpByRatingTop(driver);
        Thread.sleep(1800);

        grouponThingsToDoSortByPriceLowToHigh(driver);


    }




    // Geeks, Nurlan. As User I should able to go Things To DO Module and sort my search by:
    // by different price arrangement, dif location.

    @Test(priority = 2)
    public void test2() throws InterruptedException{

        driver = new ChromeDriver();

        grouponThingsToDo(driver);

        grouponThingsToDo(driver);
        Thread.sleep(2000);

        grouponThingsToDoSetUpPrice(driver, "45");
        Thread.sleep(1400);

        grouponThingsToDoSetUpLocationToChicago(driver);
        Thread.sleep(1200);

        grouponThingsToDoPopularityByTopSeller(driver);
        Thread.sleep(1000);

        grouponThingsToDoSetUpByRatingTop(driver);
        Thread.sleep(1243);

        grouponThingsToDoSortByPriceLowToHigh(driver);
    }





    // Geeks. Nurlan. As user I should be able to go Things to do and search by Sports & Outdoors.
    // Set up the price and location as well.

    @Test(priority = 3)
    public void test3() throws InterruptedException{

        driver = new ChromeDriver();

        grouponThingsToDo(driver);
        Thread.sleep(2000);

        grouponThingsToDoSportsAndOutdoors(driver);
        Thread.sleep(500);

        grouponThingsToDoSetUpPrice(driver, "50");
        Thread.sleep(200);

        grouponThingsToDoSetUpLocationToOldTown(driver);
        System.out.println();
    }


    public void grouponThingsToDo(WebDriver driver)throws InterruptedException{
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        driver.get("https://www.groupon.com");
        driver.manage().window().fullscreen();
        Thread.sleep(2000);
        try {
            driver.findElement(By.id("things-to-do-tab-link")).click();
        }catch (Exception e){
            driver.findElement(By.id("nothx")).click();
            Thread.sleep(1500);
            driver.findElement(By.id("things-to-do-tab-link")).click();
        }

    }

    @AfterMethod
    public void closeChrome(){

        driver.quit();

    }



    public static void grouponThingsToDoSportsAndOutdoors(WebDriver driver){
        driver.findElement(By.id("featured-category-box")).click();
        driver.findElement(By.xpath("(//label[@class='name truncated'])[5]")).click();

        String title = driver.findElement(By.xpath("//div[@data-bhc-path='BrowseHeader|subcategory:sports-and-outdoor-activities']")).getText();

        Assert.assertEquals(title, "Sports & Outdoors", "Verification of TITLE Sport & Outdoors is FAILED");

    }

    public static void grouponThingsToDoSortByPriceLowToHigh(WebDriver driver){

        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        driver.findElement(By.id("sort-arrow")).click();
        driver.findElement(By.xpath("(//div[@class='refinement'])[2]")).click();

        String sortBy = driver.findElement(By.id("featured-sort-box")).getText();
        Assert.assertEquals(sortBy, "↑ Price", "Verification of Sort by Low To High is FAILED");


    }

    public static void grouponThingsToDoSetUpByRatingTop(WebDriver driver){

        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        driver.findElement(By.id("rating-arrow")).click();
        driver.findElement(By.id("featured-rating-[4..5]-label")).click();

        String rating = driver.findElement(By.id("featured-rating-box")).getText();
        Assert.assertEquals(rating,"4 Star & Up",  "Verification of Rating is FAILED");

    }

    public static void grouponThingsToDoPopularityByTopSeller(WebDriver driver){

        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        driver.findElement(By.id("badge-arrow")).click();
        driver.findElement(By.id("featured-badge-top-seller-label")).click();

        String byPopularity = driver.findElement(By.id("featured-badge-box")).getText();
        Assert.assertEquals(byPopularity,"Top Seller", "Verification of set up by Rating is FAILED");


    }

    public static void grouponThingsToDoSetUpLocationToOldTown(WebDriver driver){
        driver.findElement(By.id("location-arrow")).click();
        driver.findElement(By.id("featured-location-old-town-chicago-il-label")).click();

        String oldTown = driver.findElement(By.id("featured-location-box")).getText();

        Assert.assertEquals(oldTown,"Old Town", "Verification of Location to Old Town is FAILED");

    }

    public static void grouponThingsToDoSetUpLocationToMagnificentMile(WebDriver driver){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("location-arrow")).click();
        driver.findElement(By.id("featured-location-magnificent-mile-chicago-il-label")).click();

        String location = driver.findElement(By.id("featured-location-box")).getText();
        Assert.assertEquals(location, "Magnificent Mile", "Verification of location is FAILED");


    }

    public static void grouponThingsToDoSetUpLocationToChicago(WebDriver driver){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("location-arrow")).click();
        driver.findElement(By.id("featured-location-chicago-label")).click();
    }

    public static void grouponThingsToDoSetUpPrice(WebDriver driver, String price){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("rangeFilters-arrow")).click();
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(price + Keys.ENTER);

        String priceRange = driver.findElement(By.xpath("(//div//span)[30]")).getText();
        Assert.assertEquals(priceRange, "Price", "Verification of Price Range is FAILED");

    }

}