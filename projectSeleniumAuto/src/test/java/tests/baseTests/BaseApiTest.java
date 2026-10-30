package tests.baseTests;

import api.clients.AuthApiClient;
import api.clients.UserApiClient;
import api.services.UserService;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Tag("api")
public abstract class BaseApiTest extends BaseTest {

    @Autowired
    protected UserApiClient userApiClient;

    @Autowired
    protected AuthApiClient authApiClient;

    @Autowired
    protected UserService userService;

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    protected final Faker faker = new Faker();

    @BeforeEach
    void throttleRateLimit() {
        // Выдерживаем паузу только для внешнего стенда ReqRes
        if (!"mock".equalsIgnoreCase(activeProfile)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        }
    }
}