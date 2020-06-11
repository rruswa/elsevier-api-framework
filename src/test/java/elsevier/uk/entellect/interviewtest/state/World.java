package elsevier.uk.entellect.interviewtest.state;

import elsevier.uk.entellect.platform.models.GroupModel;
import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class World {

    private GroupModel group;

    private ValidatableResponse response;

}
