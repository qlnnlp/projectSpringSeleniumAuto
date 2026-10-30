package api.services;

import api.clients.AuthApiClient;
import api.clients.UserApiClient;
import io.qameta.allure.Step;
import models.api.AuthRequest;
import models.api.CreateUserResponse;
import models.api.UserRequest;
import models.api.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserApiClient userApiClient;
    private final AuthApiClient authApiClient;

    public UserService(UserApiClient userApiClient, AuthApiClient authApiClient) {
        this.userApiClient = userApiClient;
        this.authApiClient = authApiClient;
    }

    @Step("Бизнес-шаг: Создать тестового пользователя и получить ID")
    public String createTestUser(String name, String job) {
        CreateUserResponse response = userApiClient.createUser(UserRequest.of(name, job));
        return response.id();
    }

    @Step("Бизнес-шаг: Получить данные пользователя по ID: {userId}")
    public UserResponse.Data getUserData(int userId) {
        return userApiClient.getUser(userId).data();
    }

    @Step("Бизнес-шаг: Получить валидный токен авторизации")
    public String obtainAuthToken(String email, String password) {
        return authApiClient.login(AuthRequest.of(email, password)).token();
    }
}