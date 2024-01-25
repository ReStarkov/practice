package restAssured.theard.fakeapi.pojo.models.user.addUser;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class ResponseAddUser {
	@JsonProperty("id")
	private Integer id;
}