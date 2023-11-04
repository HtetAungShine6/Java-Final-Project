/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserScope;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import Connection.Conn;
import java.sql.PreparedStatement;

public class Register extends JFrame implements ActionListener {
    
    JTextField tfname, tfphone, tfemail, tfpsw, tfid, tfcountry;
//    JLabel labelrollno, labelmail;
    JDateChooser dcdob;
//    JComboBox faculty;
    JButton submit, cancel;
    JRadioButton r1, r2;
    
//    Random ran = new Random();
//    int id = (int) ((Math.random() * 9000000) + 1000000);
    
    public Register(){
//        int id;
//        boolean uniqueId = false;
//        do {
//            id = (int) ((Math.random() * 9000000) + 1000000);
//            uniqueId = isIdUnique(id); // Check if the ID is unique
//        } while (!uniqueId);
//
//        this.id = id; 

        setSize(900,700);
        setLocation(350,50);
        setLayout(null);
        
        //Add Student Heading
        JLabel heading = new JLabel("Registration");
        heading.setBounds(340,30,500,50);
        heading.setFont(new Font("serif", Font.BOLD,30));
        add(heading);
        
        //Student's Name
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(50, 150, 100, 30);
        lblname.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 150, 150, 30);
        add(tfname);
        
        //Student's Father's Name
        JLabel lblgander = new JLabel("Gender");
        lblgander.setBounds(400, 150, 200, 30);
        lblgander.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(lblgander);
        
        r1 = new JRadioButton("Male");
        r1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        r1.setBackground(Color.WHITE);
        r1.setBounds(585, 150, 100, 30);
        add(r1);
                
        r2 = new JRadioButton("Female");
        r2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        r2.setBackground(Color.WHITE);
        r2.setBounds(680, 150, 200, 30);
	add(r2);
//        tffname = new JTextField();
//        tffname.setBounds(600, 150, 150, 30);
//        add(tffname);
        
        //Student's Mother's Name
        JLabel lblpassport = new JLabel("Id/Passport");
        lblpassport.setBounds(50, 200, 200, 30);
        lblpassport.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(lblpassport);
        
        tfid = new JTextField();
        tfid.setBounds(200, 200, 150, 30);
        add(tfid);
        
        //Address
        JLabel lblcountry = new JLabel("Country");
        lblcountry.setBounds(400, 200, 200, 30);
        lblcountry.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(lblcountry);
        
        tfcountry = new JTextField();
        tfcountry.setBounds(600, 200, 150, 30);
        add(tfcountry);
        
        //DOB
        JLabel lbldob = new JLabel("Date of Birth");
        lbldob.setBounds(50, 250, 200, 30);
        lbldob.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(lbldob);
        
        dcdob = new JDateChooser();
        dcdob.setBounds(200, 250, 150, 30);
        add(dcdob);
        
        //Phone
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(400, 250, 200, 30);
        lblphone.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        add(tfphone);
        
        //Nationality
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(50, 300, 200, 30);
        lblemail.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);
        
        //Passport No.
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(400, 300, 200, 30);
        lblpassword.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(lblpassword);
        
        tfpsw = new JPasswordField();
        tfpsw.setBounds(600, 300, 150, 30);
        add(tfpsw);                 
        
        submit = new JButton("Submit");
        submit.setBounds(250, 550, 120, 30);
        submit.setBackground(Color.WHITE);
        submit.setForeground(Color.BLACK);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(450, 550, 120, 30);
        cancel.setBackground(Color.WHITE);
        cancel.setForeground(Color.BLACK);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        PreparedStatement ps;
        if (ae.getSource() == submit) {
            String name = tfname.getText();
            String radio = null;
            if(r1.isSelected()){
                radio = "Male";
            }else if(r2.isSelected()){
                radio = "Female";
            }
            String gender = radio;
            String idpass = tfid.getText();
            String country = tfcountry.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String password = tfpsw.getText();

            try {
                Conn con = new Conn();
                ps = con.c.prepareStatement("insert into customerAccount(name, gender, id_or_passport, country, date_of_birth, phone, email, password)values(?,?,?,?,?,?,?,?)");
                ps.setString(1,name);
                ps.setString(2,gender);
                ps.setString(3,idpass);
                ps.setString(4,country);
                ps.setString(5,dob);
                ps.setString(6,phone);
                ps.setString(7,email);
                ps.setString(8,password);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Account Registered Successfully");
                setVisible(false);
                new Login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            new WelcomeScreen();
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new Register();
    }
}