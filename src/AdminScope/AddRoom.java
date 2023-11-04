/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminScope;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import Connection.Conn;

public class AddRoom extends JFrame implements ActionListener{

    public JPanel contentPane;
    public JTextField roomPrice,rooms;
    public JComboBox roomType; 
    public JLabel availabilitylbl;
    JButton b1,b2;

    public static void main(String[] args) {
        new AddRoom();
    }


    public AddRoom() {
        setBounds(450, 200, 1000, 450);
	contentPane = new JPanel();
	setContentPane(contentPane);
	contentPane.setLayout(null);
        
        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
                Image i3 = i1.getImage().getScaledInstance(500, 300,Image.SCALE_DEFAULT);
                ImageIcon i2 = new ImageIcon(i3);
                JLabel l15 = new JLabel(i2);
                l15.setBounds(400,30,500,370);
                add(l15);
        
        JLabel addRooms = new JLabel("Add Rooms");
        addRooms.setFont(new Font("Tahoma", Font.BOLD, 18));
	addRooms.setBounds(194, 10, 120, 22);
	contentPane.add(addRooms);

	JLabel roomNumber = new JLabel("Room Number");
	roomNumber.setForeground(new Color(25, 25, 112));
	roomNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
	roomNumber.setBounds(64, 85, 102, 22);
	contentPane.add(roomNumber);
        
        
        rooms = new JTextField();
	rooms.setBounds(174, 85, 156, 20);
        contentPane.add(rooms);

	JLabel availability = new JLabel("Availability");
	availability.setForeground(new Color(25, 25, 112));
	availability.setFont(new Font("Tahoma", Font.BOLD, 14));
	availability.setBounds(64, 160, 102, 22);
	contentPane.add(availability);
        
        availabilitylbl = new JLabel("Available");
        availabilitylbl.setForeground(new Color(25, 25, 112));
	availabilitylbl.setFont(new Font("Tahoma", Font.BOLD, 14));
	availabilitylbl.setBounds(176, 160, 154, 20);
	contentPane.add(availabilitylbl);

	JLabel price = new JLabel("Price");
	price.setForeground(new Color(25, 25, 112));
	price.setFont(new Font("Tahoma", Font.BOLD, 14));
	price.setBounds(64, 235, 102, 22);
	contentPane.add(price);
        
        this.roomPrice = new JTextField();
	this.roomPrice.setBounds(174, 235, 156, 20);
	contentPane.add(this.roomPrice);

        JLabel l5 = new JLabel("Bed Type");
	l5.setForeground(new Color(25, 25, 112));
	l5.setFont(new Font("Tahoma", Font.BOLD, 14));
	l5.setBounds(64, 310, 102, 22);
	contentPane.add(l5);

        roomType = new JComboBox(new String[] { "Single Room","Double Room","Standard Room","Twin Room","Triple Room","Quad Room","Suite","Theme Room","City View Room","Garden View Room"});
	roomType.setBounds(176, 310, 154, 20);
	contentPane.add(roomType);

	b1 = new JButton("Add");
	b1.addActionListener(this);
	b1.setBounds(64, 365, 111, 33);
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);
	contentPane.add(b1);

	b2 = new JButton("Back");
	b2.addActionListener(this);
	b2.setBounds(198, 365, 111, 33);
        b2.setBackground(Color.WHITE);
        b2.setForeground(Color.BLACK);
	contentPane.add(b2);

	
        contentPane.setBackground(Color.WHITE);
        setVisible(true);
    
    }
    
    public void actionPerformed(ActionEvent ae){
        PreparedStatement ps;
        try{
            if(ae.getSource() == b1){
                try{
                Conn con = new Conn();
                String room = rooms.getText();
                String available = availabilitylbl.getText();
                String price  = this.roomPrice.getText();
                String type = (String)roomType.getSelectedItem();
                ps = con.c.prepareStatement("insert into room(room_number, availability, price, room_type)values(?,?,?,?)");
                ps.setString(1, room);
                ps.setString(2,available);
                ps.setString(3, price);
                ps.setString(4, type);
		ps.executeUpdate();
		JOptionPane.showMessageDialog(null, "Room Successfully Added");
                this.setVisible(false);
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
            else if(ae.getSource() == b2){
                this.setVisible(false);
            }
        }catch(Exception eee){
            
        }
    }
}

