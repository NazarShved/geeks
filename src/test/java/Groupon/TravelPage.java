package Groupon;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TravelPage {
    //Nazar
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @FindBy (css = ".ui-autocomplete-input")
    WebElement pickCityField;

    @FindBy(xpath = "//div[@class = 'calendar-background']/table/thead/tr/th")
    WebElement calendarMonthValue;

    @FindBy(css = ".icon-arrow-right")
    WebElement calendarNextMonthArrow;

    @FindBy (xpath = "//table[@class = 'calendar-instance instrument'] //td")
    List<WebElement> calendarBodyNumbers;

    @FindBy (xpath = "(//div[@class = 'icon-selection-box instrument'])[2]/span")
    WebElement calendarOpen;

    @FindBy(xpath = "//span[@class = 'cui-location-name']")
    List<WebElement> searchResults;


    public TravelPage(WebDriver driver) throws Exception {
        if(!driver.getCurrentUrl().contains("getaways")) throw new Exception("Wrong page");

        this.driver = driver;

        js = (JavascriptExecutor) driver;
       PageFactory.initElements(driver, this);
       wait = new WebDriverWait(driver, 5);
    }


    public void clickOnSearchIcon(){
        driver.findElement(By.cssSelector(".btn.btn-invert.instrument")).click();
    }

    public void select(By by){
        driver.findElement(by).click();
    }


    public void pickCity(String city){

        wait.until(ExpectedConditions.elementToBeClickable(pickCityField));
        pickCityField.click();
        pickCityField.sendKeys(city.substring(0, 3));

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ui-corner-all.ui-state-focus")));
        while (true) {
            if (driver.findElement(By.cssSelector(".ui-corner-all.ui-state-focus")).getText().toLowerCase().contains(city.toLowerCase())) {
               pickCityField.sendKeys(Keys.ENTER);
                break;
            }
            pickCityField.sendKeys(Keys.DOWN);

        }
    }

    public void pickDate(String month, String day) throws Exception {

        String str = calendarMonthValue.getText();
        if (!str.toLowerCase().contains(month.toLowerCase())) {
            while (!str.toLowerCase().contains(month.toLowerCase())) {
                calendarNextMonthArrow.click();
                str = calendarMonthValue.getText();
            }
        }

        for (WebElement el :calendarBodyNumbers) {
            if (el.getText().equalsIgnoreCase(day)) {
                el.click();
                break;
            }
            if (calendarBodyNumbers.indexOf(el) == calendarBodyNumbers.size() - 1) throw new Exception("Pick the rigth date");
        }
    }

    public void pickDate(String depMonth, String depDay, String retMonth, String retDay) throws Exception {

        calendarOpen.click();
        pickDate(depMonth, depDay);
        pickDate(retMonth, retDay);

    }



    public  int checkCity() {

        js = (JavascriptExecutor) driver;
        String script = "return document.getElementsByClassName(\"ui-autocomplete-input\")[0].value;";
        String value = (String) js.executeScript(script);
        String city = value.substring(0, value.indexOf(","));

        wait.until(ExpectedConditions.elementToBeClickable(searchResults.get(2)));
        int countMissCity = 0;
        for (WebElement el : searchResults) {
            if (el.getText().isEmpty()) continue;
            if (!el.getText().contains(city)) countMissCity++;
        }

        return countMissCity;
    }


}
