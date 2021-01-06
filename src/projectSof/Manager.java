package projectSof;

import java.util.Date;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class Manager extends Person {

	Block block;//belong block
	Kindergarden garden;//belong garden
	private Date startDate;//start date work

	public Manager() {
	}
	// copy constructor used to copy data from database to array lists in system
	// class "block manager"
	public Manager(int id, String firstName, String lastName, String gender, Date startDate, int blockid, String b) {
		super(id, firstName, lastName, gender);
		this.startDate = startDate;
		this.block = new Block();
		this.block.setId(id);
	}

	// copy constructor used to copy data from database to array lists in system
		// class "garden manager"
	public Manager(int id, String firstName, String lastName, String gender, Date startDate, int gardenid) {
		super(id, firstName, lastName, gender);
		this.startDate = startDate;
		this.garden = new Kindergarden();
		this.garden.setId(gardenid);

	}

	// constructor used to insert new "block manager" in database
	public Manager(String firstName, String lastName, String gender, Date startDate, Block block) {
		super(firstName, lastName, gender);
		//startDate = new Date();
		java.util.Date utilStartDate = startDate;
		java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
		this.block = new Block(block);
		try {
			Connection conn = connClass.getConn();
			PreparedStatement preparedStmt = conn.prepareStatement(
					"insert into blockmanager (firstname,lastname,gender,startdate,blockid) values(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setString(3, gender);
			preparedStmt.setDate(4, (sqlStartDate));
			preparedStmt.setInt(5, block.getId());
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

	// constructor used to insert new "garden manager" in database
	public Manager(String firstName, String lastName, String gender, Date startDate, Kindergarden garden) {
		super(firstName, lastName, gender);
		// this.startDate=new Date();
		java.util.Date utilStartDate = startDate;
		java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
		this.garden = new Kindergarden(garden);
		try {
			Connection conn = connClass.getConn();
			PreparedStatement preparedStmt = conn.prepareStatement(
					"insert into kindergardenmanager (firstname,lastname,gender,startdate,gardenid) values(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setString(3, gender);
			preparedStmt.setDate(4, (sqlStartDate));
			preparedStmt.setInt(5, garden.getId());
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

	/////////////

	/////// start Date get&&set
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	///////////////

	//// Block get&&set
	public Block getBlock() {
		return this.block;
	}

	public boolean setBlock(Block b) {
		if (this.block != b) {
			this.block = b;
			if (this.block != null)
				this.block.setManager(this);
			return true;
		}
		return false;
	}
	////////

	//// garden get&&set
	public Kindergarden getKindergarden() {
		return this.garden;
	}

	public boolean setKindergarden(Kindergarden k) {
		if (this.garden != k) {
			this.garden = k;
			if (this.garden != null)
				this.garden.setManager(this);
			return true;
		}
		return false;
	}
	////////

	// to string
	public String toString() {
		return super.toString() + " block: " + this.block.gettName() + " " + this.garden.getName();
	}
	/////////////

}