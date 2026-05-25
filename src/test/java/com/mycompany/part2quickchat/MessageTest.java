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
public class MessageTest {
    
    private Message message;
    
    @Before
    public void setUp() {
        message = new Message("+27123456789", "Hello, this is a test message");
    }
    
    /**
     * Test Message constructor
     */
    @Test
    public void testMessageConstructor() {
        assertNotNull("Message should not be null", message);
        assertEquals("Recipient should match", "+27123456789", message.getRecipient());
        assertEquals("Message should match", "Hello, this is a test message", message.getMessage());
        assertNotNull("Message ID should not be null", message.getMessageId());
    }
    
    /**
     * Test checkMessage() - valid message (within 250 chars)
     */
    @Test
    public void testCheckMessageValid() {
        Message validMsg = new Message("+27123456789", "Short message");
        assertTrue("Message with less than 250 characters should be valid", validMsg.checkMessage());
    }
    
    /**
     * Test checkMessage() - invalid message (exceeds 250 chars)
     */
    @Test
    public void testCheckMessageInvalid() {
        String longMessage = "a".repeat(251); // 251 characters
        Message invalidMsg = new Message("+27123456789", longMessage);
        assertFalse("Message with more than 250 characters should be invalid", invalidMsg.checkMessage());
    }
    
    /**
     * Test checkMessage() - boundary case (exactly 250 chars)
     */
    @Test
    public void testCheckMessageBoundary() {
        String boundaryMessage = "a".repeat(250); // exactly 250 characters
        Message boundaryMsg = new Message("+27123456789", boundaryMessage);
        assertTrue("Message with exactly 250 characters should be valid", boundaryMsg.checkMessage());
    }
    
    /**
     * Test checkRecipientCell() - valid cell number
     */
    @Test
    public void testCheckRecipientCellValid() {
        assertTrue("Valid cell number should pass", message.checkRecipientCell());
    }
    
    /**
     * Test checkRecipientCell() - invalid format (missing +27)
     */
    @Test
    public void testCheckRecipientCellInvalidNoPrefix() {
        Message invalidMsg = new Message("123456789", "Test");
        assertFalse("Cell number without +27 prefix should be invalid", invalidMsg.checkRecipientCell());
    }
    
    /**
     * Test checkRecipientCell() - invalid format (wrong country code)
     */
    @Test
    public void testCheckRecipientCellInvalidWrongPrefix() {
        Message invalidMsg = new Message("+28123456789", "Test");
        assertFalse("Cell number with wrong country code should be invalid", invalidMsg.checkRecipientCell());
    }
    
    /**
     * Test checkRecipientCell() - invalid format (too few digits)
     */
    @Test
    public void testCheckRecipientCellInvalidTooFewDigits() {
        Message invalidMsg = new Message("+2712345678", "Test"); // 8 digits instead of 9
        assertFalse("Cell number with too few digits should be invalid", invalidMsg.checkRecipientCell());
    }
    
    /**
     * Test checkRecipientCell() - invalid format (too many digits)
     */
    @Test
    public void testCheckRecipientCellInvalidTooManyDigits() {
        Message invalidMsg = new Message("+271234567890", "Test"); // 10 digits instead of 9
        assertFalse("Cell number with too many digits should be invalid", invalidMsg.checkRecipientCell());
    }
    
    /**
     * Test getMessageId()
     */
    @Test
    public void testGetMessageId() {
        String messageId = message.getMessageId();
        assertNotNull("Message ID should not be null", messageId);
        assertTrue("Message ID should be a valid 9-digit number", messageId.matches("\\d{9}"));
    }
    
    /**
     * Test getRecipient()
     */
    @Test
    public void testGetRecipient() {
        assertEquals("Recipient should match constructor parameter", "+27123456789", message.getRecipient());
    }
    
    /**
     * Test getMessage()
     */
    @Test
    public void testGetMessage() {
        assertEquals("Message should match constructor parameter", "Hello, this is a test message", message.getMessage());
    }
    
    /**
     * Test getTotalMessages() - static method
     */
    @Test
    public void testGetTotalMessages() {
        int initialCount = Message.getTotalMessages();
        Message newMsg = new Message("+27987654321", "Another message");
        assertEquals("Total messages should increment", initialCount + 1, Message.getTotalMessages());
    }
    
    /**
     * Test toJSON() method
     */
    @Test
    public void testToJSON() {
        var json = message.toJSON();
        assertNotNull("JSON should not be null", json);
        assertEquals("JSON recipient should match", "+27123456789", json.getString("recipient"));
        assertEquals("JSON message should match", "Hello, this is a test message", json.getString("message"));
        assertNotNull("JSON should have id field", json.get("id"));
    }
}
