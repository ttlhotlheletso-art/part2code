/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.part2quickchat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ttlho
 */
public class LoginTest {
    
    private Login login;
    
    @Before
    public void setUp() {
        login = new Login();
    }
    
    // ===== USERNAME VALIDATION TESTS =====
    
    /**
     * Test checkUserName() - valid username with underscore
     */
    @Test
    public void testCheckUserNameValid() {
        assertTrue("Username with underscore and 5 chars should be valid", login.checkUserName("test_"));
        assertTrue("Username with underscore and less than 5 chars should be valid", login.checkUserName("ab_cd"));
    }
    
    /**
     * Test checkUserName() - invalid username without underscore
     */
    @Test
    public void testCheckUserNameNoUnderscore() {
        assertFalse("Username without underscore should be invalid", login.checkUserName("abcde"));
    }
    
    /**
     * Test checkUserName() - invalid username exceeds 5 chars
     */
    @Test
    public void testCheckUserNameTooLong() {
        assertFalse("Username longer than 5 chars should be invalid", login.checkUserName("abcdef_"));
    }
    
    /**
     * Test checkUserName() - boundary case exactly 5 chars with underscore
     */
    @Test
    public void testCheckUserNameBoundary() {
        assertTrue("Username with exactly 5 chars and underscore should be valid", login.checkUserName("a_bcd"));
    }
    
    /**
     * Test checkUserName() - empty string
     */
    @Test
    public void testCheckUserNameEmpty() {
        assertFalse("Empty username should be invalid", login.checkUserName(""));
    }
    
    // ===== PASSWORD COMPLEXITY TESTS =====
    
    /**
     * Test checkPasswordComplexity() - valid complex password
     */
    @Test
    public void testCheckPasswordComplexityValid() {
        assertTrue("Valid complex password should pass", login.checkPasswordComplexity("SecurePass123!"));
    }
    
    /**
     * Test checkPasswordComplexity() - too short
     */
    @Test
    public void testCheckPasswordComplexityTooShort() {
        assertFalse("Password shorter than 8 chars should be invalid", login.checkPasswordComplexity("Sec!1"));
    }
    
    /**
     * Test checkPasswordComplexity() - boundary case exactly 8 chars
     */
    @Test
    public void testCheckPasswordComplexityBoundary() {
        assertTrue("Password with exactly 8 chars and all requirements should be valid", 
                login.checkPasswordComplexity("SecPass1!"));
    }
    
    /**
     * Test checkPasswordComplexity() - missing uppercase
     */
    @Test
    public void testCheckPasswordComplexityNoUppercase() {
        assertFalse("Password without uppercase should be invalid", login.checkPasswordComplexity("secpass1!"));
    }
    
    /**
     * Test checkPasswordComplexity() - missing number
     */
    @Test
    public void testCheckPasswordComplexityNoNumber() {
        assertFalse("Password without number should be invalid", login.checkPasswordComplexity("SecurePass!"));
    }
    
    /**
     * Test checkPasswordComplexity() - missing special character
     */
    @Test
    public void testCheckPasswordComplexityNoSpecialChar() {
        assertFalse("Password without special character should be invalid", login.checkPasswordComplexity("SecurePass123"));
    }
    
    /**
     * Test checkPasswordComplexity() - all uppercase with numbers and special char
     */
    @Test
    public void testCheckPasswordComplexityAllUppercase() {
        assertTrue("Password with all uppercase and other requirements should be valid", 
                login.checkPasswordComplexity("SECUREPASS1!"));
    }
    
    // ===== CELL PHONE NUMBER VALIDATION TESTS =====
    
    /**
     * Test checkCellPhoneNumber() - valid SA number
     */
    @Test
    public void testCheckCellPhoneNumberValid() {
        assertTrue("Valid SA number should pass", login.checkCellPhoneNumber("+27123456789"));
    }
    
    /**
     * Test checkCellPhoneNumber() - another valid SA number
     */
    @Test
    public void testCheckCellPhoneNumberValidAnotherNumber() {
        assertTrue("Another valid SA number should pass", login.checkCellPhoneNumber("+27987654321"));
    }
    
    /**
     * Test checkCellPhoneNumber() - missing + prefix
     */
    @Test
    public void testCheckCellPhoneNumberNoPrefix() {
        assertFalse("Number without + prefix should be invalid", login.checkCellPhoneNumber("27123456789"));
    }
    
    /**
     * Test checkCellPhoneNumber() - wrong country code
     */
    @Test
    public void testCheckCellPhoneNumberWrongCountryCode() {
        assertFalse("Number with wrong country code should be invalid", login.checkCellPhoneNumber("+28123456789"));
    }
    
    /**
     * Test checkCellPhoneNumber() - too few digits
     */
    @Test
    public void testCheckCellPhoneNumberTooFewDigits() {
        assertFalse("Number with too few digits should be invalid", login.checkCellPhoneNumber("+2712345678"));
    }
    
    /**
     * Test checkCellPhoneNumber() - too many digits
     */
    @Test
    public void testCheckCellPhoneNumberTooManyDigits() {
        assertFalse("Number with too many digits should be invalid", login.checkCellPhoneNumber("+271234567890"));
    }
    
    /**
     * Test checkCellPhoneNumber() - contains letters
     */
    @Test
    public void testCheckCellPhoneNumberWithLetters() {
        assertFalse("Number with letters should be invalid", login.checkCellPhoneNumber("+2712345678a"));
    }
    
    // ===== REGISTRATION TESTS =====
    
    /**
     * Test registerUser() - successful registration
     */
    @Test
    public void testRegisterUserSuccessful() {
        String result = login.registerUser("test_", "SecurePass123!", "+27123456789", "John", "Doe");
        assertEquals("Successful registration should return success message", 
                "User successfully registered.", result);
    }
    
    /**
     * Test registerUser() - invalid username
     */
    @Test
    public void testRegisterUserInvalidUsername() {
        String result = login.registerUser("toolong_username", "SecurePass123!", "+27123456789", "John", "Doe");
        assertEquals("Invalid username should return error message", 
                "Username is not correctly formatted.", result);
    }
    
    /**
     * Test registerUser() - invalid password
     */
    @Test
    public void testRegisterUserInvalidPassword() {
        String result = login.registerUser("test_", "weak", "+27123456789", "John", "Doe");
        assertEquals("Invalid password should return error message", 
                "Password is not correctly formatted.", result);
    }
    
    /**
     * Test registerUser() - invalid cell phone
     */
    @Test
    public void testRegisterUserInvalidCell() {
        String result = login.registerUser("test_", "SecurePass123!", "1234567890", "John", "Doe");
        assertEquals("Invalid cell phone should return error message", 
                "Cell phone number incorrectly formatted.", result);
    }
    
    /**
     * Test registerUser() - credentials are stored
     */
    @Test
    public void testRegisterUserStoresCredentials() {
        login.registerUser("user_", "Password1!", "+27111111111", "Jane", "Smith");
        
        // Verify login works with stored credentials
        boolean loginResult = login.loginUser("user_", "Password1!");
        assertTrue("Login should succeed with stored credentials", loginResult);
    }
    
    /**
     * Test registerUser() - multiple registrations
     */
    @Test
    public void testRegisterUserMultiple() {
        String result1 = login.registerUser("usr1_", "Pass1Word!", "+27111111111", "Alice", "Brown");
        String result2 = login.registerUser("usr2_", "Pass2Word!", "+27222222222", "Bob", "Green");
        
        assertEquals("First registration should succeed", "User successfully registered.", result1);
        assertEquals("Second registration should succeed", "User successfully registered.", result2);
    }
    
    // ===== LOGIN TESTS =====
    
    /**
     * Test loginUser() - successful login
     */
    @Test
    public void testLoginUserSuccessful() {
        login.registerUser("test_", "SecurePass123!", "+27123456789", "John", "Doe");
        boolean result = login.loginUser("test_", "SecurePass123!");
        assertTrue("Login with correct credentials should succeed", result);
    }
    
    /**
     * Test loginUser() - failed login with wrong username
     */
    @Test
    public void testLoginUserWrongUsername() {
        login.registerUser("test_", "SecurePass123!", "+27123456789", "John", "Doe");
        boolean result = login.loginUser("wrong_", "SecurePass123!");
        assertFalse("Login with wrong username should fail", result);
    }
    
    /**
     * Test loginUser() - failed login with wrong password
     */
    @Test
    public void testLoginUserWrongPassword() {
        login.registerUser("test_", "SecurePass123!", "+27123456789", "John", "Doe");
        boolean result = login.loginUser("test_", "WrongPass123!");
        assertFalse("Login with wrong password should fail", result);
    }
    
    /**
     * Test loginUser() - case sensitivity
     */
    @Test
    public void testLoginUserCaseSensitivity() {
        login.registerUser("test_", "SecurePass123!", "+27123456789", "John", "Doe");
        boolean result = login.loginUser("TEST_", "SecurePass123!");
        assertFalse("Login should be case sensitive for username", result);
    }
    
    /**
     * Test loginUser() - without registration
     */
    @Test
    public void testLoginUserWithoutRegistration() {
        boolean result = login.loginUser("any_user", "AnyPass123!");
        assertFalse("Login should fail without prior registration", result);
    }
    
    // ===== LOGIN STATUS TESTS =====
    
    /**
     * Test returnLoginStatus() - successful login status
     */
    @Test
    public void testReturnLoginStatusSuccess() {
        login.registerUser("test_", "SecurePass123!", "+27123456789", "John", "Doe");
        boolean loginStatus = login.loginUser("test_", "SecurePass123!");
        String result = login.returnLoginStatus(loginStatus);
        assertEquals("Successful login should return welcome message", 
                "Welcome John Doe, it is great to see you again.", result);
    }
    
    /**
     * Test returnLoginStatus() - failed login status
     */
    @Test
    public void testReturnLoginStatusFailure() {
        login.registerUser("test_", "SecurePass123!", "+27123456789", "John", "Doe");
        boolean loginStatus = login.loginUser("test_", "WrongPassword!");
        String result = login.returnLoginStatus(loginStatus);
        assertEquals("Failed login should return error message", 
                "Username or password incorrect.", result);
    }
    
    /**
     * Test returnLoginStatus() - with different names
     */
    @Test
    public void testReturnLoginStatusDifferentNames() {
        login.registerUser("usr1_", "Password1!", "+27111111111", "Alice", "Johnson");
        boolean loginStatus = login.loginUser("usr1_", "Password1!");
        String result = login.returnLoginStatus(loginStatus);
        assertEquals("Should show correct user names in message", 
                "Welcome Alice Johnson, it is great to see you again.", result);
    }
    
    // ===== INTEGRATION TESTS =====
    
    /**
     * Test full registration and login flow
     */
    @Test
    public void testFullRegistrationAndLoginFlow() {
        // Register user
        String registerResult = login.registerUser("john_", "JohnPass123!", "+27123456789", "John", "Doe");
        assertEquals("Registration should succeed", "User successfully registered.", registerResult);
        
        // Login with correct credentials
        boolean loginResult = login.loginUser("john_", "JohnPass123!");
        assertTrue("Login should succeed", loginResult);
        
        // Check login status
        String statusResult = login.returnLoginStatus(loginResult);
        assertEquals("Status should show welcome message", 
                "Welcome John Doe, it is great to see you again.", statusResult);
    }
    
    /**
     * Test registration validation order
     */
    @Test
    public void testRegistrationValidationOrder() {
        // Invalid username is checked first
        String result = login.registerUser("invaliduser", "SecurePass123!", "+27123456789", "John", "Doe");
        assertEquals("Should fail on username validation", 
                "Username is not correctly formatted.", result);
    }
    
    /**
     * Test password validation before cell phone
     */
    @Test
    public void testPasswordValidatedBeforeCellPhone() {
        String result = login.registerUser("test_", "weak", "invalid_phone", "John", "Doe");
        assertEquals("Should fail on password validation", 
                "Password is not correctly formatted.", result);
    }
}
