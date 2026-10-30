package models.api;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AuthRequest(
        String email,
        String password
) {
    public static AuthRequest of(String email, String password) {
        return new AuthRequest(email, password);
    }

    public static AuthRequest withoutPassword(String email) {
        return new AuthRequest(email, null);
    }
}