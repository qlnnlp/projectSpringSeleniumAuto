package models.ui;

import net.datafaker.Faker;

public record UserDataForm(
        String firstName,
        String lastName,
        String address,
        String zipCode,
        String city,
        String country,
        String email,
        String phone,
        String jobPosition,
        String company
) {
    public static UserDataForm generateRandom() {
        Faker faker = new Faker();
        return new UserDataForm(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.address().fullAddress(),
                faker.address().zipCode(),
                faker.address().city(),
                faker.country().name(),
                faker.internet().emailAddress(),
                faker.phoneNumber().phoneNumber(),
                faker.job().position(),
                faker.company().name()
        );
    }
}