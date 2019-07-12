package Groupon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ThingsToDoPageNurlan {

  static WebDriver driver;
  static  MainPage mp;

  @BeforeMethod
  public void setUp() throws InterruptedException {
      mp = new MainPage();
      this.driver = mp.driver;
      Thread.sleep(2000);
      mp.pickMenuOption(By.id("things-to-do-tab-link"));
  }

    // Geeks, Nurlan. As User I should able choose Things TO Do Module, set up Price arrangement, Location,Popularity,
    // Rating and Low to High.

    @Test(priority = 1)
    public void test1() throws InterruptedException{

       // WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();

        //grouponThingsToDo(driver);
        String category = driver.findElement(By.id("pull-page-header-title")).getText();



       // grouponThingsToDo(driver);
        Thread.sleep(1000);

        grouponThingsToDoSetUpPrice(driver, "100");
        Thread.sleep(1500);

        grouponThingsToDoSetUpLocationToMagnificentMile(driver);
        Thread.sleep(1500);

        grouponThingsToDoPopularityByTopSeller(driver);
        Thread.sleep(1500);

        grouponThingsToDoSetUpByRatingTop(driver);
        Thread.sleep(1800);

        grouponThingsToDoSortByPriceLowToHigh(driver);
        Assert.assertEquals(category,"Things To Do", "Verification of category is Failed");


    }




    // Geeks, Nurlan. As User I should able to go Things To DO Module and sort my search by:
    // by different price arrangement, dif location.

    @Test
    public void test2() throws InterruptedException{

        //driver = new ChromeDriver();

       // grouponThingsToDo(driver);

        //grouponThingsToDo(driver);
        Thread.sleep(2000);

        grouponThingsToDoSetUpPrice(driver, "45");
        Thread.sleep(1500);

        grouponThingsToDoSetUpLocationToChicago(driver);
        Thread.sleep(1500);

        grouponThingsToDoPopularityByTopSeller(driver);
        Thread.sleep(1500);

        grouponThingsToDoSetUpByRatingTop(driver);
        Thread.sleep(1243);

        Assert.assertTrue(grouponThingsToDoSortByPriceLowToHigh(driver));
    }





    // Geeks. Nurlan. As user I should be able to go Things to do and search by Sports & Outdoors.
    // Set up the price and location as well.

    @Test
    public void test3() throws InterruptedException{

        //driver = new ChromeDriver();

        //grouponThingsToDo(driver);
        Thread.sleep(2000);

        Assert.assertTrue(grouponThingsToDoSportsAndOutdoors(driver));
        Thread.sleep(2500);

        grouponThingsToDoSetUpPrice(driver, "50");
        Thread.sleep(1000);

        grouponThingsToDoSetUpLocationToOldTown(driver);
        //System.out.println();
    }


//    public void grouponThingsToDo(WebDriver driver)throws InterruptedException{
//        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
//        driver.get("https://www.groupon.com");
//        driver.manage().window().fullscreen();
//        Thread.sleep(2000);
//        try {
//            driver.findElement(By.id("things-to-do-tab-link")).click();
//        }catch (Exception e){
//            driver.findElement(By.id("nothx")).click();
//            Thread.sleep(1500);
//            driver.findElement(By.id("things-to-do-tab-link")).click();
//        }
//
//    }

    @AfterMethod
    public void closeChrome(){

        driver.quit();

    }



    public static boolean grouponThingsToDoSportsAndOutdoors(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id("featured-category-box")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//label[@class='name truncated'])[6]")).click();
        Thread.sleep(2500);
        String title = driver.findElement(By.cssSelector(".featured-title.c-txt-white")).getText();

        return title.equalsIgnoreCase( "Sports & Outdoors");

    }

    public static boolean grouponThingsToDoSortByPriceLowToHigh(WebDriver driver) throws InterruptedException {

       //driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        Thread.sleep(1000);
        driver.findElement(By.id("sort-arrow")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//div[@class='refinement'])[2]")).click();
        Thread.sleep(2000);
        String sortBy = driver.findElement(By.id("featured-sort-box")).getText();
        return sortBy.equalsIgnoreCase( "↑ Price");


    }

    public static void grouponThingsToDoSetUpByRatingTop(WebDriver driver) throws InterruptedException {

        //driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        Thread.sleep(1000);
        driver.findElement(By.id("rating-arrow")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("featured-rating-[4..5]-label")).click();
        Thread.sleep(1000);
        String rating = driver.findElement(By.id("featured-rating-box")).getText();
        Assert.assertEquals(rating,"4 Star & Up",  "Verification of Rating is FAILED");

    }

    public static void grouponThingsToDoPopularityByTopSeller(WebDriver driver) throws InterruptedException {

        //driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        Thread.sleep(1000);
        driver.findElement(By.id("badge-arrow")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("featured-badge-top-seller-label")).click();
        Thread.sleep(1000);
        String byPopularity = driver.findElement(By.id("featured-badge-box")).getText();
        Assert.assertEquals(byPopularity,"Top Seller", "Verification of set up by Rating is FAILED");


    }

    public static void grouponThingsToDoSetUpLocationToOldTown(WebDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("location-arrow")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("featured-location-old-town-chicago-il-label")).click();
        Thread.sleep(1000);
        String oldTown = driver.findElement(By.id("featured-location-box")).getText();

        Assert.assertEquals(oldTown,"Old Town", "Verification of Location to Old Town is FAILED");

    }

    public static void grouponThingsToDoSetUpLocationToMagnificentMile(WebDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("location-arrow")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("featured-location-magnificent-mile-chicago-il-label")).click();
        Thread.sleep(1000);
        String location = driver.findElement(By.id("featured-location-box")).getText();
        Assert.assertEquals(location, "Magnificent Mile", "Verification of location is FAILED");


    }

    public static void grouponThingsToDoSetUpLocationToChicago(WebDriver driver) throws InterruptedException {

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(1000);
        driver.findElement(By.id("location-arrow")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("featured-location-chicago-label")).click();
    }

    public static void grouponThingsToDoSetUpPrice(WebDriver driver, String price) throws InterruptedException {

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2500);
        driver.findElement(By.id("rangeFilters-arrow")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(price + Keys.ENTER);

        String priceRange = driver.findElement(By.xpath("(//div//span)[30]")).getText();
        Assert.assertEquals(priceRange, "Price", "Verification of Price Range is FAILED");

    }

}