package api.clients;

import api.specs.RequestSpecFactory;
import config.AppProperties;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.api.AuthRequest;
import models.api.AuthResponse;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Component
public class AuthApiClient {

    private static final String LOGIN_ENDPOINT = "/login";
    private static final String REGISTER_ENDPOINT = "/register";

    private final RequestSpecification requestSpec;

    public AuthApiClient(AppProperties properties) {
        this.requestSpec = RequestSpecFactory.defaultRequestSpec(properties.api());
    }

    @Step("Отправить POST запрос на авторизацию")
    public Response postLogin(AuthRequest request) {
        return given()
                .spec(requestSpec)
                .body(request)
                .when()
                .post(LOGIN_ENDPOINT);
    }

    @Step("Выполнить авторизацию пользователя: {request.email}")
    public AuthResponse login(AuthRequest request) {
        return postLogin(request)
                .then()
                .spec(RequestSpecFactory.successResponse(200))
                .extract()
                .as(AuthResponse.class);
    }

    @Step("Отправить POST запрос на регистрацию")
    public Response postRegister(AuthRequest request) {
        return given()
                .spec(requestSpec)
                .body(request)
                .when()
                .post(REGISTER_ENDPOINT);
    }

    @Step("Зарегистрировать пользователя: {request.email}")
    public AuthResponse register(AuthRequest request) {
        return postRegister(request)
                .then()
                .spec(RequestSpecFactory.successResponse(200))
                .extract()
                .as(AuthResponse.class);
    }
}