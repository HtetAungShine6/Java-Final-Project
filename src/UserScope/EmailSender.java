/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserScope;

import java.awt.EventQueue;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    
    String userEmail;
    String roomNumber;
    String roomType;
    String price;
    String bookingDate;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new EmailSender("has@gmail.com", "101", "Deluxe Room", "150", "2023-09-30");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
    }
    
    public EmailSender(String uemail, String roomNumber, String roomType, String price, String bookingDate){
        this.userEmail = uemail;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.bookingDate = bookingDate;
        
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587"); // Use 587 for TLS
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("hotelmanagementsystemjava@gmail.com", "tqbf viqi busf twwk");
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hotelmanagementsystemjava@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Room Booking Confirmation");

            // Create the email content
            String emailContent = "Dear Customer,\n\n"
                    + "You have successfully booked a room with the following details:\n\n"
                    + "Room Number: " + roomNumber + "\n"
                    + "Room Type: " + roomType + "\n"
                    + "Price: " + price + " baht\n"
                    + "Booking Date: " + bookingDate + "\n\n"
                    + "Thank you for choosing our hotel! 🥳 😍";
            
            message.setText(emailContent);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}