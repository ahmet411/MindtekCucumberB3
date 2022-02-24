package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ConfigReader;

import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class BooksAPISteps {

    String orderId;
    Response response;
    String token;

    public String getToken(){
        Random random = new Random();
        int randomNum=random.nextInt();

        Response response=
                given().baseUri(ConfigReader.getProperty("BooksAPIBaseURI"))
                        .and().header("Content-Type","application/json")
                        .and().header("Accept","application/json")
                        .and().body("{\n" +
                                "   \"clientName\": \"Arslan\",\n" +
                                "   \"clientEmail\": \"arslan"+randomNum+"@example.com\"\n" +
                                "}")
                        .when().post("/api-clients/");
        return response.body().jsonPath().getString("accessToken");
    }

    @Given("User sends post order api call with data")
    public void user_sends_post_order_api_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        Map<String, Object> data=dataTable.asMap(String.class,Object.class);
        token=getToken();
        Response response =
                given().baseUri(ConfigReader.getProperty("BooksAPIBaseURI"))
                        .and().header("Content-Type","application/json")
                        .and().header("Authorization","Bearer "+token)
                        .and().body(data)
                        .when().post("/orders");
        System.out.println("Order created with status code "+response.getStatusCode());
        orderId = response.body().jsonPath().getString("orderId");
        System.out.println("OrderId: "+orderId);
    }

    @When("User sends get order api call")
    public void user_sends_get_order_api_call() {
        response =
                given().baseUri(ConfigReader.getProperty("BooksAPIBaseURI"))
                        .and().header("Accept","application/json")
                        .and().header("Authorization","Bearer "+token)
                        .when().get("/orders/"+orderId);
    }

    @Then("User validates status code {int}")
    public void user_validates_status_code(Integer int1) {
        Integer actualStatusCode=response.getStatusCode();
        Assert.assertEquals(int1,actualStatusCode);
    }

    @When("User sends patch order api call")
    public void user_sends_patch_order_api_call(io.cucumber.datatable.DataTable dataTable) {
        Map<String, Object> data=dataTable.asMap(String.class,Object.class);

        response =
                given().baseUri(ConfigReader.getProperty("BooksAPIBaseURI"))
                        .and().header("Content-Type","application/json")
                        .and().header("Accept","application/json")
                        .and().header("Authorization","Bearer "+token)
                        .and().body(data)
                        .when().patch("/orders/"+orderId);
    }


    @When("User sends delete order api call")
    public void user_sends_delete_order_api_call() {
        response =
                given().baseUri(ConfigReader.getProperty("BooksAPIBaseURI"))
                        .and().header("Content-Type","application/json")
                        .and().header("Accept","application/json")
                        .and().header("Authorization","Bearer "+token)
                        .when().delete("/orders/"+orderId);
    }

}
