package com.example.suingApplication;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author diwash
 * @date 8/29/25
 * @description This file contains...
 */


public class EmailSenderGUI extends JFrame {
    private final JTextField toField;
    private final JTextField subjectField;
    private final JTextArea messageArea;
    private JButton sendButton;
    private static final Dotenv dotenv = Dotenv.load();

    // Your email config
    private static final String FROM_EMAIL = dotenv.get("FROM_EMAIL");
    private static final String APP_PASSWORD =  dotenv.get("APP_PASSWORD");

    public EmailSenderGUI() {
        setTitle("Email Sender");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 1));

        formPanel.add(new JLabel("To:"));
        toField = new JTextField();
        formPanel.add(toField);

        formPanel.add(new JLabel("Subject:"));
        subjectField = new JTextField();
        formPanel.add(subjectField);

        formPanel.add(new JLabel("Message:"));
        messageArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        sendButton = new JButton("Send Email");
        sendButton.addActionListener(e -> sendEmail());

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(sendButton, BorderLayout.SOUTH);
    }

    private void sendEmail() {
        String to = toField.getText().trim();
        String subject = subjectField.getText().trim();
        String message = messageArea.getText().trim();

        if (to.isEmpty() || subject.isEmpty() || message.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "All fields are required.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Authenticator
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM_EMAIL));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(message);

            Transport.send(msg);

            JOptionPane.showMessageDialog(this,
                    "Email sent successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            toField.setText("");
            subjectField.setText("");
            messageArea.setText("");

        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to send email: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmailSenderGUI gui = new EmailSenderGUI();
            gui.setVisible(true);
        });
    }
}
