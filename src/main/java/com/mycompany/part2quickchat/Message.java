/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part2quickchat;

/**
 *
 * @author ttlho
 */


import org.json.JSONObject;
import java.util.Random;

public class Message {

    private static int totalMessages = 0;

    private String messageId;
    private String recipient;
    private String message;

    // ✅ FIX: constructor with parameters
    public Message(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
        this.messageId = generateId();
        totalMessages++;
    }

    private String generateId() {
        Random rand = new Random();
        return String.valueOf(100000000 + rand.nextInt(900000000));
    }

    // ✅ FIX: method exists now
    public boolean checkMessage() {
        return message.length() <= 250;
    }

    // ✅ FIX: method exists now
    public boolean checkRecipientCell() {
        return recipient.matches("^\\+27\\d{9}$");
    }

    public String getMessageId() {
        return messageId;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    // ✅ FIX: method exists now
    public static int getTotalMessages() {
        return totalMessages;
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("id", messageId);
        obj.put("recipient", recipient);
        obj.put("message", message);
        return obj;
    }
}