package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

    public class HRAppCreateEmployeePage {

        public HRAppCreateEmployeePage(){
            WebDriver driver = Driver.getDriver();
            PageFactory.initElements(driver,this);
        }

        @FindBy(xpath = "/html/body/app-root/div[1]/app-employee-details/div[2]/input")
        public WebElement searchBox;

        @FindBy(id = "searchButton")
        public WebElement Search;

        @FindBy (xpath = "//td[2]")
        public WebElement firstName;

        @FindBy (xpath = "//td[3]")
        public WebElement lastName;

        @FindBy (xpath = "//td[4]")
        public WebElement departmentName;

    }

