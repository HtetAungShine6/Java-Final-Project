/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.*;

public class Conn {
    
    public Connection c;
    public Statement s;
    public String username = "b61zb6b7tt35cf7e9ppn";
    public String password = "pscale_pw_hHxqhmQ2GtsVmrCzELvxD3oFFIY6DZpqH8u4FEmzcAK"; 
//    public String url = "jdbc:mysql://localhost:3306/hotelbookingsystem"; //for local database
    public String url = "jdbc:mysql://aws.connect.psdb.cloud/bookingsystem?sslMode=VERIFY_IDENTITY"; //online database API
    
    public Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(url,username,password);
            s = c.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}