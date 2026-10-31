package db.models;

import java.time.LocalDateTime;

public record UserDbEntity(
        Long id,
        String email,
        String firstName,
        String lastName,
        LocalDateTime createdAt
) {
    public static UserDbEntity of(Long id, String email, String firstName, String lastName) {
        return new UserDbEntity(id, email, firstName, lastName, null);
    }
}