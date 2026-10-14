package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.ApiResources;
import resources.TestData;
import resources.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StepDefinition {

    RequestSpecification request;
    Response response;
    static List<String> keys = new ArrayList<String>();

    @Given("{string} payload")
    public void payload(String payloadForApi) throws IOException {
        if (payloadForApi.equalsIgnoreCase("booking"))
            request = given().spec(Utils.requestSpecification()).body(TestData.createUpdateBookingPayload("Arrgtest", "Tiger","Breakfast"))
                    .header("Accept", "application/json");
        else if (payloadForApi.equalsIgnoreCase("token"))
            request = given().spec(Utils.requestSpecification()).body(TestData.createTokenPayload("admin","password123"));
        else if (payloadForApi.equalsIgnoreCase("delete"))
            request = given().spec(Utils.requestSpecification()).pathParam("bookingId",keys.get(0))
                    .header("Cookie","token="+keys.get(1));
    }
    @When("user calls {string} api request")
    public void user_calls_api_request(String apiRequest) {

        ApiResources apiResources =ApiResources.valueOf(apiRequest);

        if (apiRequest.equalsIgnoreCase("createBooking"))
            response = request.when().post(apiResources.getResource());
        else if (apiRequest.equalsIgnoreCase("createToken"))
            response = request.when().post(apiResources.getResource());
        else if (apiRequest.equalsIgnoreCase("deleteBooking"))
            response = request.when().delete(apiResources.getResource());
        else if (apiRequest.equalsIgnoreCase("updateBooking"))
            response = request.when().put(apiResources.getResource());
        else if (apiRequest.equalsIgnoreCase("partialUpdateBooking"))
            response = request.when().patch(apiResources.getResource());
    }
    @Then("verify api status code {int}")
    public void verify_api_status_code(Integer expectedStatusCode) {
        int actualStatus = response.statusCode();
        assertEquals(expectedStatusCode.intValue(),actualStatus);
    }
    @Then("verify {string} is created")
    public void verify_is_created(String key) {
        String actualKey = Utils.getJsonData(response.asString(), key);
        assertNotNull(actualKey);
        keys.add(actualKey);
    }
    @Given("{string} payload with {string} {string} and {string}")
    public void payload_with_and(String payloadForApi, String fName, String lName, String additionalNeeds) throws IOException {

        if (payloadForApi.equalsIgnoreCase("update"))
            request = given().spec(Utils.requestSpecification()).body(TestData.createUpdateBookingPayload(fName,lName,additionalNeeds))
                    .header("Cookie","token="+keys.get(1))
                    .header("Accept", "application/json")
                    .pathParam("bookingId",keys.get(0));
        else if (payloadForApi.equalsIgnoreCase("partialUpdate"))
            request = given().spec(Utils.requestSpecification()).body("{\n" +
                    "     \"firstname\" : \""+fName+"\",\n" +
                    "    \"lastname\" : \""+lName+"\",\n" +
                    "    \"additionalneeds\": \""+additionalNeeds+"\"\n" +
                    "}")
                    .header("Accept","application/json")
                    .header("Cookie","token="+keys.get(1))
                    .pathParam("bookingId",keys.get(0));

    }
    @Then("verify {string} {string} and {string} is created using getBooking api")
    public void verify_and_is_created_using_getBooking_api(String fName, String lName, String additionalNeeds) throws IOException {

        response = given().spec(Utils.requestSpecification()).pathParam("bookingId",keys.get(0))
                .when().get("/booking/{bookingId}");

        String actualFirstName = Utils.getJsonData(response.asString(),"firstname");
        assertEquals(fName,actualFirstName);
        String actualLastName = Utils.getJsonData(response.asString(),"lastname");
        assertEquals(lName,actualLastName);
        String actualAdditionalNeeds = Utils.getJsonData(response.asString(),"additionalneeds");
        assertEquals(additionalNeeds,actualAdditionalNeeds);
        

    }


}
