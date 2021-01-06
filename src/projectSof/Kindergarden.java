package projectSof;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.swing.JOptionPane;

public class Kindergarden {

	Block block;// belong block
	Manager manager;// belong manager
	ArrayList<Assistent> assistents;// belong assistants
	ArrayList<Kid> kids;// belong kids
	private int id;// garden id
	private String name;// garden name
	private int capacity;// garden capacity

	public Kindergarden() {
	}

	// copy constructor used to copy data from database to array lists in system
	// class
	public Kindergarden(int id, String name, int capacity, int bid) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.manager = new Manager();
		this.block = new Block();
		this.block.setId(bid);
		this.assistents = new ArrayList<Assistent>();
		this.kids = new ArrayList<Kid>();
	}
	//

	// constructor used to insert new garden in database
	public Kindergarden(String name, Block block) {
		setName(name);
		capacity = 0;
		this.block = new Block(block);
		System s = new System();
		try {
			Connection conn = connClass.getConn();
			PreparedStatement preparedStmt = conn.prepareStatement(
					"insert into kindergarden (name,capacity,blockid ) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, name);
			preparedStmt.setInt(2, capacity);
			preparedStmt.setInt(3, block.getId());
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
	//
	
	// copy constructor
	public Kindergarden(Kindergarden k) {
		this.block = k.block;
		this.manager = k.manager;
		this.assistents = k.assistents;
		this.kids = k.kids;
		this.id = k.id;
		this.name = k.name;
		this.capacity = k.capacity;

	}
	////////////

	///// id get
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	////////////////

	///// name get&&set
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/////////////

	///// Capacity get
	public int getCapacity() {
		return this.capacity;
	}
	//////////

	////manager get&&set
	public Manager getManager() {
		return this.manager;
	}

	public boolean setManager(Manager m) {
		if (manager != m) {
			if (manager != null)
				manager.setKindergarden(null);
			manager = m;
			this.manager.setKindergarden(this);
			return true;
		}
		return false;
	}
	////////

	/// assistents get&&set
	public Collection<Assistent> getAssistents() {
		return assistents;

	}

	public void addAssistent(Assistent a) {
		this.assistents.add(a);
	}
	///////

	/// block set&&get
	public Block getBlock() {
		return this.block;
	}

	public boolean setBlock(Block b) {
		if (block != b) {
			this.block = b;
			if (this.block != null)
				this.block = new Block(b);
			return true;
		}
		return false;
	}
	/////////////

	/// kids get&&set
	public ArrayList<Kid> getKids() {
		return kids;

	}

	public void addKid(Kid k) {
			this.kids.add(k);
	}
	//////


	// to string
	public String toString() {
		return "id: " + this.id + " garden name: " + this.name + " capacity: " + this.capacity + " manager of garden: "
				+ this.manager.getFirstName() + " " + this.manager.getLastName() + " block:" + this.block.toString();
	}
	/////////////
}