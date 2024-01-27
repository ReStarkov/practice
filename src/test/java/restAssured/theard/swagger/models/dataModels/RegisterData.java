package restAssured.theard.swagger.models.dataModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterData {
    @JsonProperty("games")
    private ArrayList<Games> games;
    @JsonProperty("id")
    private int id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("pass")
    private String pass;
}
