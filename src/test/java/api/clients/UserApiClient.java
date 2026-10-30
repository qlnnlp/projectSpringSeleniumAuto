package api.clients;

import api.specs.RequestSpecFactory;
import config.AppProperties;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.api.CreateUserResponse;
import models.api.UpdateUserResponse;
import models.api.UserRequest;
import models.api.UserResponse;
import models.api.UsersListResponse;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Component
public class UserApiClient {

    private static final String USERS_ENDPOINT = "/users";
    private static final String USER_BY_ID_ENDPOINT = "/users/{id}";

    private final RequestSpecification requestSpec;

    public UserApiClient(AppProperties properties) {
        this.requestSpec = RequestSpecFactory.defaultRequestSpec(properties.api());
    }

    @Step("Отправить GET запрос на получение списка пользователей (страница {page})")
    public Response getUsersListResponse(int page) {
        return given()
                .spec(requestSpec)
                .queryParam("page", page)
                .when()
                .get(USERS_ENDPOINT);
    }

    @Step("Получить список пользователей на странице {page}")
    public UsersListResponse getUsersList(int page) {
        return getUsersListResponse(page)
                .then()
                .spec(RequestSpecFactory.successResponse(200))
                .extract()
                .as(UsersListResponse.class);
    }

    @Step("Отправить GET запрос на получение пользователя с ID: {userId}")
    public Response getUserResponse(int userId) {
        return given()
                .spec(requestSpec)
                .when()
                .get(USER_BY_ID_ENDPOINT, userId);
    }

    @Step("Получить данные пользователя с ID: {userId}")
    public UserResponse getUser(int userId) {
        return getUserResponse(userId)
                .then()
                .spec(RequestSpecFactory.successResponse(200))
                .extract()
                .as(UserResponse.class);
    }

    @Step("Отправить POST запрос на создание пользователя")
    public Response createUserResponse(UserRequest userRequest) {
        return given()
                .spec(requestSpec)
                .body(userRequest)
                .when()
                .post(USERS_ENDPOINT);
    }

    @Step("Создать нового пользователя: {userRequest.name}")
    public CreateUserResponse createUser(UserRequest userRequest) {
        return createUserResponse(userRequest)
                .then()
                .spec(RequestSpecFactory.successResponse(201))
                .extract()
                .as(CreateUserResponse.class);
    }

    @Step("Отправить PUT запрос на обновление пользователя с ID: {userId}")
    public Response updateUserResponse(int userId, UserRequest userRequest) {
        return given()
                .spec(requestSpec)
                .body(userRequest)
                .when()
                .put(USER_BY_ID_ENDPOINT, userId);
    }

    @Step("Обновить данные пользователя (PUT) с ID: {userId}")
    public UpdateUserResponse updateUser(int userId, UserRequest userRequest) {
        return updateUserResponse(userId, userRequest)
                .then()
                .spec(RequestSpecFactory.successResponse(200))
                .extract()
                .as(UpdateUserResponse.class);
    }

    @Step("Отправить PATCH запрос на частичное обновление пользователя с ID: {userId}")
    public Response patchUserResponse(int userId, UserRequest userRequest) {
        return given()
                .spec(requestSpec)
                .body(userRequest)
                .when()
                .patch(USER_BY_ID_ENDPOINT, userId);
    }

    @Step("Частично обновить данные пользователя (PATCH) с ID: {userId}")
    public UpdateUserResponse patchUser(int userId, UserRequest userRequest) {
        return patchUserResponse(userId, userRequest)
                .then()
                .spec(RequestSpecFactory.successResponse(200))
                .extract()
                .as(UpdateUserResponse.class);
    }

    @Step("Удалить пользователя с ID: {userId}")
    public Response deleteUser(int userId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete(USER_BY_ID_ENDPOINT, userId);
    }
}