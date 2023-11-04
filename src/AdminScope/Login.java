/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminScope;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import Connection.Conn;

public class Login extends JFrame implements ActionListener{
    
    public JLabel username,password;
    public JTextField usernameTF;
    public JPasswordField passwordTF;
    public JButton login,cancel;

    public Login(){

        super("Admin Login");

        setLayout(null);

        username = new JLabel("Username");
        username.setBounds(40,20,100,30);
        add(username);
        
        password = new JLabel("Password");
        password.setBounds(40,70,100,30);
        add(password);
 
        usernameTF=new JTextField();
        usernameTF.setBounds(150,20,150,30);
        add(usernameTF);

        passwordTF=new JPasswordField();
        passwordTF.setBounds(150,70,150,30);
        add(passwordTF);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(350,10,150,150);
        add(l3);


        login = new JButton("Login");
        login.setBounds(40,140,120,30);
        login.setFont(new Font("serif",Font.BOLD,15));
        login.addActionListener(this);
        login.setBackground(Color.WHITE);
        login.setForeground(Color.BLACK);
        add(login);

        cancel=new JButton("Cancel");
        cancel.setBounds(180,140,120,30);
        cancel.setFont(new Font("serif",Font.BOLD,15));
        cancel.setBackground(Color.WHITE);
        cancel.setForeground(Color.BLACK);
        add(cancel);

        cancel.addActionListener(this);
        
        
        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
        setSize(600,300);
        setLocation(600,350);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==login){
        try{
            Conn c1 = new Conn();
            String u = usernameTF.getText();
            String v = passwordTF.getText();
            
            String q = "select * from admin_login where username='"+u+"' and password='"+v+"'";
            
            ResultSet rs = c1.s.executeQuery(q); 
            if(rs.next()){ 
                new AdminMain().setVisible(true);
                setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Invalid login");
                setVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        }else if(ae.getSource()==cancel){
            System.exit(0);
        }
    }
    public static void main(String[] arg){
        new Login();
    }
}
