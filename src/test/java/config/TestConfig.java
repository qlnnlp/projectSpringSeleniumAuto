package config;

import net.datafaker.Faker;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Locale;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
@ComponentScan(basePackages = {"config", "core", "api", "ui", "tests"})
public class TestConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Faker faker() {
        return new Faker(Locale.ENGLISH);
    }
}