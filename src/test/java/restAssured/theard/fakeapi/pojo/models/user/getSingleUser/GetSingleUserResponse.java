package restAssured.theard.fakeapi.pojo.models.user.getSingleUser;
import restAssured.theard.fakeapi.pojo.models.user.Address;
import restAssured.theard.fakeapi.pojo.models.user.Name;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetSingleUserResponse {

	@JsonProperty("password")
	private String password;

	@JsonProperty("address")
	private Address address;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("name")
	private Name name;

	@JsonProperty("id")
	private int id;

	@JsonProperty("email")
	private String email;

	@JsonProperty("username")
	private String username;

	@JsonProperty("__v")
	private Integer v;
}