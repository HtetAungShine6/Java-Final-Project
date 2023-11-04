/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserScope;

import Connection.Conn;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Profile extends JFrame{
    
    JLabel username, dob, phone, nationality;
    JButton okbtn;
    private String userEmail;
    private String userName;
    private String userPhone;
    
    public String getUsername() {
        return userName;
    }

    public String getPhone() {
        return userPhone;
    }
    public Profile(String uemail){
        
        this.userEmail = uemail;
        setSize(900, 650);
        setLocation(350, 50);
        
        setLayout(null);
        
        JLabel heading = new JLabel("Cutomer's Profile");
        heading.setBounds(300, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(200, 150, 200, 30);
        lblname.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(lblname);
        
        username = new JLabel();
        username.setBounds(400, 150, 200, 30);
        username.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(username);
        
        JLabel lbldob = new JLabel("Date of Birth");
        lbldob.setBounds(200, 200, 200, 30);
        lbldob.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(lbldob);
        
        dob = new JLabel();
        dob.setBounds(400, 200, 200, 30);
        dob.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(dob);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(200, 250, 200, 30);
        lblphone.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(lblphone);
        
        phone = new JLabel();
        phone.setBounds(400, 250, 200, 30);
        phone.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(phone);
        
        JLabel lblnation = new JLabel("Email");
        lblnation.setBounds(200, 300, 200, 30);
        lblnation.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(lblnation);
        
        nationality = new JLabel();
        nationality.setBounds(400, 300, 300, 30);
        nationality.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(nationality);
        
        try {
            Conn c = new Conn();
            String query = "select * from customerAccount where email='"+ userEmail +"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                username.setText(rs.getString("name"));
                dob.setText(rs.getString("date_of_birth"));
                phone.setText(rs.getString("phone"));
                nationality.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setVisible(true);
    }
    
    public static void main(String[] args){
        new Profile("1831009@au.edu");
    }
}
