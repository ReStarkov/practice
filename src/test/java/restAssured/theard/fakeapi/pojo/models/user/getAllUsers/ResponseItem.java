package restAssured.theard.fakeapi.pojo.models.user.getAllUsers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import restAssured.theard.fakeapi.pojo.models.user.Address;
import restAssured.theard.fakeapi.pojo.models.user.Name;

@Data
public class ResponseItem{

	@JsonProperty("password")
	private String password;

	@JsonProperty("address")
	private Address address;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("__v")
	private int v;

	@JsonProperty("name")
	private Name name;

	@JsonProperty("id")
	private int id;

	@JsonProperty("email")
	private String email;

	@JsonProperty("username")
	private String username;
}