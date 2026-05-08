import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";
    private static final String REGISTER_USER = "/api/auth/register";
    private static final String LOGIN_USER = "/api/auth/login";
    private static final String DELETE_USER = "/api/auth/user";

    @Step("Создать пользователя через API")
    public Response createUser(TestUser user) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(REGISTER_USER);
    }

    @Step("Авторизоваться через API")
    public Response loginUser(TestUser user) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(Map.of(
                        "email", user.email,
                        "password", user.password
                ))
                .when()
                .post(LOGIN_USER);
    }

    @Step("Удалить пользователя через API")
    public Response deleteUser(String accessToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_USER);
    }
}