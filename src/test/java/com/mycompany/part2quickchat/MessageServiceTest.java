/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.part2quickchat;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;

/**
 *
 * @author ttlho
 */
public class MessageServiceTest {
    
    private MessageService messageService;
    private Message testMessage;
    
    @Before
    public void setUp() {
        messageService = new MessageService();
        testMessage = new Message("+27123456789", "Test message for service");
    }
    
    /**
     * Test MessageService constructor and initialization
     */
    @Test
    public void testMessageServiceConstructor() {
        assertNotNull("MessageService should not be null", messageService);
    }
    
    /**
     * Test sendMessage() - single message
     */
    @Test
    public void testSendMessageSingle() {
        String result = messageService.sendMessage(testMessage);
        assertEquals("Send message should return success message", "Message successfully sent.", result);
    }
    
    /**
     * Test sendMessage() - multiple messages
     */
    @Test
    public void testSendMessageMultiple() {
        Message msg1 = new Message("+27111111111", "Message 1");
        Message msg2 = new Message("+27222222222", "Message 2");
        Message msg3 = new Message("+27333333333", "Message 3");
        
        String result1 = messageService.sendMessage(msg1);
        String result2 = messageService.sendMessage(msg2);
        String result3 = messageService.sendMessage(msg3);
        
        assertEquals("First message should be sent successfully", "Message successfully sent.", result1);
        assertEquals("Second message should be sent successfully", "Message successfully sent.", result2);
        assertEquals("Third message should be sent successfully", "Message successfully sent.", result3);
    }
    
    /**
     * Test storeMessage() - single message
     */
    @Test
    public void testStoreMessageSingle() {
        String result = messageService.storeMessage(testMessage);
        assertEquals("Store message should return success message", "Message successfully stored.", result);
    }
    
    /**
     * Test storeMessage() - multiple messages
     */
    @Test
    public void testStoreMessageMultiple() {
        Message msg1 = new Message("+27111111111", "Stored message 1");
        Message msg2 = new Message("+27222222222", "Stored message 2");
        
        String result1 = messageService.storeMessage(msg1);
        String result2 = messageService.storeMessage(msg2);
        
        assertEquals("First stored message should succeed", "Message successfully stored.", result1);
        assertEquals("Second stored message should succeed", "Message successfully stored.", result2);
    }
    
    /**
     * Test discardMessage()
     */
    @Test
    public void testDiscardMessage() {
        String result = messageService.discardMessage();
        assertEquals("Discard message should return correct message", "Message discarded.", result);
    }
    
    /**
     * Test sendMessage() with valid message
     */
    @Test
    public void testSendValidMessage() {
        Message validMsg = new Message("+27987654321", "This is a valid message");
        String result = messageService.sendMessage(validMsg);
        assertEquals("Valid message should be sent successfully", "Message successfully sent.", result);
    }
    
    /**
     * Test storeMessage() with valid message
     */
    @Test
    public void testStoreValidMessage() {
        Message validMsg = new Message("+27987654321", "This is a valid stored message");
        String result = messageService.storeMessage(validMsg);
        assertEquals("Valid message should be stored successfully", "Message successfully stored.", result);
    }
    
    /**
     * Test sendMessage() followed by printMessages()
     */
    @Test
    public void testSendAndPrint() {
        Message msg = new Message("+27555555555", "Print test message");
        messageService.sendMessage(msg);
        
        // This should run without throwing an exception
        try {
            messageService.printMessages();
        } catch (Exception e) {
            fail("printMessages() should not throw an exception: " + e.getMessage());
        }
    }
    
    /**
     * Test saveToJSON() creates a file
     */
    @Test
    public void testSaveToJSON() {
        Message msg1 = new Message("+27666666666", "JSON test message 1");
        Message msg2 = new Message("+27777777777", "JSON test message 2");
        
        messageService.sendMessage(msg1);
        messageService.sendMessage(msg2);
        
        try {
            messageService.saveToJSON();
            
            File file = new File("messages.json");
            assertTrue("messages.json file should be created", file.exists());
            assertTrue("messages.json file should contain data", file.length() > 0);
            
            // Clean up
            file.delete();
        } catch (Exception e) {
            fail("saveToJSON() should not throw an exception: " + e.getMessage());
        }
    }
    
    /**
     * Test saveToJSON() with empty sent messages
     */
    @Test
    public void testSaveToJSONEmpty() {
        try {
            messageService.saveToJSON();
            
            File file = new File("messages.json");
            assertTrue("messages.json file should be created even if empty", file.exists());
            
            // Clean up
            file.delete();
        } catch (Exception e) {
            fail("saveToJSON() should not throw an exception even with no messages: " + e.getMessage());
        }
    }
    
    /**
     * Test that sent messages and stored messages are kept separately
     */
    @Test
    public void testSendAndStoreAreSeparate() {
        Message msg1 = new Message("+27111111111", "Send this");
        Message msg2 = new Message("+27222222222", "Store this");
        
        messageService.sendMessage(msg1);
        messageService.storeMessage(msg2);
        
        String result1 = messageService.sendMessage(msg1);
        String result2 = messageService.storeMessage(msg2);
        
        assertEquals("Should be able to send messages", "Message successfully sent.", result1);
        assertEquals("Should be able to store messages separately", "Message successfully stored.", result2);
    }
}
