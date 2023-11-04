/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminScope;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AdminMain extends JFrame implements ActionListener{
       
    public AdminMain(){
        
        
        setSize(1540, 850);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/main.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        JMenuBar mb = new JMenuBar();
        
        // room menu
        JMenu rooms = new JMenu("Rooms");
        rooms.setForeground(Color.BLUE);
        mb.add(rooms);
        
        //adding rooms
        JMenuItem addRooms = new JMenuItem("Add Rooms");
        addRooms.setBackground(Color.WHITE);
        addRooms.addActionListener(this);
        rooms.add(addRooms);
        
        //viewing rooms
        JMenuItem viewRooms = new JMenuItem("View Rooms");
        viewRooms.setBackground(Color.WHITE);
        viewRooms.addActionListener(this);
        rooms.add(viewRooms);
        
        //customer booking
        JMenu customerBooking = new JMenu("Customer Booking");
        customerBooking.setForeground(Color.BLUE);
        mb.add(customerBooking);
        
        JMenuItem viewBooking = new JMenuItem("View Booking");
        viewBooking.setBackground(Color.WHITE);
        viewBooking.addActionListener(this);
        customerBooking.add(viewBooking);
        
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
        }else if(msg.equals("Add Rooms")){
            new AddRoom();
        }else if(msg.equals("View Rooms")){
            new Room();
        }else if(msg.equals("View Booking")){
            new ViewBooking();
        }else if(msg.equals("About")){
            new About();
        }else if(msg.equals("Sign Out")){
            setVisible(false);
            new Login();
        }
    }
    
    public static void main(String[] args){
        new AdminMain();
    }
}