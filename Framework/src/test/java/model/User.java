package model;

import lombok.Getter;
import lombok.Setter;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String address;
    private String apartment;
    private String city;
    private String state;
    private String zipCode;
    private String email;
    private String phoneNumber;

    private User(String firstName, String lastName, String address, String apartment, String city,
                 String state, String zipCode, String email, String phoneNumber) {

        checkNotNull(firstName);
        checkNotNull(lastName);
        checkNotNull(address);
        checkNotNull(apartment);
        checkNotNull(city);
        checkNotNull(state);
        checkNotNull(zipCode);
        checkNotNull(email);
        checkNotNull(phoneNumber);

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.apartment = apartment;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static User of(String firstName, String lastName, String address, String apartment, String city,
                  String state, String zipCode, String email, String phoneNumber){

        return new User(firstName, lastName, address, apartment, city, state, zipCode, email, phoneNumber);
    }
}
