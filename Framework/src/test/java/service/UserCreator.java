package service;

import model.User;

import static util.Resolver.resolveTemplate;

public class UserCreator {
    public static final String USER_FIRST_NAME = "test.data.user.firstname";
    public static final String USER_LAST_NAME = "test.data.user.lastname";
    public static final String USER_ADDRESS = "test.data.user.address";
    public static final String USER_APARTMENT = "test.data.user.apartment";
    public static final String USER_CITY = "test.data.user.city";
    public static final String USER_STATE = "test.data.user.state";
    public static final String USER_ZIP_CODE = "test.data.user.zipcode";
    public static final String USER_EMAIL = "test.data.user.email";
    public static final String USER_PHONE_NUMBER = "test.data.user.phonenumber";

    public static User withCredentialsFromProperty(){

        String firstName = TestDataReader.getTestData(USER_FIRST_NAME);
        String lastName = TestDataReader.getTestData(USER_LAST_NAME);
        String address = TestDataReader.getTestData(USER_ADDRESS);
        String apartment = TestDataReader.getTestData(USER_APARTMENT);
        String city = TestDataReader.getTestData(USER_CITY);
        String state = TestDataReader.getTestData(USER_STATE);
        String zipCode = TestDataReader.getTestData(USER_ZIP_CODE);
        String email = TestDataReader.getTestData(USER_EMAIL);
        String phoneNumber = TestDataReader.getTestData(USER_PHONE_NUMBER);

        return User.of(firstName, lastName, address, apartment, city, state, zipCode, email, phoneNumber);
    }
}
