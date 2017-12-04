package com.netcracker.developerforum.beans;

import org.springframework.data.mongodb.core.mapping.Document;

import static com.netcracker.developerforum.constants.ForumConstants.ACTIVE;

/**
 * Created by yogs0616 on 11/28/2017.
 */
@Document(collection = "users")
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String userState = ACTIVE;
    private String password;

    public User(){

    }

    public User(String firstName, String lastName, String email, String mobileNumber, String userState, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.userState = userState;
        this.password = password;
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

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "First Name" +firstName+'\''+
                "Last Name" +lastName+'\''+
                "Email" +email+'\''+
                "Mobile Number" +mobileNumber+'\''+
                "User Status" +userState+'\''+
                "}";
    }
}
