package tests.baseTests;

import config.AppProperties;
import config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestConfig.class)
public abstract class BaseTest {

    @Autowired
    protected AppProperties appProperties;
}