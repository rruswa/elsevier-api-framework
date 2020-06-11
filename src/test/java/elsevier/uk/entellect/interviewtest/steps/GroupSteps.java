package elsevier.uk.entellect.interviewtest.steps;

import elsevier.uk.entellect.interviewtest.state.World;
import elsevier.uk.entellect.platform.services.common.BaseAPI;
import elsevier.uk.entellect.platform.services.group.GroupAPI;
import io.cucumber.java8.En;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class GroupSteps implements En {

    public GroupSteps(World world) {
        Given("^there are group resources$", () -> {
            // Retrieves all available groups and stores the first one as part of world.
            world.group(GroupAPI.groups().groups().get(0));
        });
        When("a {word} request is made to {string}", (String method, String endpoint) -> {
            ValidatableResponse response = given()
                        .spec(BaseAPI.getBaseRequestSpecBuilder().build())
                    .when()
                        .request(method, endpoint, world.group().guid())
                    .then()
                        .spec(BaseAPI.getBaseResponseSpecBuilder().build());

            world.response(response);
        });
        Then("^the response status code is (\\d+)$", (Integer statusCode) -> {
            world.response().statusCode(statusCode);
        });
    }

}
