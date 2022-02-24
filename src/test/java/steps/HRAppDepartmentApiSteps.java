package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import pojos.hrapipojos.Department;
import pojos.hrapipojos.Location;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class HRAppDepartmentApiSteps {

    Response response;
    Integer departmentId;

    @Given("User creates department with departments post call having random data")
    public void user_creates_department_with_departments_post_call_having_random_data() {
        Department departmentBody =new Department();
        Random random=new Random();
        departmentId=random.nextInt();

        departmentBody.setDepartmentId(departmentId);
        departmentBody.setDepartmentName("Arslan"+departmentId);
        Location location=new Location();
        location.setLocationCity("Chicago");
        location.setLocationCountry("US");
        location.setLocationId(123);
        location.setLocationState("IL");
        departmentBody.setLocation(location);

        response =
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Content-Type", "application/json")
                        .and().body(departmentBody)
                        .when().post("/api/departments");
    }

    @Then("User validates status code is {int}")
    public void user_validates_status_code_is(Integer expectedStatuscode) {
        response.then().statusCode(expectedStatuscode);
    }

    @When("User gets created department")
    public void user_gets_created_department() {
        response =
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Accept","application/json")
                        .when().get("/api/departments/"+departmentId);
    }

    @Then("User validates created department in Departments dropdown")
    public void user_validates_created_department_in_Departments_dropdown() {
        String uiDepartment = Driver.getDriver().findElement(By.xpath("//option[@value='"+departmentId+"']")).getText();
        Assert.assertEquals("Arslan"+departmentId,uiDepartment);
    }

    @When("user deletes created department")
    public void user_deletes_created_department() {
        response =
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .when().delete("/api/departments/"+departmentId);
    }

}
