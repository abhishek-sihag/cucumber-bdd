package stepdefinitions;

import apiBase.ApiSpecs;
import com.google.gson.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiStepDef {

    static final Logger LOG = LogManager.getLogger(ApiStepDef.class);
    Response response;
    RequestSpecification request;

    @Given("Have GET request specs for execution")
    public void haveGetApiSpecs() {
        request = given().spec(ApiSpecs.getRequestSpec());
    }

    @When("User hit GET request")
    public void userHitGETRequest() {
        response = request.when().get();
    }

    @Then("Validate GET API response")
    public void validateGETAPIResponse() {
        response.then().spec(ApiSpecs.getResponseSpec());
    }

    @Given("Have POST request for execution")
    public void havePOSTRequestAndResponseSpecsForExecution() {
        request = given().spec(ApiSpecs.postRequestSpec());
    }

    @When("User hit POST request with body")
    public void userHitPOSTRequestWithBody() {
        response = request.when().post();
    }

    @Then("Validate POST API response")
    public void validatePOSTAPIResponse() {
        response.then().spec(ApiSpecs.postResponseSpec());
    }

    @Given("User has request specification")
    public void userHasRequestSpecification() {
        request = given().spec(ApiSpecs.postRequestProducts());
    }

    @When("User sends POST request with id {string} title {string} price {string} description {string} category {string} image {string}")
    public void userSendsPOSTRequestWithIdTitlePriceDescriptionCategoryImage(
            String id, String title, String price, String desc, String cat, String img) {

        JsonObject json = new JsonObject();
        json.addProperty("id", Integer.parseInt(id));
        json.addProperty("title", title);
        json.addProperty("price", Double.parseDouble(price));
        json.addProperty("description", desc);
        json.addProperty("category", cat);
        json.addProperty("image", img);

        response = request
                .body(json.toString())
                .when()
                .post();
    }

    @Then("Validate API response")
    public void validateAPIResponse() {
        response.then().spec(ApiSpecs.postResponseProd());
    }

    @Given("Have typicode GET request specs for execution")
    public void haveTypicodeGETRequestSpecsForExecution() {
        request = given().spec(ApiSpecs.get2RequestSpec());
    }

    @When("User hit typicode GET request")
    public void userHitTypicodeGETRequest() {
        response = request.get();
    }

    @Then("Validate typicode GET API response")
    public void validateTypicodeGETAPIResponse() {
        response.then().spec(ApiSpecs.get2ResponseSpec());
        // Get full list
        List<Map<String, Object>> posts = response.jsonPath().getList("");

        LOG.info("Total posts: {}", posts.size());
        int firstUserId = response.jsonPath().getInt("[0].userId");
        String firstTitle = response.jsonPath().getString("[0].title");

        LOG.info("First userId: {}", firstUserId);
        LOG.info("First title: {}", firstTitle);
    }
}