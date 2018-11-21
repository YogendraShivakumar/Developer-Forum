package com.yogendra.developerforum.beans;

import org.springframework.data.mongodb.core.mapping.Document;

import static com.yogendra.developerforum.constants.ForumConstants.ACTIVE;

/**
 * Created by yogs0616 on 11/28/2017.
 */
@Document(collection = "users")
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private Login registration;

    public User() {

    }

    public User(String firstName, String lastName, String email, String mobileNumber, String userState, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public Login getRegistration() {
        return registration;
    }

    public void setRegistration(Login registration) {
        this.registration = registration;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "First Name" + firstName + '\'' +
                "Last Name" + lastName + '\'' +
                "Email" + email + '\'' +
                "Mobile Number" + mobileNumber + '\'' +
                "}";
    }
}
