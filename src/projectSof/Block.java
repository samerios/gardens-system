package projectSof;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.swing.JOptionPane;

public class Block {
	Manager manager;//belong manager
	ArrayList<Kindergarden> gardens;//belong gardens
	private int id;//block id
	private String name;//block name
	public Block() {}
	
	//copy constructor used to copy data from database to array lists in system class 
	public Block(int id, String name, int managerId) {
		this.id = id;
		this.name = name;
		this.manager = new Manager();
		this.manager.setId(managerId);
		this.gardens = new ArrayList<Kindergarden>();
	}
	
	//constructor used to insert new assistant in database
	public Block(String name, Manager manager) {
		setName(name);
		try {
			Connection conn = connClass.getConn();
			PreparedStatement preparedStmt = conn.prepareStatement("insert into block (name,managerid) values(?,?)",Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, name);
			preparedStmt.setInt(2, 0);
			preparedStmt.execute();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
			conn.close();
			new System();// update all array lists
			// if there are database problems throw exceptions
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "There is database problem try again later", "error message: " + "exit",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "sql error connection", "error message: " + "exit",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	//copy constructor
	public Block(Block b) {
		this.manager = b.manager;
		this.gardens = b.gardens;
		this.id = b.id;
		this.name = b.name;
	}

	//////

	///// id get&&set
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	////////////////

	//// name get&&set
	public String gettName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	///////////

	//// manager get&&set
	public Manager getManager() {
		return this.manager;
	}
	public boolean setManager(Manager m) {
		if (this.manager != m) {
			if (this.manager != null)
				this.manager.setBlock(null);
			this.manager = m;
			this.manager.setBlock(this);
			return true;
		}
		return false;
	}
	////////

	//// add garden
	public void addGarden(Kindergarden g) {
		this.gardens.add(g);
	}
	//////////

	/////
	// to string
	public String toString() {
		return "id: " + this.id + "  name: " + this.name + " manager of block: " + this.manager.getFirstName() + " "
				+ this.manager.getLastName() + " start date:" + this.manager.getStartDate().toString();
	}
	/////////////

}