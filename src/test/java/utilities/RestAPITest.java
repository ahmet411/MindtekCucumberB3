package utilities;

import io.restassured.response.Response;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class RestAPITest {

    public static void main(String[] args) {

        /*
        Get list of books
        1. URL - BaseURL
        2. Headers - application/json
         */

        Response response =
                given().baseUri("https://simple-books-api.glitch.me")
                .and().header("Accept","application/json")
                .when().get("/books");


        System.out.println(response.statusCode());
        System.out.println(response.body().asString());

        /*
        Create an Order
        1. URL
        2. Headers
        3. Body
         */

        Random random=new Random();
        int randomNum=random.nextInt();

        Response authResponse =
                given().baseUri("https://simple-books-api.glitch.me")
                        .and().header("Content-Type","application/json")
                        .and().header("Accept","application/json")
                        .and().body("{\n" +
                                "   \"clientName\": \"Eldar\",\n" +
                                "   \"clientEmail\": \"arslan"+randomNum+"@example.com\"\n" +
                                "}")
                        .when().post("/api-clients/");

        System.out.println(authResponse.getStatusCode());
        System.out.println(authResponse.body().asString());

        String token=authResponse.body().jsonPath().getString("accessToken");

        Response postResponse =
                given().baseUri("https://simple-books-api.glitch.me")
                        .and().header("Content-Type","application/json")
                        .and().header("Authorization","Bearer "+token)
                        .and().body("{\n" +
                                "  \"bookId\": 1,\n" +
                                "  \"customerName\": \"Patel Harsh\"\n" +
                                "}")
                        .when().post("/orders");

        System.out.println(postResponse.getStatusCode());

    }

}
