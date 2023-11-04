/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserScope;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import Connection.Conn;

public class CancelBooking extends JFrame {

//    Connection conn = null;
//    PreparedStatement pst = null;
    public JPanel contentPane;
//    private JTextField t1, t2, roomno, t4, t5, t6;
    public JLabel pricelbl, bedlbl, dcdob;
//    JComboBox comboBox;
//    JRadioButton r1, r2;
    Choice roomChoice;
    private String userEmail;
//    private Profile userProfile;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CancelBooking("has@gmail.com");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public CancelBooking(String uemail) {
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

        JLabel lblName = new JLabel("Cancel Booked Rooms");
        lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblName.setBounds(118, 11, 260, 53);
        contentPane.add(lblName);

        JLabel lblReserveRoomNumber = new JLabel("Room Number :");
        lblReserveRoomNumber.setBounds(35, 100, 200, 14);
        contentPane.add(lblReserveRoomNumber);

        roomChoice = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customerBooking where customer_email = '" + userEmail + "'");
            while (rs.next()) {
                roomChoice.add(rs.getString("room_number"));
            }
        } catch (Exception e) {
           System.out.print("Error 404");
        }
        roomChoice.setBounds(271, 100, 150, 20);
        contentPane.add(roomChoice);

        JLabel lblCheckInStatus = new JLabel("Bed Type :");
        lblCheckInStatus.setBounds(35, 175, 200, 14);
        contentPane.add(lblCheckInStatus);

        JLabel lblDeposite = new JLabel("Price :");
        lblDeposite.setBounds(35, 250, 200, 14);
        contentPane.add(lblDeposite);

        bedlbl = new JLabel();
        bedlbl.setBounds(271, 175, 150, 20);
        contentPane.add(bedlbl);

        pricelbl = new JLabel();
        pricelbl.setBounds(271, 250, 150, 20);
        contentPane.add(pricelbl);

        JLabel lbldob = new JLabel("Booking Date");
        lbldob.setBounds(35, 325, 200, 30);
        add(lbldob);
        
        dcdob = new JLabel();
        dcdob.setBounds(271, 325, 150, 30);
        add(dcdob);

        try {
            Conn c = new Conn();
            String query = "select * from customerBooking where room_number='" + roomChoice.getSelectedItem() + "'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                pricelbl.setText(rs.getString("room_price"));
                bedlbl.setText(rs.getString("room_type"));
                dcdob.setText(rs.getString("booking_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Copy
        roomChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from customerBooking where room_number='" + roomChoice.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()) {
                        pricelbl.setText(rs.getString("room_price"));
                        bedlbl.setText(rs.getString("room_type"));
                        dcdob.setText(rs.getString("booking_date"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JButton btnNewButton = new JButton("Cancel Booking");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreparedStatement ps;
                PreparedStatement ps1;
                Conn con = new Conn();
                String s6 = roomChoice.getSelectedItem();
                try {                   
                    ps = con.c.prepareStatement("update room set availability = 'Available' where room_number = " + s6);
                    ps1 = con.c.prepareStatement("delete from customerBooking where room_number = " + s6);
                    ps.executeUpdate();
                    ps1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Booked Room Cancelled Successfully");
                    new EmailSenderCancelling(userEmail,roomChoice.getSelectedItem(), bedlbl.getText(), pricelbl.getText(),dcdob.getText());
                    setVisible(false);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(100, 430, 150, 30);
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
