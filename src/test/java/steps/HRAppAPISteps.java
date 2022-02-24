package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ConfigReader;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class HRAppAPISteps {

    Response response;

    @Given("User sends get location api call with {int} locationId")
    public void user_sends_get_location_api_call_with_locationId(Integer locationId) {
        response =
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Accept","application/json")
                        .when().get("/api/location/"+locationId);
    }

    @Then("User validates {int} status code")
    public void user_validates_status_code(Integer expectedStatusCode) {
        Integer actualStatusCode=response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("User validates response body with data")
    public void user_validates_response_body_with_data(io.cucumber.datatable.DataTable dataTable) {
        Map<String, Object> expectedData=dataTable.asMap(String.class,Object.class);

        for(String key : expectedData.keySet()){
            String expectedValue=expectedData.get(key).toString();
            String actualValue=response.body().jsonPath().getString(key);
            Assert.assertEquals(expectedValue, actualValue);
        }

    }

}









