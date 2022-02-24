package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PizzaAppSteps {

    @Given("user navigates to pizza application")
    public void user_navigates_to_pizza_application() {

    }

    @When("user creates pizza order with data")
    public void user_creates_pizza_order_with_data(DataTable dataTable) {

    }

    @Then("user validates that order is created with success message {string}")
    public void user_validates_that_order_is_created_with_success_message(String expectedMessage) {

    }
}
