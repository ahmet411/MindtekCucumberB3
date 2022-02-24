package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.EtsyAppHomePage;
import pages.EtsyAppSearchPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Locale;

public class EtsyAppSteps {

    WebDriver driver = Driver.getDriver();
    EtsyAppHomePage etsyAppHomePage = new EtsyAppHomePage();
    EtsyAppSearchPage etsyAppSearchPage = new EtsyAppSearchPage();

    @Given("user navigates to Etsy application")
    public void user_navigates_to_Etsy_application() {
        driver.get(ConfigReader.getProperty("EtsyURL"));

    }

    @When("user searches for {string}")
    public void user_searches_for(String item) throws InterruptedException {
        etsyAppHomePage.searchBox.sendKeys(item + Keys.ENTER);
        Thread.sleep(3000);

    }

    @When("user applies price filter over {int}")
    public void user_applies_price_filter_over(Integer price) {
        etsyAppSearchPage.filterButton.click();
        etsyAppSearchPage.priceRadioButton.click();
        etsyAppSearchPage.applyButton.click();

    }

    @Then("user validates that item prices are over {int}")
    public void user_validates_that_item_prices_are_over(Integer price) throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> prices = etsyAppSearchPage.prices;
        //   BrowserUtils.numberOfElements(By.xpath("//div[@class='wt-bg-white wt-display-block wt-pb-xs-2 wt-mt-xs-0']//span[@class='currency-value']"),etsyAppSearchPage.prices.size()-1);

        for (WebElement element : prices) {
//            System.out.println(element.getText());
            String priceStr = element.getText().replace(",", ""); //"32,402.00" --> 32402.00
            double doublePrice = Double.parseDouble(priceStr);
            System.out.println(doublePrice);
            Assert.assertTrue(doublePrice >= price);
        }

    }

    @Then("user validates search result items contain keyword {string} and that item prices are over {int}")
    public void userValidatesSearchResultItemsContainKeywordAndThatItemPricesAreOver(String item, int price) throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> prices = etsyAppSearchPage.prices;
        for (WebElement element : prices) {
//            System.out.println(element.getText());
            String priceStr = element.getText().replace(",", ""); //"32,402.00" --> 32402.00
            double doublePrice = Double.parseDouble(priceStr);
            System.out.println(doublePrice);
            Assert.assertTrue(doublePrice >= price);
        }

        List<WebElement> itemNames = etsyAppSearchPage.itemNames;

        for (WebElement element : itemNames) { // looping through 64 WebElements
            if (element.getText().toLowerCase(Locale.ROOT).contains(item) || element.getText().toLowerCase(Locale.ROOT).contains("rug")){
               StringBuilder newItemNames = new StringBuilder();
               newItemNames.append(element.getText());
                System.out.println(newItemNames);
                Assert.assertTrue(newItemNames.toString().toLowerCase(Locale.ROOT).contains(item) || newItemNames.toString().toLowerCase(Locale.ROOT).contains("rug"));

            }
        }
    }

    @When("user clicks on {string} section")
    public void userClicksOnSection(String section) {

        if(section.equalsIgnoreCase("Jewelry and Accessories")){
            etsyAppHomePage.jewelryAndAccessories.click();
        }else if (section.equalsIgnoreCase("End of Year Sales Event")){
            etsyAppHomePage.endOfYearSalesEvent.click();
        }else if (section.equalsIgnoreCase("Clothing and Shoes")){
            etsyAppHomePage.clothingAndShoes.click();
        }else if (section.equalsIgnoreCase("Home and Living")){
            etsyAppHomePage.homeAndLiving.click();
        }else if (section.equalsIgnoreCase("Toys and Entertainment")){
            etsyAppHomePage.toysAndEntertaiment.click();
        }else if (section.equalsIgnoreCase("Art and Collectibles")){
            etsyAppHomePage.artAndCollectibles.click();
        }else if (section.equalsIgnoreCase("Craft Supplies and Tools")){
            etsyAppHomePage.craftSupplies.click();
        }else if (section.equalsIgnoreCase("Gift and Gift Cards")) {
            etsyAppHomePage.giftAndGiftCards.click();
        }else if (section.equalsIgnoreCase("Wedding and Party")){
            etsyAppHomePage.wedingAndParty.click();
        }

    }

    @Then("user validates title is {string}")
    public void userValidatesTitleIs(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);

    }
}
