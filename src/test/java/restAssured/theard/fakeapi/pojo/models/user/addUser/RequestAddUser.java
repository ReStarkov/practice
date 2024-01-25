package restAssured.theard.fakeapi.pojo.models.user.addUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import restAssured.theard.fakeapi.pojo.models.user.Address;
import restAssured.theard.fakeapi.pojo.models.user.Name;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestAddUser {

	@JsonProperty("password")
	private String password;

	@JsonProperty("address")
	private Address address;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("name")
	private Name name;

	@JsonProperty("email")
	private String email;

	@JsonProperty("username")
	private String username;
}