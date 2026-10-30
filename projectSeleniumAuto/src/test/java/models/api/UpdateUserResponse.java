package models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdateUserResponse(
        String name,
        String job,
        String updatedAt
) {}