package elsevier.uk.entellect.interviewtest.steps;

import com.github.javafaker.Faker;
import elsevier.uk.entellect.interviewtest.state.World;
import elsevier.uk.entellect.platform.models.BitlinkModel;
import elsevier.uk.entellect.platform.services.common.BaseAPI;
import io.cucumber.java8.En;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assertions;

import static io.restassured.RestAssured.given;

public class BitlinkSteps implements En {

    public BitlinkSteps(World world) {
        Given("^a valid( dynamic)? bitlink payload has been initialised$", (String isDynamic) -> {
            String url = "https://www.%s.com/";
            if (isDynamic.contains("dynamic")) {
                url = String.format(url, new Faker().company().name().replaceAll("[^\\w]+", "").toLowerCase());
            } else {
                url = String.format(url, "example");
            }
            BitlinkModel bitlink = new BitlinkModel().longUrl(url);
            world.set("payload", bitlink);
        });
        When("a POST request is made to {string} with payload", (String endpoint) -> {
            ValidatableResponse response = given()
                        .spec(BaseAPI.getBaseRequestSpecBuilder().build())
                        .contentType(ContentType.JSON)
                    .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                        .body(world.get("payload", BitlinkModel.class).orElseThrow())
                    .when()
                        .post(endpoint)
                    .then()
                        .spec(BaseAPI.getBaseResponseSpecBuilder().build());

            world.response(response);
        });
        And("^the bitlink has been created$", () -> {
            BitlinkModel responseBody = world.response().extract().body().as(BitlinkModel.class);
            Assertions.assertThat(responseBody).isEqualTo(world.get("payload", BitlinkModel.class).orElseThrow());
        });
    }

}
