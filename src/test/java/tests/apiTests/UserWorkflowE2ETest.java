package tests.apiTests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.api.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.baseTests.BaseApiTest;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("REST API Regression")
@Feature("Сквозные бизнес-сценарии")
@Tag("api")
@Tag("e2e")
public class UserWorkflowE2ETest extends BaseApiTest {

    @Test
    @Story("Жизненный цикл пользователя")
    @DisplayName("E2E API: Авторизация -> Создание пользователя -> Проверка профиля")
    void shouldExecuteFullUserWorkflow() {
        // 1. Получение токена авторизации
        String token = userService.obtainAuthToken("eve.holt@reqres.in", "cityslicka");
        assertThat(token).isNotBlank();

        // 2. Создание пользователя через бизнес-сервис
        String candidateName = faker.name().fullName();
        String candidateJob = "Automation QA Lead";
        String createdUserId = userService.createTestUser(candidateName, candidateJob);
        assertThat(createdUserId).isNotBlank();

        // 3. Получение данных существующего пользователя из базы системы
        UserResponse.Data existingUser = userService.getUserData(2);
        assertThat(existingUser.id()).isEqualTo(2);
        assertThat(existingUser.email()).isNotBlank();
    }
}