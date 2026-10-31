package tests.dbTests;

import db.models.UserDbEntity;
import db.repository.UserDbRepository;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tests.baseTests.BaseTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Работа с базой данных")
@Feature("User Repository CRUD операции")
@Tag("db")
public class UserDbRepositoryTest extends BaseTest {

    @Autowired
    private UserDbRepository userDbRepository;

    private final Long testUserId = 9999L;

    @AfterEach
    void tearDown() {
        userDbRepository.deleteById(testUserId);
    }

    @Test
    @DisplayName("DB: Сохранение пользователя, чтение и корректность маппинга полей")
    void shouldSaveAndRetrieveUserFromDatabase() {
        UserDbEntity expectedUser = UserDbEntity.of(
                testUserId,
                "qa_architect@example.com",
                "Senior",
                "Engineer"
        );

        userDbRepository.save(expectedUser);

        Optional<UserDbEntity> actualUserOpt = userDbRepository.findById(testUserId);

        assertThat(actualUserOpt)
                .as("Пользователь должен быть найден в базе данных")
                .isPresent();

        UserDbEntity actualUser = actualUserOpt.get();
        assertThat(actualUser.email()).isEqualTo(expectedUser.email());
        assertThat(actualUser.firstName()).isEqualTo(expectedUser.firstName());
        assertThat(actualUser.lastName()).isEqualTo(expectedUser.lastName());
        assertThat(actualUser.createdAt()).isNotNull();
    }
}