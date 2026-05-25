/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ttlho
 */
package com.mycompany.part2quickchat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageServiceTest {

    @Test
    public void testSendMessage() {
        MessageService service = new MessageService();
        Message msg = new Message("+27123456789", "Hello");

        String result = service.sendMessage(msg);

        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testStoreMessage() {
        MessageService service = new MessageService();
        Message msg = new Message("+27123456789", "Stored message");

        String result = service.storeMessage(msg);

        assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testDiscardMessage() {
        MessageService service = new MessageService();

        String result = service.discardMessage();

        assertEquals("Message discarded.", result);
    }

    @Test
    public void testCheckMessageLength() {
        Message msg = new Message("+27123456789", "Short message");

        assertTrue(msg.checkMessage());
    }

    @Test
    public void testInvalidMessageLength() {
        String longMsg = "A".repeat(300);
        Message msg = new Message("+27123456789", longMsg);

        assertFalse(msg.checkMessage());
    }

    @Test
    public void testValidRecipient() {
        Message msg = new Message("+27123456789", "Hi");

        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testInvalidRecipient() {
        Message msg = new Message("0123456789", "Hi");

        assertFalse(msg.checkRecipientCell());
    }

    private void assertEquals(String message_successfully_stored, String result) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}