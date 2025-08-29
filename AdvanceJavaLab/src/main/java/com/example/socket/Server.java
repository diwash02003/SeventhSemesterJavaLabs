package com.example.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 9999;
        System.out.println("Server started on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket socket = serverSocket.accept();
             BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter clientWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to " + socket.getInetAddress() + ":" + socket.getPort());

            // Thread to read messages from client
            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = clientReader.readLine()) != null) {
                        if (message.equalsIgnoreCase("bye")) {
                            System.out.println("\nClient: " + message);
                            System.out.println("Client disconnected.");
                            break;
                        }
                        System.out.println("\nClient: " + message);
                        System.out.print("You: ");
                    }
                } catch (IOException e) {
                    System.out.println("\nConnection closed by client.");
                }
            });

            readThread.start();

            // Main thread to send messages
            String input;
            System.out.print("You: ");
            while ((input = consoleReader.readLine()) != null) {
                clientWriter.println(input);
                if (input.equalsIgnoreCase("bye")) {
                    break;
                }
                // The 'You: ' prompt will be printed by the main thread
                // after the user types their message.
                // The readThread handles the client messages and prints the prompt
                // after receiving a message.
            }

            socket.close();
            System.out.println("Connection closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}