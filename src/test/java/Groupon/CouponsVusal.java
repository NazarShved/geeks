package Groupon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CouponsVusal {
    static WebDriver driver;
    static MainPage mp;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        mp = new MainPage();
        this.driver = mp.driver;
        Thread.sleep(6500);
        mp.pickMenuOption(By.id("coupons-tab-link"));
        Thread.sleep(6500);
        WebElement searchCoupons = driver.findElement(By.id("coupons-search-field"));
        searchCoupons.sendKeys("adidas");
        WebElement searchButton = driver.findElement(By.xpath("//input[@type='submit']"));
        searchButton.click();
    }

    // As a user I want to choose Adidas coupons and check
    // if number of coupons listed are matching "All coupons" number
    @Test
    public void searchCouponsAdidas(){

        Assert.assertTrue(driver.getTitle().contains("adidas"));
    }
    // As a user I want to see if All coupons function
    // showing the same numbers as coupons real numbers on the page
    @Test
    public void checkNumberOFCoupons()throws InterruptedException{
        Thread.sleep(2000);
        String allCoupon = driver.findElement(By.xpath("//*[contains(text(),'All Coupons')]")).getText();
        String res = allCoupon.substring(allCoupon.indexOf("(") + 1, allCoupon.length()-1);
        int numOfAllCoupons = Integer.parseInt(res);
        List<WebElement> coupons = driver.findElements(By.xpath("//div[@class='nine columns']/div //li"));
        int numOFDisplayedCoupons=0;
        for(WebElement el : coupons){
            if (!el.getText().contains("See sale")) {
                continue;
            } else {
                numOFDisplayedCoupons++;
            }
        }
        Assert.assertEquals(numOfAllCoupons, numOFDisplayedCoupons, "All coupon size and displayed coupon size are not equal");

    }
    // As a user when press on coupon want to see if it goes to the Adidas original website
    @Test
    public static void checkLinks()throws InterruptedException {
        int countLinks = 0;
        List<WebElement> coupons = driver.findElements(By.xpath("//div[@class='nine columns']/div //li"));
        for (WebElement el : coupons) {
            if (!el.getText().contains("See sale")) continue;
            String mainW = driver.getWindowHandle();
            el.click();
            Set<String> wndws = driver.getWindowHandles();
            Iterator<String> it = wndws.iterator();
            it.next();
            String newW = it.next();
            //Thread.sleep(15000);
            driver.switchTo().window(newW);
            mp.wait.until(ExpectedConditions.urlContains("www.adidas.com"));
//           System.out.println(driver.getCurrentUrl());
            if(!driver.getCurrentUrl().toLowerCase().contains("adidas")) countLinks++;
            driver.close();
            driver.switchTo().window(mainW);
            if (driver.findElement(By.className("vodal-close")).isDisplayed())
                driver.findElement(By.className("vodal-close")).click();
        }
        Assert.assertTrue(countLinks == 0);

    }

    @AfterMethod
    public static void tearDown(){
        driver.close();
    }
}
