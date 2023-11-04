/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserScope;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeScreen extends JFrame implements ActionListener {
    JButton loginButton, registerButton;

    public WelcomeScreen() {
        // Create and customize buttons
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.addActionListener(this);

        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(120, 40));
        registerButton.addActionListener(this);

        // Create a panel to hold the buttons using GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Login button on the left
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 20); // Add some spacing between the buttons
        buttonPanel.add(loginButton, gbc);

        gbc.gridx = 1; // Register button on the right
        gbc.insets = new Insets(0, 20, 0, 0); // Add some spacing between the buttons
        buttonPanel.add(registerButton, gbc);

        // Create the main content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(buttonPanel, BorderLayout.CENTER);

        // Set up the frame
        add(contentPanel);
        setTitle("Welcome Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 120); // Adjusted size to accommodate the buttons
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            new Login();
            setVisible(false);
        } else if (ae.getSource() == registerButton) {
            new Register();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new WelcomeScreen();
    }
}