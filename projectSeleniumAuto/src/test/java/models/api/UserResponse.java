package models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserResponse(
        Data data,
        Support support
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Data(
            Integer id,
            String email,
            @JsonProperty("first_name")
            String firstName,
            @JsonProperty("last_name")
            String lastName,
            String avatar
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Support(
            String url,
            String text
    ) {}
}