package models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateUserResponse(
        String id,
        String name,
        String job,
        String createdAt
) {}