package Groupon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Aigerim {

      static WebDriver driver;
      static MainPage mp;


        @BeforeMethod
        public void setUp() throws InterruptedException {
            mp=new MainPage();
            this.driver =mp.driver;

            mp.pickMenuOption(By.id("beauty-and-spas-tab-link"));
            Thread.sleep(2000);
        }


        @Test
        public void GrouponBeautySpa() throws InterruptedException {

            // as a user i should be able to see all the spas on the webpage starting from $1-$50  from "SPAS" link Aigerim;

            // setting location for "NYC";

            mp.mainSearchChangeCity("New York City");
            // choosing Beauty and Spas link;

            Thread.sleep(3000);

            WebElement beautyAndSpaOptionLink = driver.findElement(By.id("featured-category-box"));
            Assert.assertTrue(beautyAndSpaOptionLink.isDisplayed(), "The message is not displayed");

            driver.findElement(By.xpath("//span[@id='category-arrow']")).click();


            // choosing 'SPA' link;


            Thread.sleep(2000);
            driver.findElement(By.xpath("(//*[@class='name truncated'])[9]")).click();
            Thread.sleep(2000);
            WebElement spaLink = driver.findElement(By.xpath("//span[@class='featured-title c-txt-white']"));


            Assert.assertTrue(spaLink.isDisplayed(), "Spa link is not displayed");


            // price link;

            Thread.sleep(2000);
            driver.findElement(By.xpath("(//span[@class='featured-title c-txt-black'])[1]"));
            // arrow;
            driver.findElement(By.id("rangeFilters-arrow")).click();

            // min price select;

            Thread.sleep(2000);

            Actions action = new Actions(driver);

            WebElement max = driver.findElement(By.xpath("//input[@title='Price max']"));

            action.moveToElement(max).build().perform();
            action.doubleClick().build().perform();

            driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys("50" + Keys.ENTER);


        }


        @Test
        public void GrouponBeautyAndSpaTest2() throws InterruptedException {

            // as a user i should be able to see all the hair salons options from "HAIR" link by topseller and highest ratings;
            mp.mainSearchChangeCity("New York City");

            // choosing Beauty and Spas link;

            Thread.sleep(2000);
            WebElement beautyAndSpaOptionLink = driver.findElement(By.id("featured-category-box"));
            Assert.assertTrue(beautyAndSpaOptionLink.isDisplayed(), "The message is not displayed");


            WebElement arrowLink = driver.findElement(By.xpath("//span[@id='category-arrow']"));
            Thread.sleep(2000);
            arrowLink.click();


            // HAIR link;
            Thread.sleep(2000);
            WebElement hairLink = driver.findElement(By.xpath("(//label[@class='name truncated'])[5]"));
            hairLink.click();

            Thread.sleep(5000);

            //byPopularity link


            WebElement byPopularity = driver.findElement(By.xpath("(//span[@class='featured-title c-txt-black'])[3]"));
            byPopularity.click();

            Assert.assertTrue(byPopularity.isDisplayed(), "byPopularity link is FAILED");

            Thread.sleep(2000);

            WebElement all = driver.findElement(By.xpath("(//div[@class='radio-label-content'])[2]"));
            all.click();


            // by ratings link;

            Thread.sleep(3000);
            driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
            driver.findElement(By.id("rating-arrow")).click();
            driver.findElement(By.id("featured-rating-[4..5]-label")).click();
            Thread.sleep(2000);

            String rating = driver.findElement(By.id("featured-rating-box")).getText();
            Assert.assertEquals(rating, "4 Star & Up", "Verification of Rating is FAILED");


        }

    // as a user i should be able to click on Nails option, and also see Blog window

        @Test
        public void GrouponBeautyAndSpa3() throws InterruptedException {

            mp.mainSearchChangeCity("New York City");
            Thread.sleep(3000);
            WebElement beautyAndSpaOptionLink = driver.findElement(By.id("featured-category-box"));
            Assert.assertTrue(beautyAndSpaOptionLink.isDisplayed(), "The message is not displayed");


            WebElement arrowLink = driver.findElement(By.xpath("//span[@id='category-arrow']"));
            arrowLink.click();

            // accessing nails

            WebElement nailLink = driver.findElement(By.xpath("(//label[@class='name truncated'])[10]"));
            nailLink.click();

            Thread.sleep(4000);

            // blog link accessing;

            WebElement blog = driver.findElement(By.linkText("Blog"));
            blog.click();
        }

        @AfterMethod
        public void close(){
            driver.close();
        }

    }






