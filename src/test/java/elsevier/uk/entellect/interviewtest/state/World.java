package elsevier.uk.entellect.interviewtest.state;

import elsevier.uk.entellect.platform.models.GroupModel;
import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@Accessors(fluent = true)
public class World {

    /**
     * TODO: Update clients to use the generalised #get and #set object store
     * instead of group and response
    * */
    private GroupModel group;
    private ValidatableResponse response;
    private Map<String, Object> map = new HashMap<>();

    public <T> Optional<T> get(String key, Class<T> clazz) {
        if (!map.containsKey(key)) {
            return Optional.empty();
        } else {
            return Optional.of(clazz.cast(map.get(key)));
        }
    }

    public World set(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
