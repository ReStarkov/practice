package restAssured.theard.swagger.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import restAssured.theard.swagger.models.dataModels.Games;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationRequest {
    @JsonProperty("login")
    private String login;
    @JsonProperty("pass")
    private String pass;
    @JsonProperty("games")
    private List<Games> games;
}
