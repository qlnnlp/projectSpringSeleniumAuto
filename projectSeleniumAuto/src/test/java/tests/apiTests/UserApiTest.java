package tests.apiTests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.api.CreateUserResponse;
import models.api.UpdateUserResponse;
import models.api.UserRequest;
import models.api.UserResponse;
import models.api.UsersListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.baseTests.BaseApiTest;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("REST API Regression")
@Feature("Управление пользователями")
@Tag("api")
@Tag("regression")
public class UserApiTest extends BaseApiTest {

    @Test
    @Story("Получение списка пользователей")
    @DisplayName("GET /api/users?page=2 — Получение страницы пользователей с пагинацией")
    void shouldGetUsersListWithPagination() {
        int targetPage = 2;

        UsersListResponse response = userApiClient.getUsersList(targetPage);

        assertThat(response.page()).isEqualTo(targetPage);
        assertThat(response.perPage()).isPositive();
        assertThat(response.total()).isPositive();
        assertThat(response.totalPages()).isPositive();
        assertThat(response.data())
                .isNotEmpty()
                .allSatisfy(user -> {
                    assertThat(user.id()).isNotNull();
                    assertThat(user.email()).contains("@");
                    assertThat(user.firstName()).isNotBlank();
                    assertThat(user.lastName()).isNotBlank();
                    assertThat(user.avatar()).startsWith("http");
                });
    }

    @Test
    @Story("Получение данных пользователя")
    @DisplayName("GET /api/users/{id} — Успешное получение профиля существующего пользователя")
    void shouldGetExistingUserById() {
        int userId = 2;

        UserResponse response = userApiClient.getUser(userId);

        assertThat(response.data().id()).isEqualTo(userId);
        assertThat(response.data().email()).isEqualTo("janet.weaver@reqres.in");
        assertThat(response.data().firstName()).isEqualTo("Janet");
        assertThat(response.data().lastName()).isEqualTo("Weaver");
        assertThat(response.support().url()).isNotBlank();
    }

    @Test
    @Story("Получение данных пользователя")
    @DisplayName("GET /api/users/{id} — Возврат 404 Not Found для несуществующего пользователя")
    void shouldReturn404WhenUserDoesNotExist() {
        int nonExistentId = 9999;

        Response response = userApiClient.getUserResponse(nonExistentId);

        assertThat(response.statusCode()).isEqualTo(404);
    }

    @Test
    @Story("Создание пользователя")
    @DisplayName("POST /api/users — Успешное создание нового пользователя")
    void shouldCreateUserSuccessfully() {
        String name = faker.name().firstName();
        String job = faker.job().title();
        UserRequest request = UserRequest.of(name, job);

        CreateUserResponse response = userApiClient.createUser(request);

        assertThat(response.id()).isNotBlank();
        assertThat(response.name()).isEqualTo(name);
        assertThat(response.job()).isEqualTo(job);
        assertThat(response.createdAt()).isNotBlank();
    }

    @Test
    @Story("Обновление данных пользователя")
    @DisplayName("PUT /api/users/{id} — Полное обновление данных пользователя")
    void shouldUpdateUserWithPut() {
        int userId = 2;
        String updatedName = faker.name().firstName();
        String updatedJob = "Senior QA Automation Engineer";
        UserRequest request = UserRequest.of(updatedName, updatedJob);

        UpdateUserResponse response = userApiClient.updateUser(userId, request);

        assertThat(response.name()).isEqualTo(updatedName);
        assertThat(response.job()).isEqualTo(updatedJob);
        assertThat(response.updatedAt()).isNotBlank();
    }

    @Test
    @Story("Обновление данных пользователя")
    @DisplayName("PATCH /api/users/{id} — Частичное обновление данных пользователя")
    void shouldUpdateUserWithPatch() {
        int userId = 2;
        String updatedJob = "Lead QA Architect";
        UserRequest request = UserRequest.of(null, updatedJob);

        UpdateUserResponse response = userApiClient.patchUser(userId, request);

        assertThat(response.job()).isEqualTo(updatedJob);
        assertThat(response.updatedAt()).isNotBlank();
    }

    @Test
    @Story("Удаление пользователя")
    @DisplayName("DELETE /api/users/{id} — Успешное удаление с возвратом 204 No Content")
    void shouldDeleteUserSuccessfully() {
        int userId = 2;

        Response response = userApiClient.deleteUser(userId);

        assertThat(response.statusCode()).isEqualTo(204);
    }
}