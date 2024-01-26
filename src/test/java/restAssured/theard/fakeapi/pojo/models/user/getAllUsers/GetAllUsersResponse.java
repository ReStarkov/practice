package restAssured.theard.fakeapi.pojo.models.user.getAllUsers;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class GetAllUsersResponse {

	@JsonProperty("Response")
	private List<ResponseItem> response;
}