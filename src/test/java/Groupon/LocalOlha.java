package Groupon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LocalOlha {
  
    static WebDriver driver;
    MainPage mp;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        mp = new MainPage();
       this.driver = mp.driver;
       Thread.sleep(2000);
       mp.pickMenuOption(By.id("local-tab-link"));
    }

    @Test
    public void localSortMinMax() throws InterruptedException {
        Assert.assertTrue(sortingByMinAndMaxPrice(driver, 3, 555));
    }

    @Test
    public void localCheckSelectedLocation() throws InterruptedException {
        Assert.assertTrue(checkSelectedLocation(driver));
    }


    @Test
    public void localCheckSelectedCategory() throws InterruptedException{
        Assert.assertTrue(checkSelectedCategory(driver));
    }

    @Test
    public void localCheckSelectedBrand() throws InterruptedException{
        Assert.assertTrue(checkSelectedBrand(driver));
    }

    @AfterMethod
    public void tearDown() throws InterruptedException{
        Thread.sleep(3000);
        driver.close();
    }

    // TestCase #1
    /**
     * As a user I want to be able find items by the given minimum and maximum prices*/

    public static boolean sortingByMinAndMaxPrice(WebDriver driver, int minPrice, int maxPrice) throws InterruptedException{
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='featured-category-box']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//ul[@class='refinement-list']/li/a)[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("rangeFilters-arrow")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@title='Price min']")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1500);
        String strMin = "" + minPrice;
        driver.findElement(By.xpath("//input[@title='Price min']")).sendKeys(strMin + Keys.ENTER);
        Thread.sleep(1500);
        driver.findElement(By.id("rangeFilters-arrow")).click();
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1500);
        String strMax = "" + maxPrice;
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(strMax+ Keys.ENTER);
        driver.findElement(By.id("featured-location-box")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("featured-location-chicago-label")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("featured-rating-box")).click();
        driver.findElement(By.id("featured-rating-[4..5]-label")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='featured-sort-box']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("featured-sort-price:desc-label")).click();

        List<WebElement> prices = driver.findElement(By.id("pull-cards")).findElements(By.cssSelector(".cui-price-discount.c-txt-price"));
        String s ="";
        double itemPrice = 0;
        for (WebElement price:prices) {
            s = price.getText().substring(1).replace(",", "");
            itemPrice = Double.parseDouble(s);
            if (itemPrice <= minPrice &&  itemPrice >= maxPrice) {
                return false;
            }
        }
        System.out.println("Price verification PASSED");
        return true;
    }

    // Test Case #2
    /**
     * As a user I should be able to verify if all the results are in the given location*/
    public static boolean checkSelectedLocation(WebDriver driver) throws InterruptedException{
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='featured-category-box']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//ul[@class='refinement-list']/li/a)[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("featured-location-box")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("featured-location-chicago-label")).click();
        Thread.sleep(1000);
        String selectedLocation = driver.findElement(By.xpath("//div//span[@class='featured-title c-txt-white']")).getText();
        System.out.println(selectedLocation);
        List<WebElement> locations = driver.findElement(By.id("pull-cards")).findElements(By.cssSelector(".cui-location-name"));
     //   Thread.sleep(1000);
        for (WebElement location : locations){
            if (!location.getText().contains(selectedLocation)){
                System.out.println("Location verification FAILED");
                return false;
            }
        }
        System.out.println("Location verification PASSED");
        return true;
    }

    // Test Case #3
    /**
     * As a user I should be able to verify the selected category and brand*/
    public static boolean checkSelectedCategory(WebDriver driver) throws InterruptedException{
        Thread.sleep(2500);
        driver.findElement(By.xpath("//div[@id='featured-category-box']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//ul[@class='refinement-list']/li/a)[2]")).click();
        String selectedCategory = driver.findElement(By.xpath("(//div//span[@class='featured-title c-txt-black'])[1]")).getText();
        return selectedCategory.equalsIgnoreCase("Retail");

    }

    // Test Case #4
    /**
     * As a user I should be able to verify the selected brand*/
    public static boolean checkSelectedBrand(WebDriver driver) throws InterruptedException{
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@id='featured-brand-box']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//*[@class='brand-checkbox-label'])[2]")).click();
        String chosenBrand = driver.findElement(By.xpath("//div//span[@class='featured-title c-txt-white']")).getText();
        return chosenBrand.equals("Contec Medical Systems");
    }
}
