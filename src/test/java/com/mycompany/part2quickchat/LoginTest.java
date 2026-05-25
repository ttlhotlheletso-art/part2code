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
    
    // ============ USERNAME VALIDATION TESTS ============
    
    @Test
    public void testCheckUserNameValid() {
        assertTrue("Username with underscore and <= 5 chars should be valid", login.checkUserName("u_123"));
    }
    
    @Test
    public void testCheckUserNameValidBoundary() {
        assertTrue("Username with exactly 5 characters and underscore should be valid", login.checkUserName("u_abc"));
    }
    
    @Test
    public void testCheckUserNameInvalidNoUnderscore() {
        assertFalse("Username without underscore should be invalid", login.checkUserName("username"));
    }
    
    @Test
    public void testCheckUserNameInvalidTooLong() {
        assertFalse("Username longer than 5 characters should be invalid", login.checkUserName("user_name"));
    }
    
    @Test
    public void testCheckUserNameOnlyUnderscore() {
        assertTrue("Single underscore should be valid", login.checkUserName("_"));
    }
    
    // ============ PASSWORD COMPLEXITY TESTS ============
    
    @Test
    public void testCheckPasswordComplexityValid() {
        assertTrue("Valid password should pass all checks", login.checkPasswordComplexity("MyPassword123!"));
    }
    
    @Test
    public void testCheckPasswordComplexityValidBoundary8Chars() {
        assertTrue("Password with exactly 8 characters and all requirements should be valid", login.checkPasswordComplexity("Abc1@xyz"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalidTooShort() {
        assertFalse("Password with less than 8 characters should be invalid", login.checkPasswordComplexity("Pass1@x"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalidNoUppercase() {
        assertFalse("Password without uppercase letter should be invalid", login.checkPasswordComplexity("password123!"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalidNoNumber() {
        assertFalse("Password without number should be invalid", login.checkPasswordComplexity("Password@abc"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalidNoSpecialChar() {
        assertFalse("Password without special character should be invalid", login.checkPasswordComplexity("Password123"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalidAllLowercase() {
        assertFalse("Password with all lowercase should be invalid", login.checkPasswordComplexity("password1!"));
    }
    
    // ============ CELL PHONE VALIDATION TESTS ============
    
    @Test
    public void testCheckCellPhoneNumberValid() {
        assertTrue("Valid SA cell number should pass", login.checkCellPhoneNumber("+27123456789"));
    }
    
    @Test
    public void testCheckCellPhoneNumberValidAnother() {
        assertTrue("Another valid SA cell number should pass", login.checkCellPhoneNumber("+27987654321"));
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalidNoPlus() {
        assertFalse("Cell number without + sign should be invalid", login.checkCellPhoneNumber("27123456789"));
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalidWrongCode() {
        assertFalse("Cell number with wrong country code should be invalid", login.checkCellPhoneNumber("+28123456789"));
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalidTooFewDigits() {
        assertFalse("Cell number with too few digits should be invalid", login.checkCellPhoneNumber("+2712345678"));
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalidTooManyDigits() {
        assertFalse("Cell number with too many digits should be invalid", login.checkCellPhoneNumber("+271234567890"));
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalidWithLetters() {
        assertFalse("Cell number with letters should be invalid", login.checkCellPhoneNumber("+27ABCDEFGHI"));
    }
    
    // ============ REGISTRATION TESTS ============
    
    @Test
    public void testRegisterUserSuccessful() {
        String result = login.registerUser("u_001", "MyPass1!", "+27123456789", "John", "Doe");
        assertEquals("User registration should succeed", "User successfully registered.", result);
    }
    
    @Test
    public void testRegisterUserInvalidUsername() {
        String result = login.registerUser("invaliduser", "MyPass1!", "+27123456789", "John", "Doe");
        assertEquals("Invalid username should return error", "Username is not correctly formatted.", result);
    }
    
    @Test
    public void testRegisterUserInvalidPassword() {
        String result = login.registerUser("u_001", "weak", "+27123456789", "John", "Doe");
        assertEquals("Invalid password should return error", "Password is not correctly formatted.", result);
    }
    
    @Test
    public void testRegisterUserInvalidCell() {
        String result = login.registerUser("u_001", "MyPass1!", "+28123456789", "John", "Doe");
        assertEquals("Invalid cell number should return error", "Cell phone number incorrectly formatted.", result);
    }
    
    @Test
    public void testRegisterUserStoresUsername() {
        login.registerUser("u_test", "Pass1@abc", "+27555555555", "Jane", "Smith");
        assertTrue("Stored credentials should allow login", login.loginUser("u_test", "Pass1@abc"));
    }
    
    // ============ LOGIN TESTS ============
    
    @Test
    public void testLoginUserSuccessful() {
        login.registerUser("u_001", "MyPass1!", "+27123456789", "John", "Doe");
        assertTrue("Valid credentials should allow login", login.loginUser("u_001", "MyPass1!"));
    }
    
    @Test
    public void testLoginUserFailedWrongUsername() {
        login.registerUser("u_001", "MyPass1!", "+27123456789", "John", "Doe");
        assertFalse("Wrong username should fail login", login.loginUser("u_002", "MyPass1!"));
    }
    
    @Test
    public void testLoginUserFailedWrongPassword() {
        login.registerUser("u_001", "MyPass1!", "+27123456789", "John", "Doe");
        assertFalse("Wrong password should fail login", login.loginUser("u_001", "WrongPass1!"));
    }
    
    @Test
    public void testLoginUserCaseSensitiveUsername() {
        login.registerUser("u_001", "MyPass1!", "+27123456789", "John", "Doe");
        assertFalse("Username should be case sensitive", login.loginUser("U_001", "MyPass1!"));
    }
    
    @Test
    public void testLoginUserCaseSensitivePassword() {
        login.registerUser("u_001", "MyPass1!", "+27123456789", "John", "Doe");
        assertFalse("Password should be case sensitive", login.loginUser("u_001", "mypass1!"));
    }
    
    @Test
    public void testLoginUserWithoutRegistration() {
        assertFalse("Login without registration should fail", login.loginUser("u_001", "MyPass1!"));
    }
    
    // ============ LOGIN STATUS TESTS ============
    
    @Test
    public void testReturnLoginStatusSuccessful() {
        login.registerUser("u_001", "MyPass1!", "+27123456789", "John", "Doe");
        String status = login.returnLoginStatus(true);
        assertTrue("Successful login should show welcome message", status.contains("Welcome"));
        assertTrue("Welcome message should contain first name", status.contains("John"));
        assertTrue("Welcome message should contain last name", status.contains("Doe"));
    }
    
    @Test
    public void testReturnLoginStatusFailed() {
        String status = login.returnLoginStatus(false);
        assertEquals("Failed login should show error message", "Username or password incorrect.", status);
    }
    
    @Test
    public void testReturnLoginStatusFormatMessage() {
        login.registerUser("u_002", "Pass1@abc", "+27999999999", "Alice", "Johnson");
        String status = login.returnLoginStatus(true);
        assertEquals("Welcome message format should be correct", 
            "Welcome Alice Johnson, it is great to see you again.", status);
    }
    
    // ============ INTEGRATION TESTS ============
    
    @Test
    public void testFullRegistrationAndLoginFlow() {
        // Register
        String regResult = login.registerUser("u_int", "MyInteg1@", "+27111111111", "Integration", "Test");
        assertEquals("Registration should succeed", "User successfully registered.", regResult);
        
        // Login
        boolean loginResult = login.loginUser("u_int", "MyInteg1@");
        assertTrue("Login after registration should succeed", loginResult);
        
        // Check status
        String statusResult = login.returnLoginStatus(loginResult);
        assertEquals("Status message should be correct", 
            "Welcome Integration Test, it is great to see you again.", statusResult);
    }
    
    @Test
    public void testMultipleRegistrations() {
        String result1 = login.registerUser("u_001", "Pass1@abc", "+27111111111", "User", "One");
        assertEquals("First registration should succeed", "User successfully registered.", result1);
        
        // Second registration with different credentials will overwrite first
        String result2 = login.registerUser("u_002", "Pass2@def", "+27222222222", "User", "Two");
        assertEquals("Second registration should succeed", "User successfully registered.", result2);
        
        // Only second user should be logged in
        assertTrue("Second user should be able to login", login.loginUser("u_002", "Pass2@def"));
        assertFalse("First user should no longer work after second registration", login.loginUser("u_001", "Pass1@abc"));
    }
}
