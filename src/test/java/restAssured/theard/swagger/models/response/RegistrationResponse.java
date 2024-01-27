package restAssured.theard.swagger.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import restAssured.theard.swagger.models.dataModels.RegisterData;
import restAssured.theard.swagger.models.dataModels.Info;

@Data
public class RegistrationResponse {
    private Info info;
    @JsonProperty("register_data")
    private RegisterData registerData;
}
