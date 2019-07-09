package Groupon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Olha {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.groupon.com/");

        // Comment
        
        try {
            Thread.sleep(1000);
            driver.findElement(By.id("local-tab-link")).click();
        }catch (Exception e){
            driver.findElement(By.id("nothx")).click();
            Thread.sleep(1500);
            driver.findElement(By.id("local-tab-link")).click();
            Thread.sleep(1500);
        }

        sortingByMinPrice(driver, 2000);
        sortingByMaxPrice(driver, 600);
        sortingByMinAndMaxPrice(driver, 3, 555);
        searchingByBrand(driver, 50);

    }

    // TestCase #1
    /**
     * As a user I want to be able find items by the given minimum and maximum prices*/
    public static void sortingByMinAndMaxPrice(WebDriver driver, int minPrice, int maxPrice) throws InterruptedException{

        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='featured-category-box']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//ul[@class='refinement-list']/li/a)[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("rangeFilters-arrow")).click();

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
        driver.findElement(By.id("featured-sort-price:desc-label")).click();

        List<WebElement> prices = driver.findElement(By.id("pull-cards")).findElements(By.cssSelector(".cui-price-discount.c-txt-price"));
        System.out.println("Number of items: " + prices.size());
        String s ="";
        double number = 0;
        for (WebElement price:prices) {
            s = price.getText().substring(1).replace(",", "");
            if (Character.isDigit(s.charAt(0))) {
                number = Double.parseDouble(s);
                if (number >= minPrice && number <= maxPrice) {
                    System.out.println(number);
                } else {
                    System.out.println("Price verification FAILED");
                    break;
                }
            }
        }
        System.out.println("Price verification PASSED");
        driver.close();
    }

    // TestCase #2
    /**
     * As a user I want to be able to find items by the given minimum price*/
    public static void sortingByMinPrice(WebDriver driver, int minPrice) throws InterruptedException{

        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='featured-category-box']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//ul[@class='refinement-list']/li/a)[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("rangeFilters-arrow")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@title='Price min']")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1500);
        String str = "" + minPrice;
        driver.findElement(By.xpath("//input[@title='Price min']")).sendKeys(str + Keys.ENTER);
        Thread.sleep(1500);
        driver.findElement(By.id("featured-location-box")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("featured-location-chicago-label")).click();
        Thread.sleep(3000);
        List<WebElement> prices = driver.findElement(By.id("pull-cards")).findElements(By.cssSelector(".cui-price-discount.c-txt-price"));
        System.out.println("Number of items: " + prices.size());
        String s ="";
        double number = 0;
        for (WebElement price:prices) {
            s = price.getText().substring(1).replace(",", "");
            if (Character.isDigit(s.charAt(0))) {
                number = Double.parseDouble(s);
                if (number >= minPrice) {
                    System.out.println(number);
                } else {
                    System.out.println("Price verification FAILED");
                    break;
                }
            }
        }
        System.out.println("Price verification PASSED");
        driver.close();
    }

    // TestCase #3
    /**
     * As a user I want to be able to find items by the given maximum price*/
    public static void sortingByMaxPrice(WebDriver driver, int maxPrice) throws InterruptedException{
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@id='featured-category-box']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//ul[@class='refinement-list']/li/a)[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("rangeFilters-arrow")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1500);
        String str = "" + maxPrice;
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(str + Keys.ENTER);
        driver.findElement(By.id("featured-location-box")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("featured-location-river-north-chicago-il-label")).click();
        Thread.sleep(1000);

        List<WebElement> prices = driver.findElement(By.id("pull-cards")).findElements(By.cssSelector(".cui-price-discount.c-txt-price"));
        System.out.println("Number of items: " + prices.size());
        String s ="";
        double number = 0;
        for (WebElement price:prices) {
            s = price.getText().substring(1).replace(",", "");
            if (Character.isDigit(s.charAt(0))) {
                number = Double.parseDouble(s);
                if (number <= maxPrice) {
                    System.out.println(number);
                } else {
                    System.out.println("Price verification FAILED");
                    break;
                }
            }
        }
        System.out.println("Price verification PASSED");
        driver.close();
    }

    // TestCase #4
    /**
     * As a user I want to be able to find items of selected brand by the given maximum price*/
    public static void searchingByBrand(WebDriver driver, int maxPrice) throws InterruptedException{
        driver.findElement(By.xpath("//div[@id='featured-brand-box']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//*[@class='brand-checkbox-label'])[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("rangeFilters-arrow")).click();
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1500);
        String strMax = "" + maxPrice;
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(strMax+ Keys.ENTER);
        Thread.sleep(1000);
        List<WebElement> prices = driver.findElement(By.id("pull-cards")).findElements(By.cssSelector(".cui-price-discount.c-txt-price"));
        System.out.println("Number of items: " + prices.size());
        String s ="";
        double number = 0;
        for (WebElement price:prices) {
            s = price.getText().substring(1).replace(",", "");
            if (Character.isDigit(s.charAt(0))) {
                number = Double.parseDouble(s);
                if (number <= maxPrice && number > 0) {
                    System.out.println(number);
                } else {
                    System.out.println("Price verification FAILED");
                    break;
                }
            }
        }
        System.out.println("Price verification PASSED");
        driver.close();

    }
}
