package restAssured.theard.fakeapi.pojo.models.user.authUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthUserResponse {

    @JsonProperty("token")
    private String token;
}
