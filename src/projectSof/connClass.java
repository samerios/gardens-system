package projectSof;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import java.sql.DriverManager;
import java.sql.SQLException;
import static java.lang.System.out;
public class connClass {
	
	//this class used to make connection to database
public static Connection conn;
public static Connection getConn() throws ClassNotFoundException
{
	try{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kindergardensys?useSSL=false","root","");
		return conn;
	}catch(SQLException e){
		JOptionPane.showMessageDialog(null, "sql error connection", "error message: " + "exit",
				JOptionPane.INFORMATION_MESSAGE);
		return null;
	}
 } 
}