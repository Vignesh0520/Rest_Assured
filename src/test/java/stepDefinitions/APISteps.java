package stepDefinitions;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APISteps {
    Response response;

    @Given("the base URI is {string}")
    public void the_base_uri_is(String baseUri) {
        RestAssured.baseURI = baseUri;
    }

    @When("I send GET request to {string}")
    public void i_send_get_request_to(String endpoint) {
        response = RestAssured
                    .given()
                    .header("x-api-key", "reqres-free-v1")
                    .log().all()
                    .when()
                    .get(endpoint)
                    .then()
                    .log().all()
                    .extract().response();
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        assertEquals(expectedStatusCode.intValue(), response.statusCode());
    }

    @Then("the response should contain {string}")
    public void the_response_should_contain(String expectedField) {
        String responseString = response.getBody().asString();
        assertTrue("Field '" + expectedField + "' not found in response!",
                   responseString.contains(expectedField));
    }}
