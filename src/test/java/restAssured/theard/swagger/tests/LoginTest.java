package restAssured.theard.swagger.tests;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restAssured.theard.swagger.models.dataModels.RegisterData;
import restAssured.theard.swagger.models.request.LoginRequest;
import restAssured.theard.swagger.models.response.LoginResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Авторизация пользователя, проверка содержания токена")
    public void loginTest(){
        RegisterData user = createUser();
        LoginRequest request = LoginRequest.builder()
                .username(user.getLogin())
                .password(user.getPass())
                .build();

        LoginResponse response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract().as(LoginResponse.class);
        assertThat(response.getToken(), notNullValue());

        DecodedJWT decodedJWT = JWT.decode(response.getToken());
        String username = decodedJWT.getClaim("sub").toString();
        assertThat(username, containsString(request.getUsername()));
    }
}
