/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserScope;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import AdminScope.About;


public class UserMain extends JFrame implements ActionListener{
       
    private String userEmail;
    public UserMain(String uemail){
        
        this.userEmail = uemail;
        
        setSize(1540, 850);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        JMenuBar mb = new JMenuBar();
        
        
        // profile
        JMenu profile = new JMenu("Profile");
        profile.setForeground(Color.BLUE);
        mb.add(profile);

        JMenuItem viewProfile = new JMenuItem("View Profile");
        viewProfile.setBackground(Color.WHITE);
        viewProfile.addActionListener(this);
        profile.add(viewProfile);
       
        JMenu booking = new JMenu("Booking");
        booking.setForeground(Color.BLUE);
        mb.add(booking);
        
        JMenuItem takeBooking = new JMenuItem("Take a Booking");
        takeBooking.setBackground(Color.WHITE);
        takeBooking.addActionListener(this);
        booking.add(takeBooking);
        
        JMenuItem cancelBooking = new JMenuItem("Cancel Booking");
        cancelBooking.setBackground(Color.WHITE);
        cancelBooking.addActionListener(this);
        booking.add(cancelBooking);
        
        JMenuItem viewBooking = new JMenuItem("View Booking");
        viewBooking.setBackground(Color.WHITE);
        viewBooking.addActionListener(this);
        booking.add(viewBooking);
        
        // about
        JMenu about = new JMenu("About");
        about.setForeground(Color.BLUE);
        mb.add(about);
        
        JMenuItem ab = new JMenuItem("About");
        ab.setBackground(Color.WHITE);
        ab.addActionListener(this);
        about.add(ab);
        
        JMenu logout = new JMenu("Log Out");
        logout.setForeground(Color.BLUE);
        mb.add(logout);
        
        JMenuItem lo = new JMenuItem("Sign Out");
        lo.setBackground(Color.WHITE);
        lo.addActionListener(this);
        logout.add(lo);
        
        // exit
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.RED);
        mb.add(exit);
        
        JMenuItem ex = new JMenuItem("Exit");
        ex.setBackground(Color.WHITE);
        ex.addActionListener(this);
        exit.add(ex);
        
        setJMenuBar(mb);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        String msg = ae.getActionCommand();
        
        if (msg.equals("Exit")){
            setVisible(false);
        }else if(msg.equals("About")){
            new About();
        }else if(msg.equals("Take a Booking")){
            new Booking(userEmail);
        }else if(msg.equals("Cancel Booking")){
            new CancelBooking(userEmail);
        }else if(msg.equals("View Booking")){
            new ViewBooking(userEmail);
        }else if(msg.equals("View Profile")){
            new Profile(userEmail);
        }else if(msg.equals("Sign Out")){
            setVisible(false);
            new Login();
        }
    }
    
    public static void main(String[] args){
        new UserMain("1831009@au.edu");
    }
    
}
