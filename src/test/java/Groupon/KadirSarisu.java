package Groupon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class KadirSarisu {
    public static void main(String[] args) throws InterruptedException {


        // hello geeks
        //HELLO WORLD

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(" http://groupon.com");

        String actualTittle = driver.getTitle();

        try {
            driver.findElement(By.id("goods-tab-link")).click();
        } catch (Exception e) {
            driver.findElement(By.id("nothx")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("goods-tab-link")).click();

        }

        // TEST CASE 1

        // As a user I should be able to sort items by price from high to low;

        WebElement priceOrdering = driver.findElement(By.id("grpn-sorts-select"));

        Select pricehightoLow = new Select(priceOrdering);
        pricehightoLow.selectByValue("price:desc");
        Thread.sleep(3000);

        if (driver.getCurrentUrl().contains("/goods?sort=price%3Adesc")) {
            System.out.println("Sorted by High to Low verification  is PASSED");
        } else {
            System.out.println("Sorted by High to Low verification  is FAILED");


        }

        // As a user I should be able to sort items from low to high

        WebElement priceOrder = driver.findElement(By.id("grpn-sorts-select"));

        Select priceLowTohigh = new Select(priceOrdering);
        priceLowTohigh.selectByValue("price:asc");
        Thread.sleep(3000);

        if (driver.getCurrentUrl().contains("/goods?sort=price%3Aasc")) {
            System.out.println("Sorted by Low to High verification is PASSED");
        } else {
            System.out.println("Sorted by Low to High Verification is FAILED");
        }


        // As a user I should be able to sort items by Customer Ratings

        WebElement rating = driver.findElement(By.id("grpn-sorts-select"));

        Select customerRating = new Select(priceOrdering);
        customerRating.selectByValue("rating\n");
        Thread.sleep(3000);

        if (driver.getCurrentUrl().contains("/goods?sort=rating")) {
            System.out.println("Sorted by Customer Rating Verification is PASSED");
        } else {
            System.out.println("Sorted by Customer Rating Verification is FAILED");

        }

    }
}
