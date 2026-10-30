package config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.time.Duration;

@ConfigurationProperties(prefix = "app")
public record AppProperties(
        UiProperties ui,
        ApiProperties api
) {
    public record UiProperties(
            String baseUrl,
            String browser,
            boolean headless,
            Duration waitTimeout,
            String remoteUrl
    ) {}

    public record ApiProperties(
            String baseUrl,
            Duration timeout
    ) {}
}