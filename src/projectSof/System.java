package projectSof;

import java.util.*;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//system class load all data from data base to array lists
public class System {

	ArrayList<Kid> kids;
	ArrayList<Block> blocks;
	ArrayList<Kindergarden> gardens;
	ArrayList<Assistent> assistents;
	ArrayList<Manager> gardenManagers;
	ArrayList<Manager> blockManagers;

	// after load all data to all array lists we need to sort them then we can call
	// constructor to start working
	// load all data to all array list

	// constructor
	System() {
		kids = new ArrayList<Kid>();
		blocks = new ArrayList<Block>();
		gardens = new ArrayList<Kindergarden>();
		assistents = new ArrayList<Assistent>();
		gardenManagers = new ArrayList<Manager>();
		blockManagers = new ArrayList<Manager>();

		String query, name;
		int id, capacity;
		try {// load all kids details from database to data members and call the constructor
				// of kid class then add object
				// to kids array list
			Connection conn = connClass.getConn();
			Statement stmt;
			ResultSet rs;

			stmt = conn.createStatement();
			query = "SELECT * from kid";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String gender = rs.getString("gender");
				Date dateofbirth = rs.getDate("dateofbirth");
				int gardenid = rs.getInt("gardenid");
				Kid k = new Kid(id, firstname, lastname, gender, dateofbirth, gardenid);
				kids.add(k);
			}
			rs.close();

			// load all assistant details from database to data members and call the
			// constructor of
			// assistents class then add object to kids array list
			stmt = conn.createStatement();
			query = "SELECT * from assistent";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String gender = rs.getString("gender");
				int workhours = rs.getInt("workhours");
				double perhour = rs.getDouble("perhour");
				int gardenid = rs.getInt("gardenid");
				Assistent a = new Assistent(id, firstname, lastname, gender, workhours, perhour, gardenid);
				assistents.add(a);
			}
			rs.close();

			// load all garden managers details from database to data members and call the
			// constructor of
			// manager class then add object to gardenManagers array list
			stmt = conn.createStatement();
			query = "SELECT * from kindergardenmanager";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String gender = rs.getString("gender");
				Date startdate = rs.getDate("startdate");
				int gardenid = rs.getInt("gardenid");
				Manager mg = new Manager(id, firstname, lastname, gender, startdate, gardenid);
				gardenManagers.add(mg);
			}
			rs.close();

			// load all block managers details from database to data members and call the
			// constructor of
			// manager class then add object to blockManagers array list
			stmt = conn.createStatement();
			query = "SELECT * from blockmanager";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String gender = rs.getString("gender");
				Date startdate = rs.getDate("startdate");
				int blockid = rs.getInt("blockid");
				Manager mb = new Manager(id, firstname, lastname, gender, startdate, blockid, "");
				blockManagers.add(mb);
			}
			rs.close();

			
			// load all gardens details from database to data members and call the
			// constructor of
			// kindergarden class then add kids and assistants and garden manager to belong garden
			//then add object to gardens array list
			stmt = conn.createStatement();
			query = "SELECT * from kindergarden";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				capacity = rs.getInt("capacity");
				int blockid = rs.getInt("blockid");
				Kindergarden k1 = new Kindergarden(id, name, capacity, blockid);
				for (int i = 0; i < kids.size(); i++) {
					if (kids.get(i).garden.getId() == k1.getId()) {
						k1.kids.add(kids.get(i));
						kids.get(i).setKindergarden(k1);
					}
				}
				for (int i = 0; i < assistents.size(); i++) {
					if (assistents.get(i).garden.getId() == k1.getId()) {
						k1.assistents.add(assistents.get(i));
						assistents.get(i).setKindergarden(k1);
					}
				}
				for (int i = 0; i < gardenManagers.size(); i++) {
					if (gardenManagers.get(i).garden.getId() == k1.getId()) {
						k1.setManager(gardenManagers.get(i));
						gardenManagers.get(i).setKindergarden(k1);
					}
				}
				gardens.add(k1);

			}
			rs.close();

			
			// load all blocks details from database to data members and call the
			// constructor of
			// block class then add gardens and block manager to belong block
			//then add object to blocks array list
			stmt = conn.createStatement();
			query = "SELECT * from block";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				int managerid = rs.getInt("managerid");

				Block b1 = new Block(id, name, managerid);
				for (int i = 0; i < blockManagers.size(); i++) {
					if (blockManagers.get(i).getId() == b1.manager.id) {
						b1.setManager(blockManagers.get(i));
						blockManagers.get(i).setBlock(b1);
					}
				}
				for (int i = 0; i < gardens.size(); i++) {
					if (gardens.get(i).block.getId() == b1.getId()) {
						gardens.get(i).setBlock(b1);
						b1.gardens.add(gardens.get(i));

					}
				}
				blocks.add(b1);
			}
			rs.close();

			conn.close();
			// if there are database problems throw exceptions
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "There is database problem try again later", "error message: " + "exit",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "sql error connection", "error message: " + "exit",
					JOptionPane.INFORMATION_MESSAGE);

		}

	}

	public void main(String[] args) {

	}

}
