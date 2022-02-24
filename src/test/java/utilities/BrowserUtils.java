package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class BrowserUtils {

    Map<Object,String> map = new HashMap<>();




    /*
    Method that will accept dropdown WebElement
    and value of that dropdown, and it will select
    that value which is provided in parameter.
    Ex;
    .selectByValue(dropdownElement, "1"); -> void

     */
    public static void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }
     /*
    Method that will accept dropdown WebElement
    and text of that dropdown, and it will select
    that text which is provided in parameter.
    Ex;
    .selectByText(dropdownElement, "1"); -> void

     */

    public static void selectByText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    /*
    This method will take a screenshot of the browser.
    Ex:
    .takeScreenshot("LoginTest");
     */
    public static void takeScreenshot(String name) throws IOException {
        WebDriver driver = Driver.getDriver();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/src/test/resources/Screenshots"
                + name+ System.currentTimeMillis() + ".png";
        File file = new File(path);
        FileUtils.copyFile(screenshot, file);
    }
    /*
    This method will wait until element is clickable.
    Ex:
        .waitElementToBeClickable(element); -> returns WebElement element;

     */

    public static WebElement waitElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(element));
        return element1;
    }

    public static WebElement waitElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        WebElement element1 = wait.until(ExpectedConditions.visibilityOf(element));
        return element1;
    }
    public static WebElement waitForWebElement(By locator, WebElement element) {
        WebDriver driver = Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement element2 = wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
        return element2;
    }
    public static List<WebElement> numberOfElements(final By locator, final Integer number) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        List<WebElement> elements = wait.until(ExpectedConditions.numberOfElementsToBe(locator,number));
        return elements;
        }
    /*
    This method will scroll the page.
    Ex:
    .scroll(250);
     */
    public static void scrollUp( int pixels){
        WebDriver driver = Driver.getDriver();
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("window.scrollBy(0,"+pixels+")");

    }
    /*
    This method will hover over to element in browser.
    Ex:
          .hoverOver(element);
     */
    public static void hoverOver(WebElement element){
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }




}

