package com.yogendra.developerforum.beans;


public class Login {

    private String userName;
    private String userState;
    private String password;

    public Login() {

    }

    public Login(String userName, String userState, String password) {
        this.userName = userName;
        this.userState = userState;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        return "Login{" +
                "userName='" + userName + '\'' +
                ", userState='" + userState + '\'' +
                '}';
    }
}
