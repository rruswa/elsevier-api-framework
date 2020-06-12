package elsevier.uk.entellect.interviewtest.steps;

import elsevier.uk.entellect.interviewtest.state.World;
import elsevier.uk.entellect.platform.models.BitlinksModel;
import elsevier.uk.entellect.platform.models.GroupModel;
import elsevier.uk.entellect.platform.services.group.GroupAPI;
import io.cucumber.java8.En;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class CommonSteps implements En {

    public CommonSteps(World world) {
         Given("^there are bitlinks available$", () -> {
             GroupModel groupModel = GroupAPI.groups().groups().get(0);
             world.group(groupModel);
             BitlinksModel bitlinks = GroupAPI.bitlink(groupModel.guid());
             world.set("bitlinks", bitlinks);
        });
        Then("^the opened bitlink is returned$", () -> {
            BitlinksModel bitlinksModel = world.response().statusCode(HttpStatus.SC_OK).extract().as(BitlinksModel.class);
            Assertions.assertThat(bitlinksModel.links()).containsAll(world.get("bitlinks", BitlinksModel.class).orElseThrow().links());
        });
        And("^a bitlink has been opened$", () -> {
            BitlinksModel bitlinks = world.get("bitlinks", BitlinksModel.class).orElseThrow(IllegalStateException::new);
            HtmlUnitDriver driver = new HtmlUnitDriver(false);
            bitlinks.links().forEach(bitlink -> {
                System.out.println("Opening bitlink: " + bitlink.link());
                driver.get(bitlink.link());
            });
            driver.close();
        });
    }

}
