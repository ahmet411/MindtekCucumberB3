package utilities;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static Response apiGet(String endpoint, Map<String, Object> headers){
        Response response = given().baseUri(ConfigReader.getProperty("BankAPIBaseURL"))
                .and().headers(headers)
                .and().auth().oauth2(getToken())
                .when().get(endpoint);
        response.then().log().all();
        return response;
    }

    public static Response apiPost(String endpoint, Map<String, Object> headers, Object body){
        Response response = given().baseUri(ConfigReader.getProperty("BankAPIBaseURL"))
                .and().headers(headers)
                .and().auth().oauth2(getToken())
                .and().body(body)
                .when().post(endpoint);
        response.then().log().all();
        return response;
    }

    public static Response apiPut(String endpoint, Map<String, Object> headers, Object body){
        Response response = given().baseUri(ConfigReader.getProperty("BankAPIBaseURL"))
                .and().headers(headers)
                .and().auth().oauth2(getToken())
                .and().body(body)
                .when().put(endpoint);
        response.then().log().all();
        return response;
    }

    public static Response apiDelete(String endpoint, Map<String, Object> headers){
        Response response = given().baseUri(ConfigReader.getProperty("BankAPIBaseURL"))
                .and().headers(headers)
                .and().auth().oauth2(getToken())
                .when().delete(endpoint);
        response.then().log().all();
        return response;
    }

    public static String getToken(){
        Response response = given().baseUri(ConfigReader.getProperty("BankAPIBaseURL"))
                .and().header("Content-Type","application/json")
                .and().header("Accept","application/json")
                .and().body("{\n" +
                        "    \"password\": \"MindtekStudent\",\n" +
                        "    \"username\": \"Mindtek\"\n" +
                        "}")
                .when().post("authenticate");
        response.then().log().all();
        return response.body().jsonPath().getString("jwt");
    }

}
