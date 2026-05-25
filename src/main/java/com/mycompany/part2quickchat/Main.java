package com.mycompany.part2quickchat;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();
        MessageService service = new MessageService();

        System.out.println("=== REGISTER ===");

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter phone (+27...): ");
        String phone = input.nextLine();

        String result = login.registerUser(username, password, phone, firstName, lastName);
        System.out.println(result);

        // STOP if registration fails
        if (!result.equals("User successfully registered.")) {
            return;
        }

        System.out.println("\n=== LOGIN ===");

        System.out.print("Enter username: ");
        String loginUser = input.nextLine();

        System.out.print("Enter password: ");
        String loginPass = input.nextLine();

        boolean status = login.loginUser(loginUser, loginPass);
        System.out.println(login.returnLoginStatus(status));

        if (!status) {
            return;
        }

        // ✅ PART 2 STARTS HERE
        System.out.println("\n=== QUICKCHAT ===");

        System.out.print("How many messages do you want to send? ");
        int total = input.nextInt();
        input.nextLine(); // clear buffer

        for (int i = 0; i < total; i++) {

            System.out.println("\nMessage " + (i + 1));

            System.out.print("Enter recipient (+27...): ");
            String recipient = input.nextLine();

            System.out.print("Enter message: ");
            String text = input.nextLine();

            Message msg = new Message(recipient, text);

            if (!msg.checkRecipientCell()) {
                System.out.println("Invalid phone number.");
                continue;
            }

            if (!msg.checkMessage()) {
                System.out.println("Message too long.");
                continue;
            }

            System.out.println("1) Send");
            System.out.println("2) Store");
            System.out.println("3) Discard");
            System.out.print("Choose option: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(service.sendMessage(msg));
                    break;
                case 2:
                    System.out.println(service.storeMessage(msg));
                    break;
                case 3:
                    System.out.println(service.discardMessage());
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        System.out.println("\n=== SENT MESSAGES ===");
        service.printMessages();

        service.saveToJSON();

        System.out.println("Total messages sent: " + Message.getTotalMessages());

        input.close();
    }
}