package Groupon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class KadirSarisu {

    static WebDriver driver;
    static MainPage mp;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        mp = new MainPage();
        this.driver = mp.driver;
        Thread.sleep(2500);
        mp.pickMenuOption(By.id("goods-tab-link"));

    }


    @Test
    public void sortingByPriceFromHighToLow() throws InterruptedException {
        Assert.assertTrue(sortingByPriceFromHighToLow(driver));
    }

    @Test
    public void sortingByPriceLowToHigh() throws InterruptedException {
        Assert.assertTrue(sortingByPriceLowToHigh(driver));
    }

    @Test
    public void sortingByCustomerRating() throws InterruptedException {
        Assert.assertTrue(sortingByCustomerRating(driver));
    }

    @AfterMethod
    public void tearDown() throws InterruptedException{
        Thread.sleep(3000);
        driver.close();
    }

        // TEST CASE 1

        // As a user I should be able to sort items by price from high to low;


    public static boolean sortingByPriceFromHighToLow(WebDriver driver) throws InterruptedException {

        Thread.sleep(2500);
        WebElement priceOrdering = driver.findElement(By.id("grpn-sorts-select"));

        Select pricehightoLow = new Select(priceOrdering);
        pricehightoLow.selectByIndex(2);
        Thread.sleep(3000);

        if (driver.getCurrentUrl().contains("/goods?sort=price%3Adesc")) {
            System.out.println("Sorted by High to Low verification  is PASSED");
            return true;
        }
            System.out.println("Sorted by High to Low verification  is FAILED");
            return false;

    }


    public static boolean sortingByPriceLowToHigh(WebDriver driver) throws InterruptedException {

        // As a user I should be able to sort items from low to high

        Thread.sleep(2500);

        WebElement priceOrdering2 = driver.findElement(By.id("grpn-sorts-select"));

        Select priceLowTohigh = new Select(priceOrdering2);
        priceLowTohigh.selectByIndex(1);
        Thread.sleep(3000);

        if (driver.getCurrentUrl().contains("/goods?sort=price%3Aasc")) {
            System.out.println("Sorted by Low to High verification is PASSED");
            return true;
        }
            System.out.println("Sorted by Low to High Verification is FAILED");

             return false;
        }


    public static boolean sortingByCustomerRating(WebDriver driver) throws InterruptedException {

        // As a user I should be able to sort items by Customer Ratings

        Thread.sleep(2500);
        WebElement priceOrdering3 = driver.findElement(By.id("grpn-sorts-select"));

        Select customerRating = new Select(priceOrdering3);
        customerRating.selectByIndex(3);
        Thread.sleep(3000);

        if (driver.getCurrentUrl().contains("/goods?sort=rating")) {
            System.out.println("Sorted by Customer Rating Verification is PASSED");
            return true;
        }
            System.out.println("Sorted by Customer Rating Verification is FAILED");

        return false;

        }



    }

