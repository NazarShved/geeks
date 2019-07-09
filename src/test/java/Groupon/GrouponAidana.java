package Groupon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GrouponAidana {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.groupon.com/");

      // verifyPriceRangeInDealsOfTheDay(driver);
        locationVerificationInFeatured(driver);
        //priceRangeVerificationInFeatured(driver,1,1000);

        //Hello World
    }

    //Click on "Popularity" and verify the default value
    public static void verifyDefaultValuePopularity(WebDriver driver) throws InterruptedException{
        driver.findElement(By.id("featured-badge-box")).click();
        Thread.sleep(1000);
        if (driver.findElement(By.id("featured-badge-all-label")).getText().equals("All")) {
            System.out.println("The Default Value Verification for Sorting by Popularity Passed");
        } else {
            System.out.println("The Default Value Verification for Sorting by Popularity Failed");
        }
    }
    //Click on "Rating" and verify the default value
    public static void verifyDefaultValueRating(WebDriver driver)throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("featured-rating-box")).click();
        Thread.sleep(1000);
        if (driver.findElement(By.id("featured-rating-all-label")).getText().equals("All")) {
            System.out.println("The Default Value Verification for Sorting by Rating Passed");
            System.out.println("============================");
        } else {
            System.out.println("The Default Value Verification for Sorting by Rating Failed");
        }
        Thread.sleep(1000);
    }
    //Sort by Low to High
    public static void sortLowToHigh(WebDriver driver)throws InterruptedException {
        driver.findElement(By.id("featured-sort-box")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("featured-sort-price:asc-label")).click();
        Thread.sleep(1000);
    }

    //TEST CASE #1 "Deals Of the Day"
    //As a user I want to be able to verify if the price of every item is within the given price range.
    //1.Go to "Deals Of the Day" Functionality
    //2.Select the category "For the Home"
    //3. Select the price range 26-50 under "Price Breakdown"
    //4.Verify if the item's prices are within the selected price range

    public static boolean verifyPriceRangeInDealsOfTheDay(WebDriver driver)throws InterruptedException{
        try {
            Thread.sleep(1000);
            //Deals of the Day Button
            driver.findElement(By.id("goods-deal-of-day-tab-link")).click();

        }catch (Exception e){
            //No Thanks Button
            driver.findElement(By.id("nothx")).click();
            Thread.sleep(1500);
            driver.findElement(By.id("goods-deal-of-day-tab-link")).click();
            Thread.sleep(1500);
        }
        //Click For the Home Checkbox
        driver.findElement(By.xpath("//label[@for='facet_category_for-the-home']")).click();
        Thread.sleep(1000);
        //Select price range under "Price Breakdown"
        driver.findElement(By.xpath("//label[@for='range_26_50']")).click();

        Thread.sleep(1000);
        List<WebElement> priceList = driver.findElements(By.xpath("//div[contains(@class, 'cui-price-discount cui-urgent')]"));
        Thread.sleep(1000);
        System.out.println("The number of product prices: " + priceList.size());
        Thread.sleep(1000);

        for (WebElement productPrice : priceList) {
            Thread.sleep(1000);
            ArrayList<String> actualPriceList = new ArrayList();
            actualPriceList.add(productPrice.getText());
            Thread.sleep(1000);
            System.out.print(actualPriceList);
            //Getting rid of $ sign
            String cut$SignPrice = actualPriceList.get(0).substring(1);
            ArrayList<Double> actualPriceWithout$ = new ArrayList();
            actualPriceWithout$.add(Double.parseDouble(cut$SignPrice));
            for (int i = 0; i < actualPriceWithout$.size(); i++) {
                if (actualPriceWithout$.get(i) < 26 || actualPriceWithout$.get(i) > 50) {
                    //System.out.println("====After removing $ sign: " + cut$SignPrice);
                    return false;
                } //else {
//                    System.out.println("The price range verification FAILED");
//                    break;
//                }
            }
        }
        //System.out.println("The price range verification PASSED");
        Thread.sleep(1000);

        return true;
    }


    //TEST CASE #2 "Featured"
    //As a user I want to be able to verify if all the results are in the given location.
    //1.Go to Restaurants-> Food & Drinks--> Groceries & Markets
    //2.Select location "Chicago"
    //3.Verify all the result are within the chosen location ("Chicago")
    //4.Verify the default value for popularity is "All"
    //5.Verify the default value for Ratings is "All"
    //6.Sort by Low to High

    public static boolean locationVerificationInFeatured(WebDriver driver)throws InterruptedException {
        try {
            Thread.sleep(1000);
            //Click on Featured Button
            driver.findElement(By.id("home-tab-link")).click();
        } catch (Exception e) {
            //No Thanks Button
            driver.findElement(By.id("nothx")).click();
            Thread.sleep(1500);
            driver.findElement(By.id("home-tab-link")).click();
            Thread.sleep(1500);
        }
        //Click on Restaraunts Button
        driver.findElement(By.xpath("(//div[@class='cui-nav-icon-image-wrapper'])[3]")).click();
        Thread.sleep(2000);
        //Click on Food and Drinks
        driver.findElement(By.id("featured-category-box")).click();
        Thread.sleep(2000);
        //Click on Groceries & Markets
        driver.findElement(By.xpath("(//label[@class='name truncated'])[3]")).click();
        Thread.sleep(2000);
        //Click on "Location"
        driver.findElement(By.id("featured-location-box")).click();
        Thread.sleep(2000);
        //Choose "Chicago"// any City
       WebElement city = driver.findElement(By.xpath("//div[@class = 'refinement-list']/div[3]"));
       String cityName = city.getText();
        System.out.println(cityName);
        Thread.sleep(2000);

        //Verify all the result are within the chosen location ("Chicago)
        List<WebElement> locations = driver.findElement(By.id("pull-cards")).findElements(By.cssSelector(".cui-location-name"));
        Thread.sleep(2000);
        System.out.println("The number of locations : " + locations.size());
        System.out.println("============================");
        Thread.sleep(1000);
        for (WebElement location : locations) {
            System.out.println(location.getText());

            if (!location.getText().contains(cityName)) {

                return false;
            }
//             else {
//                System.out.println("Location verification Failed");
//                break;
//            }
        }
        Thread.sleep(1000);
        System.out.println("============================");
       // System.out.println("Location verification Passed");
        Thread.sleep(1000);

        //Using ready methods to verify default values for "Popularity" and "Ratings" and Sort by "Low to High"
        verifyDefaultValuePopularity(driver);
        verifyDefaultValueRating(driver);
        sortLowToHigh(driver);
        return true;
    }




    //TEST CASE #3 "Featured"
    //As a user I want to be able verify if the products are within the price range passed by the user
    //1.Go to Restaurants-> Food & Drinks--> Groceries & Markets
    //2.Set min and max prices
    //3.Click on Location and select "Chicago"
    //4.Verify the default value for popularity is "All"
    //5.Verify the default value for Ratings is "All"
    //6.Sort by Low to High
    //Verify the products are within the price range passed by the user

    public static boolean priceRangeVerificationInFeatured (WebDriver driver, int minPrice, int maxPrice )
            throws InterruptedException {

        try {
            Thread.sleep(1000);
            //Click on Featured Button
            driver.findElement(By.id("home-tab-link")).click();
        } catch (Exception e) {
            //No Thanks Button
            driver.findElement(By.id("nothx")).click();
            Thread.sleep(1500);
            driver.findElement(By.id("home-tab-link")).click();
            Thread.sleep(1500);
        }

        Thread.sleep(1000);
        //Click on Restaraunts Button
        driver.findElement(By.xpath("(//div[@class='cui-nav-icon-image-wrapper'])[3]")).click();
        Thread.sleep(2000);
        //Click on Food and Drinks
        driver.findElement(By.id("featured-category-box")).click();
        Thread.sleep(2000);
        //Click on Groceries & Markets
        driver.findElement(By.xpath("(//label[@class='name truncated'])[3]")).click();
        Thread.sleep(1000);
        //Click on price
        driver.findElement(By.xpath("//div[@id='featured-rangeFilters-box']")).click();
        Thread.sleep(2000);
        //Set the max and min values for Price
        String strMinValue = "" + minPrice;
        String strMaxValue = "" + maxPrice;
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@title='Price min']")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@title='Price min']")).sendKeys(strMinValue + Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='featured-rangeFilters-box']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@title='Price max']")).sendKeys(strMaxValue + Keys.ENTER);
        Thread.sleep(1000);
        //Click on "Location"
        driver.findElement(By.id("featured-location-box")).click();
        Thread.sleep(2000);
        //Choose "Chicago"
       // driver.findElement(By.id("featured-location-chicago-label")).click();
        driver.findElement(By.xpath("//div[@class = 'refinement-list']/div[3]"));
        Thread.sleep(1000);

        //Using ready methods to verify default values for "Popularity" and "Ratings" and Sort by "Low to High"
        verifyDefaultValuePopularity(driver);
        verifyDefaultValueRating(driver);
        sortLowToHigh(driver);

        //Verify the price range for the results
        List<WebElement> listOfPrices = driver.findElement(By.id("pull-cards")).findElements(By.cssSelector(".cui-price-discount.c-txt-price"));
        System.out.println("The number of product prices: " + listOfPrices.size());
        Thread.sleep(1000);

        for (WebElement price : listOfPrices) {
            String priceWithout$Sign = price + "";
            priceWithout$Sign = price.getText().substring(1).replace(",", "");
            double priceDouble = Double.parseDouble(priceWithout$Sign);
            if (priceDouble < minPrice || priceDouble > maxPrice) {
                //System.out.println(priceDouble);
                return false;
            }
//            } else {
//                System.out.println("Price Range Verifiction Failed");
//                break;
            }

        Thread.sleep(1000);
        System.out.println("Price Range Verification Passed");
        return true;
    }
}
