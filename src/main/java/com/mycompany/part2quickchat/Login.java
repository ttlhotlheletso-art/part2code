/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ttlho
 */
 package com.mycompany.part2quickchat;

public class Login {

    private String storedUsername;
    private String storedPassword;
    private String storedPhone;
    private String firstName;
    private String lastName;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[^a-zA-Z0-9].*");
    }

    public boolean checkCellPhoneNumber(String number) {
        return number.matches("^\\+27\\d{9}$");
    }

    public String registerUser(String username, String password, String number,
                               String firstNameInput, String lastNameInput) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted.";
        }

        if (!checkCellPhoneNumber(number)) {
            return "Cell phone number incorrectly formatted.";
        }

        storedUsername = username;
        storedPassword = password;
        storedPhone = number;
        firstName = firstNameInput;
        lastName = lastNameInput;

        return "User successfully registered.";
    }

    public boolean loginUser(String username, String password) {
        return username.equals(storedUsername) &&
                password.equals(storedPassword);
    }

    public String returnLoginStatus(boolean status) {
        if (status) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect.";
        }
    }
}
