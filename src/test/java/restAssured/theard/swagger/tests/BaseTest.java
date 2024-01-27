package restAssured.theard.swagger.tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import restAssured.theard.listener.CustomTpl;
import restAssured.theard.swagger.models.dataModels.RegisterData;
import restAssured.theard.swagger.models.request.RegistrationRequest;
import restAssured.theard.swagger.models.response.RegistrationResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class BaseTest {
    @BeforeAll
    private static void setUp() {
        RestAssured.baseURI = "http://85.192.34.140:8080";
        RestAssured.basePath = "/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                CustomTpl.customFilter().withCustomTemplates());
    }
    Faker faker = new Faker();

    protected RegisterData createUser(){
        RegistrationRequest request = RegistrationRequest
                .builder()
                .pass("qwerty")
                .login("Bob" + faker.number().numberBetween(1,111111111)).build();

        RegistrationResponse response =  given().contentType(ContentType.JSON)
                .body(request)
                .post("/signup")
                .then()
                .statusCode(201)
                .extract().as(RegistrationResponse.class);

        return response.getRegisterData();
    }
}
