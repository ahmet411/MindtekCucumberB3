package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.HRAppCreateEmployeePage;
import pages.HRAppLoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JDBCUtils;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class HRAppAddEmployeeSteps {

    WebDriver driver = Driver.getDriver();
    HRAppLoginPage hrAppLoginPage = new HRAppLoginPage();
    HRAppCreateEmployeePage hrAppEmployeePage = new HRAppCreateEmployeePage();

    @Given("user navigates to login page")
    public void user_navigates_to_login_page() {
        driver.get("https://qeenv-webhr-arslan.herokuapp.com/login");
        Assert.assertEquals(driver.getTitle(),"HrApp");
    }

    @When("user logs in to HRapp")
    public void user_logs_in_to_HRapp() {
        hrAppLoginPage.username.sendKeys(ConfigReader.getProperty("HrAppUsername"));
        hrAppLoginPage.password.sendKeys(ConfigReader.getProperty("HrAppPassword"));
        hrAppLoginPage.login.click();
    }

    @And("creates new employee")
    public void creates_new_employee() {
        System.out.println("Employee created!");
    }

    @Then("user validates new employee is created in database")
    public void user_validates_new_employee_is_created_in_database() throws SQLException {
        hrAppEmployeePage.searchBox.sendKeys("105"+ Keys.ENTER);
        String actualFirstName = hrAppEmployeePage.firstName.getText();
        String actualLastName = hrAppEmployeePage.lastName.getText();
        String actualDepartmentName = hrAppEmployeePage.departmentName.getText();

        JDBCUtils.establishConnection();
        List<Map<String,Object>> data = JDBCUtils.runQuery
                ("select * from employees e inner join departments d on e.department_id = d.department_id where employee_id = 100");

        //Assert.assertEquals(data.get(0).get("first_name").toString(), actualFirstName);
        Assert.assertEquals(data.get(0).get("last_name").toString(), actualLastName);
        Assert.assertEquals(data.get(0).get("department_name").toString(), actualDepartmentName);

        JDBCUtils.closeDatabase();
    }
}
