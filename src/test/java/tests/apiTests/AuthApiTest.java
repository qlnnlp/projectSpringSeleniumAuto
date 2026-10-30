package tests.apiTests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.api.AuthRequest;
import models.api.AuthResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.baseTests.BaseApiTest;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("REST API Regression")
@Feature("Аутентификация и авторизация")
@Tag("api")
@Tag("regression")
public class AuthApiTest extends BaseApiTest {

    private static final String VALID_USER_EMAIL = "eve.holt@reqres.in";
    private static final String VALID_PASSWORD = "cityslicka";

    @Test
    @Story("Авторизация (Login)")
    @DisplayName("POST /api/login — Успешный вход зарегистрированного пользователя")
    void shouldLoginSuccessfully() {
        AuthRequest request = AuthRequest.of(VALID_USER_EMAIL, VALID_PASSWORD);

        AuthResponse response = authApiClient.login(request);

        assertThat(response.token()).isNotBlank();
    }

    @Test
    @Story("Авторизация (Login)")
    @DisplayName("POST /api/login — Ошибка 400 при отсутствии пароля")
    void shouldFailLoginWhenPasswordIsMissing() {
        AuthRequest request = AuthRequest.withoutPassword(VALID_USER_EMAIL);

        Response response = authApiClient.postLogin(request);

        assertThat(response.statusCode()).isEqualTo(400);
        AuthResponse errorBody = response.as(AuthResponse.class);
        assertThat(errorBody.error()).isEqualTo("Missing password");
    }

    @Test
    @Story("Авторизация (Login)")
    @DisplayName("POST /api/login — Ошибка 400 для незарегистрированного пользователя")
    void shouldFailLoginForUnregisteredUser() {
        AuthRequest request = AuthRequest.of("notfound@example.com", "anypassword");

        Response response = authApiClient.postLogin(request);

        assertThat(response.statusCode()).isEqualTo(400);
        AuthResponse errorBody = response.as(AuthResponse.class);
        assertThat(errorBody.error()).isEqualTo("user not found");
    }

    @Test
    @Story("Регистрация (Register)")
    @DisplayName("POST /api/register — Успешная регистрация пользователя")
    void shouldRegisterUserSuccessfully() {
        AuthRequest request = AuthRequest.of(VALID_USER_EMAIL, "pistol");

        AuthResponse response = authApiClient.register(request);

        assertThat(response.id()).isNotNull().isPositive();
        assertThat(response.token()).isNotBlank();
    }

    @Test
    @Story("Регистрация (Register)")
    @DisplayName("POST /api/register — Ошибка 400 при попытке регистрации без пароля")
    void shouldFailRegistrationWhenPasswordIsMissing() {
        AuthRequest request = AuthRequest.withoutPassword("sydney@fife");

        Response response = authApiClient.postRegister(request);

        assertThat(response.statusCode()).isEqualTo(400);
        AuthResponse errorBody = response.as(AuthResponse.class);
        assertThat(errorBody.error()).isEqualTo("Missing password");
    }
}