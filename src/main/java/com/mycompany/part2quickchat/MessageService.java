/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ttlho
 */
package com.mycompany.part2quickchat;

import org.json.JSONArray;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageService {

    private List<Message> sentMessages = new ArrayList<>();
    private List<Message> storedMessages = new ArrayList<>();

    // NO CONSTRUCTOR AT ALL

    public String sendMessage(Message msg) {
        sentMessages.add(msg);
        return "Message successfully sent.";
    }

    public String storeMessage(Message msg) {
        storedMessages.add(msg);
        return "Message successfully stored.";
    }

    public String discardMessage() {
        return "Message discarded.";
    }

    public void printMessages() {
        for (Message msg : sentMessages) {
            System.out.println("ID: " + msg.getMessageId());
            System.out.println("Recipient: " + msg.getRecipient());
            System.out.println("Message: " + msg.getMessage());
            System.out.println("----------------------");
        }
    }

    public void saveToJSON() {
        JSONArray array = new JSONArray();

        for (Message msg : sentMessages) {
            array.put(msg.toJSON());
        }

        try (FileWriter file = new FileWriter("messages.json")) {
            file.write(array.toString(4));
            System.out.println("Messages saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}