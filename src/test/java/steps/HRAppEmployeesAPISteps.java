package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.HRAppHomePage;
import pojos.hrapipojos.Department;
import pojos.hrapipojos.Employee;
import pojos.hrapipojos.Job;
import pojos.hrapipojos.Location;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HRAppEmployeesAPISteps {

    HRAppHomePage hrAppHomePage=new HRAppHomePage();
    List<Map<String, Object>> dbData;
    Response response;
    String employeeId;
    Integer numberOfDepartmentEmployeesDB;

    @Given("user gets employee from database with employeeId {int}")
    public void user_gets_employee_from_database_with_employeeId(Integer employeeId) throws SQLException {
        JDBCUtils.establishConnection();
        dbData = JDBCUtils.runQuery("select e.first_name, e.last_name, d.department_name\n" +
                "from employees e join departments d\n" +
                "on e.department_id=d.department_id\n" +
                "where employee_id="+employeeId);
    }

    @When("user searches for employee with {int} employeeId")
    public void user_searches_for_employee_with_employeeId(Integer employeeId) {
        hrAppHomePage.searchBox.sendKeys(employeeId.toString()+Keys.ENTER);
        hrAppHomePage.searchButton.click();
    }

    @Then("user validates UI data matches with DB data")
    public void user_validates_UI_data_matches_with_DB_data() {
        String uiFirstName = hrAppHomePage.firstName.getText();
        String uiLastName = hrAppHomePage.lastName.getText();
        String uiDepartmentName = hrAppHomePage.departmentName.getText();

        String dbFirstName = dbData.get(0).get("first_name").toString();
        String dbLastName = dbData.get(0).get("last_name").toString();
        String dbDepartmentName = dbData.get(0).get("department_name").toString();

        Assert.assertEquals(dbFirstName,uiFirstName);
        Assert.assertEquals(dbLastName,uiLastName);
        Assert.assertEquals(dbDepartmentName,uiDepartmentName);
    }

    @When("user get employee with get employee api call with {int} employeeId")
    public void user_get_employee_with_get_employee_api_call_with_employeeId(Integer employeeId) {
        response =
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Accept","application/json")
                        .when().get("/api/employees/"+employeeId);
    }

    @Then("user validates {int} statusCode")
    public void user_validates_statusCode(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("user validates API data matches with DB data")
    public void user_validates_API_data_matches_with_DB_data() {
        String dbFirstName = dbData.get(0).get("first_name").toString();
        String dbLastName = dbData.get(0).get("last_name").toString();
        String dbDepartmentName = dbData.get(0).get("department_name").toString();

        String apiFirstName=response.body().jsonPath().getString("firstName");
        String apiLastName=response.body().jsonPath().getString("lastName");
        String apiDepartmentName=response.body().jsonPath().getString("department.departmentName");

        Assert.assertEquals(dbFirstName,apiFirstName);
        Assert.assertEquals(dbLastName,apiLastName);
        Assert.assertEquals(dbDepartmentName,apiDepartmentName);
    }

    @Given("user creates employee with post api call with data")
    public void user_creates_employee_with_post_api_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        Map<String, Object> data=dataTable.asMap(String.class, Object.class);
       /*
       Request POST employee
       1. URL
       2. Headers
       3. Body
        */
        Employee employee = new Employee();
        Department department = new Department();
        department.setDepartmentId(10);
        department.setDepartmentName(data.get("departmentName").toString());
        Location location=new Location();
        location.setLocationCity("MAUI");
        location.setLocationCountry("US");
        location.setLocationId(333);
        location.setLocationState("HI");
        department.setLocation(location);
        employee.setDepartment(department);
        employee.setFirstName(data.get("firstName").toString());
        employee.setEmployeeId(0);
        Job job=new Job();
        job.setJobId("IT_PROG");
        job.setSalary(100000.0);
        job.setTitle("SDET");
        employee.setJob(job);
        employee.setLastName(data.get("lastName").toString());

        response =
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Content-Type","application/json")
                        .and().header("Accept","application/json")
                        .and().body(employee)
                        .when().post("/api/employees");

        employeeId=response.body().jsonPath().getString("employeeId");
    }

    @When("user searches for created employee")
    public void user_searches_for_created_employee() {
        hrAppHomePage.searchBox.sendKeys(employeeId);
        hrAppHomePage.searchButton.click();
    }

    @Then("user validates employee is created in UI with data")
    public void user_validates_employee_is_created_in_UI_with_data(io.cucumber.datatable.DataTable dataTable) {
        String uiFirstName=hrAppHomePage.firstName.getText();
        String uiLastName=hrAppHomePage.lastName.getText();
        String uiDepartmentName=hrAppHomePage.departmentName.getText();

        Map<String, Object> data=dataTable.asMap(String.class,Object.class);
        String expectedFirstName = data.get("firstName").toString();
        String expectedLastName = data.get("lastName").toString();
        String expectedDepartmentName = data.get("departmentName").toString();

        Assert.assertEquals(expectedFirstName, uiFirstName);
        Assert.assertEquals(expectedLastName, uiLastName);
        Assert.assertEquals(expectedDepartmentName, uiDepartmentName);
    }

    @Given("user get number of employees in {string} department from DB")
    public void user_get_number_of_employees_in_department_from_DB(String departmentName) throws SQLException {
        JDBCUtils.establishConnection();
        dbData = JDBCUtils.runQuery("select count(e.employee_id)\n" +
                "from employees e join departments d\n" +
                "on e.department_id=d.department_id\n" +
                "where d.department_name='"+departmentName+"'");
        numberOfDepartmentEmployeesDB = Integer.parseInt(dbData.get(0).get("count").toString()); // Object -> String -> Integer
    }

    @When("user selects {string} department from dropdown")
    public void user_selects_department_from_dropdown(String departmentName) throws InterruptedException {
        BrowserUtils.selectByText(hrAppHomePage.departmentsDropdown,departmentName);
        Thread.sleep(500);
    }

    @Then("user validates UI number of employees matches with DB number")
    public void user_validates_UI_number_of_employees_matches_with_DB_number() {
        Integer numberOfDepartmentEmployeesUI = Driver.getDriver().findElements(By.xpath("//tr")).size()-1;
        Assert.assertEquals(numberOfDepartmentEmployeesDB,numberOfDepartmentEmployeesUI);
    }

    @When("user gets employees from {string} department with api call")
    public void user_gets_employees_from_department_with_api_call(String departmentName) throws SQLException {
        JDBCUtils.establishConnection();
        String departmentID = JDBCUtils.runQuery("select department_id\n" +
                "from departments\n" +
                "where department_name='"+departmentName+"'").get(0).get("department_id").toString();

        response =
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Accept", "application/json")
                        .when().get("/api/departments/"+departmentID+"0/employees");
    }

    @Then("user validates number of employees in API matches with DB number")
    public void user_validates_number_of_employees_in_API_matches_with_DB_number() {
        Employee[] employees = response.body().as(Employee[].class);
        Integer numberOfDepartmentEmployeesAPI = employees.length;

        Assert.assertEquals(numberOfDepartmentEmployeesDB,numberOfDepartmentEmployeesAPI);
    }

}





