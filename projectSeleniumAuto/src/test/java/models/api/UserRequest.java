package models.api;

public record UserRequest(
        String name,
        String job
) {
    public static UserRequest of(String name, String job) {
        return new UserRequest(name, job);
    }
}
