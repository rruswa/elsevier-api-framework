package elsevier.uk.entellect.interviewtest.steps;

import elsevier.uk.entellect.interviewtest.state.World;
import elsevier.uk.entellect.platform.models.ErrorModel;
import elsevier.uk.entellect.platform.models.GroupModel;
import elsevier.uk.entellect.platform.services.common.BaseAPI;
import elsevier.uk.entellect.platform.services.group.GroupAPI;
import elsevier.uk.entellect.platform.utils.PropUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assertions;

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
        Given("^the user is trying to access a group they aren't a part of$", () -> {
            GroupModel fakeGroup = new GroupModel().guid(PropUtils.get("user.dir") + "fakeGUID");
            world.group(fakeGroup);
        });
        And("^the error message contains the following properties$", (DataTable table) -> {
            // Note that this can be further generalised by extracting the property values based on the keySet
            // Values from table.asMaps instead of having them be static values as done here.
            Assertions.assertThat(world.response().extract().as(ErrorModel.class))
                    .extracting("message", "resource", "description")
                    .containsOnly(table.asMaps().get(0).values().toArray(new String[0]));
        });
    }

}
