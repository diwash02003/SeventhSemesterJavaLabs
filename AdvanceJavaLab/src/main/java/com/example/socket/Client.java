package com.example.socket;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String address = "localhost";
        int port = 9999;
        System.out.println("Connecting to " + address + ":" + port);

        try (Socket socket = new Socket(address, port);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter serverWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader clientReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to server.");

            // Thread to read messages from server
            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = serverReader.readLine()) != null) {
                        if (message.equalsIgnoreCase("bye")) {
                            System.out.println("\nServer: " + message);
                            System.out.println("Server ended the chat.");
                            break;
                        }
                        System.out.println("\nServer: " + message);
                        System.out.print("You: ");
                    }
                } catch (IOException e) {
                    System.out.println("\nConnection closed by server.");
                }
            });

            readThread.start();

            // Main thread to send messages
            String input;
            System.out.print("You: ");
            while ((input = clientReader.readLine()) != null) {
                serverWriter.println(input);
                if (input.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Connection closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}