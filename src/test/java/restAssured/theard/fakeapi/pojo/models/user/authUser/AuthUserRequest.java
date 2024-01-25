package restAssured.theard.fakeapi.pojo.models.user.authUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserRequest {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
