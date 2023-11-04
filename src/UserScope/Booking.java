/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserScope;

import java.awt.BorderLayout;
import java.awt.*;
import Connection.Conn;
import com.toedter.calendar.JDateChooser;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Image;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.*;

public class Booking extends JFrame {

    public JPanel contentPane;
    public JLabel pricelbl, bedlbl;
    Choice roomChoice;
    private String userEmail;
    JDateChooser bookingDate;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Booking("has@gmail.com");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //throws SQLException
    public Booking(String uemail) {
        this.userEmail = uemail;
        setBounds(400, 200, 1000, 550);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/HotelBooking.png"));
        Image i3 = i1.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(470, 10, 550, 500);
        add(l1);

        JLabel availableRooms = new JLabel("Available Rooms");
        availableRooms.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        availableRooms.setBounds(118, 11, 260, 53);
        contentPane.add(availableRooms);

        JLabel lblReserveRoomNumber = new JLabel("Room Number :");
        lblReserveRoomNumber.setBounds(35, 100, 200, 14);
        contentPane.add(lblReserveRoomNumber);

        roomChoice = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from room where availability = 'Available'");
            while (rs.next()) {
                roomChoice.add(rs.getString("room_number"));
            }
        } catch (Exception e) {
        }
        roomChoice.setBounds(271, 100, 150, 20);
        contentPane.add(roomChoice);

        JLabel lblBedType = new JLabel("Bed Type :");
        lblBedType.setBounds(35, 175, 200, 14);
        contentPane.add(lblBedType);

        JLabel lblPrice = new JLabel("Price :");
        lblPrice.setBounds(35, 250, 200, 14);
        contentPane.add(lblPrice);
        
        bedlbl = new JLabel();
        bedlbl.setBounds(271, 175, 150, 20);
        contentPane.add(bedlbl);

        pricelbl = new JLabel();
        pricelbl.setBounds(271, 250, 150, 20);
        contentPane.add(pricelbl);

        JLabel lblBookingDate = new JLabel("Booking Date");
        lblBookingDate.setBounds(35, 325, 200, 30);
        add(lblBookingDate);
        
        bookingDate = new JDateChooser();
        bookingDate.setBounds(271, 325, 150, 30);
        add(bookingDate);

        try {
            Conn c = new Conn();
            String query = "select * from room where room_number='" + roomChoice.getSelectedItem() + "'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                pricelbl.setText(rs.getString("price"));
                bedlbl.setText(rs.getString("room_type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Copy
        roomChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from room where room_number='" + roomChoice.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()) {
                        pricelbl.setText(rs.getString("price"));
                        bedlbl.setText(rs.getString("room_type"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JButton btnNewButton = new JButton("Book");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String price = pricelbl.getText();
                String type = bedlbl.getText();
                String dob = ((JTextField) bookingDate.getDateEditor().getUiComponent()).getText();
                PreparedStatement ps;
                PreparedStatement ps1;
                Conn con = new Conn();
                String s6 = roomChoice.getSelectedItem();
                String queryName = "select name from customerAccount where email = ?";
                String queryPhone = "select phone from customerAccount where email = ?";
                String queryEmail = "select email from customerAccount where email = ?";
                String username = null;
                String phone = null;
                String email = null;
                try {
                    PreparedStatement psName = con.c.prepareStatement(queryName);
                    psName.setString(1, userEmail);
                    ResultSet rsName = psName.executeQuery();
                    if (rsName.next()) {
                        username = rsName.getString("name");
                    }

                    PreparedStatement psPhone = con.c.prepareStatement(queryPhone);
                    psPhone.setString(1, userEmail);
                    ResultSet rsPhone = psPhone.executeQuery();
                    if (rsPhone.next()) {
                        phone = rsPhone.getString("phone");
                    }
                    
                    PreparedStatement psEmail = con.c.prepareStatement(queryEmail);
                    psEmail.setString(1, userEmail);
                    ResultSet rsEmail = psEmail.executeQuery();
                    if (rsEmail.next()) {
                        email = rsEmail.getString("email");
                    }

                    // Check if username and phone are not null before inserting
                    if (username != null && phone != null) {
                        ps = con.c.prepareStatement("update room set availability = 'Booked' where room_number = " + s6);
                        ps1 = con.c.prepareStatement("insert into customerBooking(room_number, room_type, booking_date,room_price, customer_name, customer_phone, customer_email) values(?,?,?,?,?,?,?)");
                        ps1.setString(1, s6);
                        ps1.setString(2, type);
                        ps1.setString(3,dob);
                        ps1.setString(4, price);
                        ps1.setString(5, username);
                        ps1.setString(6, phone);
                        ps1.setString(7,email);
                        ps.executeUpdate();
                        ps1.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Room Booked Successfully");
                        new EmailSender(userEmail,roomChoice.getSelectedItem(), bedlbl.getText(), pricelbl.getText(), ((JTextField) bookingDate.getDateEditor().getUiComponent()).getText());
                     
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Customer data not found");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(100, 430, 120, 30);
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setForeground(Color.BLACK);
        contentPane.add(btnNewButton);

        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnExit.setBounds(260, 430, 120, 30);
        btnExit.setBackground(Color.WHITE);
        btnExit.setForeground(Color.BLACK);
        setVisible(true);
        contentPane.add(btnExit);

        getContentPane().setBackground(Color.WHITE);
    }
}

