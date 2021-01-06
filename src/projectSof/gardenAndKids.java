package projectSof;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import projectSof.System;

public class gardenAndKids implements ActionListener {

	JFrame f;
	String garden[][] = null;// garden details array
	String col[] = { "ID", "NAME", "CAPACITY", "BLOCK NAME" };// columns titles of table garden
	String data[][] = null;// kids details array
	String column[] = { "ID", "FIRST NAME", "LAST NAME", "GENDER", "DATE OF BIRTH" };// columns titles of table kids
	String asistent[][] = null;// assistants details array
	String ascol[] = { "ID", "FIRST NAME", "LAST NAME", "GENDER", "WORK HOURS", "PER HOUR" };// columns titles of table
																								// assistants
	private static int gardenid;// save garden id
	private JButton back = new JButton("back");// back button
	//
	private JLabel lbldeletekid = new JLabel("Remove kid");// remove kid title
	private JLabel lblkidid = new JLabel("ID");// remove kid id label
	private JTextField txtdeletekid = new JTextField();// remove kid id text
	private JPanel pnldeletekid = new JPanel();// remove kid panel
	private JButton btndeletekid = new JButton("delete kid");// remove kid button
	//
	private JLabel lbldeleteassis = new JLabel("Remove Assistent");// remove assistant title
	private JLabel lblidassis = new JLabel("ID");// remove assistant id label
	private JTextField txtdeleteassis = new JTextField();// remove assistant id text
	private JPanel pnldeleteassis = new JPanel();// remove assistant panel
	private JButton btndeleteassis = new JButton("delete Assistent");// remove assistant button
	//
	private JPanel details1 = new JPanel();// kids details panel
	private JTable myTable1 = new JTable();// kids details table
	private JScrollPane sp1 = new JScrollPane();// kids add table to scrolPane
	//
	private JPanel details2 = new JPanel();// garden details panel
	private JTable myTable2 = new JTable();// garden details table
	private JScrollPane sp2 = new JScrollPane();// garden add table to scrolPane
	//
	private JPanel details3 = new JPanel();// assistants details panel
	private JTable myTable3 = new JTable();// assistants details table
	private JScrollPane sp3 = new JScrollPane();// assistants add table to scrolPane
	//
	private JLabel titletblgardens = new JLabel("Garden Details");// garden table title
	private JLabel titletblkids = new JLabel("Kids List");// kids table title
	private JLabel titletblassisitents = new JLabel("Assistents List");// assistants table title

	// constructor
	public gardenAndKids(int gid) {
		gardenid = gid;/// save into gardenid the id of gid
		createMyWindow(); // create the window function
		setLocation(); // set location function
		addComponents(); // add components function
		actionEventFunc(); // action event function
		// design
		titletblgardens.setFont(new Font("Serif", Font.PLAIN, 18));
		titletblkids.setFont(new Font("Serif", Font.PLAIN, 18));
		titletblassisitents.setFont(new Font("Serif", Font.PLAIN, 18));
	}

	public void createMyWindow() {
		// get the index of garden id from array list in system class
		System s = new System();
		int ind = -1, k = 0;
		for (int i = 0; i < s.gardens.size(); i++) {
			if (s.gardens.get(i).getId() == gardenid)
				ind = i;
		}
		// save all details of kids into the string
		data = new String[s.gardens.get(ind).kids.size()][5];
		int size = s.gardens.get(ind).kids.size();
		for (int i1 = 0; i1 < size; i1++) {
			k = 0;
			data[i1][k++] = "" + s.gardens.get(ind).kids.get(i1).getId();
			data[i1][k++] = "" +s.gardens.get(ind).kids.get(i1).getFirstName();
			data[i1][k++] = "" + s.gardens.get(ind).kids.get(i1).getLastName();
			data[i1][k++] = "" + s.gardens.get(ind).kids.get(i1).getGender();
			data[i1][k++] = "" + s.gardens.get(ind).kids.get(i1).getBirthdate();
		}
		///

		// save all details of garden into the string
		garden = new String[1][4];
		k = 0;
		garden[0][k++] = "" + s.gardens.get(ind).getId();
		garden[0][k++] = s.gardens.get(ind).getName();
		garden[0][k++] = "" + s.gardens.get(ind).getCapacity();
		garden[0][k++] = "" + s.gardens.get(ind).block.gettName();
		///

		// save all details of assistants into the string
		asistent = new String[s.gardens.get(ind).assistents.size()][6];
		size = s.gardens.get(ind).assistents.size();
		for (int i1 = 0; i1 < size; i1++) {
			k = 0;
			asistent[i1][k++] = "" + s.gardens.get(ind).assistents.get(i1).getId();
			asistent[i1][k++] = s.gardens.get(ind).assistents.get(i1).getFirstName();
			asistent[i1][k++] = "" + s.gardens.get(ind).assistents.get(i1).getLastName();
			asistent[i1][k++] = "" + s.gardens.get(ind).assistents.get(i1).getGender();
			asistent[i1][k++] = "" + s.gardens.get(ind).assistents.get(i1).getWorkhours();
			asistent[i1][k++] = "" + s.gardens.get(ind).assistents.get(i1).getSalaryPerHour();
		}
		//

		myTable1 = new JTable(data, column);// put the values of string "data" into the table kids
		myTable1.getTableHeader().setReorderingAllowed(false);// to lock columns
		myTable1.setEnabled(false);// to lock rows
		sp1 = new JScrollPane(myTable1); // add kids table to scrolPane

		myTable2 = new JTable(garden, col);// put the values of string "gardens" into the table gardens
		myTable2.getTableHeader().setReorderingAllowed(false);// to lock columns
		myTable2.setEnabled(false);// to lock rows
		sp2 = new JScrollPane(myTable2);// add gardens table to scrolPane

		myTable3 = new JTable(asistent, ascol);// put the values of string "asistent" into the table asistents
		myTable3.getTableHeader().setReorderingAllowed(false);// to lock columns
		myTable3.setEnabled(false);// to lock rows
		sp3 = new JScrollPane(myTable3);// add asistents table to scrolPane

		// insert frame and his properties
		f = new JFrame();
		f.setTitle("Kids in Garden");
		f.setBounds(0, 0, 1000, 700);
		f.getContentPane().setLayout(null);
		f.getContentPane().setBackground(Color.CYAN);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		pnldeletekid.setBackground(Color.RED);
		pnldeletekid.setLayout(null);
		pnldeleteassis.setBackground(Color.RED);
		pnldeleteassis.setLayout(null);
		details1.setLayout(new BorderLayout());
		details2.setLayout(new BorderLayout());
		details3.setLayout(new BorderLayout());
	}

	public void setLocation() {
		back.setBounds(30, 620, 80, 30);
		details1.setBounds(450, 150, 520, 200);
		details2.setBounds(450, 30, 520, 50);
		details3.setBounds(450, 390, 520, 200);
		lbldeletekid.setBounds(30, 5, 300, 30);
		pnldeletekid.setBounds(30, 30, 360, 50);
		lblkidid.setBounds(10, 0, 300, 50);
		txtdeletekid.setBounds(60, 20, 150, 20);
		btndeletekid.setBounds(215, 20, 90, 20);
		lbldeleteassis.setBounds(30, 125, 200, 30);
		pnldeleteassis.setBounds(30, 150, 360, 50);
		lblidassis.setBounds(10, 0, 300, 50);
		txtdeleteassis.setBounds(60, 20, 150, 20);
		btndeleteassis.setBounds(215, 20, 135, 20);
		titletblgardens.setBounds(650, 0, 200, 40);
		titletblkids.setBounds(650, 120, 200, 40);
		titletblassisitents.setBounds(650, 360, 200, 40);
	}

	public void addComponents() {
		details1.add(sp1);
		details2.add(sp2);
		details3.add(sp3);
		pnldeletekid.add(lblkidid);
		pnldeletekid.add(txtdeletekid);
		pnldeletekid.add(btndeletekid);
		f.add(pnldeletekid);
		pnldeleteassis.add(lblidassis);
		pnldeleteassis.add(txtdeleteassis);
		pnldeleteassis.add(btndeleteassis);
		f.add(pnldeleteassis);
		f.add(lbldeleteassis);
		f.add(lbldeletekid);
		f.add(details1);
		f.add(details2);
		f.add(details3);
		f.add(back);
		f.add(titletblgardens);
		f.add(titletblkids);
		f.add(titletblassisitents);
	}

	public void actionEventFunc() {
		back.addActionListener(this);
		btndeletekid.addActionListener(this);
		btndeleteassis.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		// if click button equals back then hide this page and return to manager page
		if (ev.getSource() == back) {
			f.dispose();
			new managerPage();
		}
		// if click button equals btndeletekid then
		if (ev.getSource() == btndeletekid) {
			// if kid id text is not empty then
			if (!txtdeletekid.getText().isEmpty()) {
				try {// if id text all chars is digits continue

					char[] chars = txtdeletekid.getText().toCharArray();
					boolean res = true;
					for (char c : chars)
						if (!Character.isDigit((c)))
							res = false;
					if (res == false)// else throw exception
						throw new Exception();
				} catch (Exception e) {// show error message
					txtdeletekid.setText("");
					JOptionPane.showMessageDialog(null, "id cannot contain chars or special characters",
							"error message: " + " ", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				System s = new System();
				int ind = -1;
				// save into ind the index of selected garden in array list
				for (int i = 0; i < s.gardens.size(); i++) {
					if (s.gardens.get(i).getId() == gardenid)
						ind = i;
				}
				// search into the gardens array list from system class if kid id is exist if
				// yes continue
				int size = s.gardens.get(ind).kids.size();
				boolean res = false;
				for (int i1 = 0; i1 < size; i1++) {
					if (s.gardens.get(ind).kids.get(i1).getId() == Integer.parseInt(txtdeletekid.getText()))
						res = true;
				}
				if (res == false) {
					txtdeletekid.setText("");// if kid id not exist then show error message
					JOptionPane.showMessageDialog(null, "id not found in system", "error message: " + "exit",
							JOptionPane.INFORMATION_MESSAGE);
					return;

				} else {// if kid id is exists then connect to data base and delete the row and update
						// garden capacity and update all
						// array lists from system class
					try {
						Connection conn = connClass.getConn();
						PreparedStatement preparedStmt = conn.prepareStatement("DELETE FROM kid WHERE id=?");
						preparedStmt.setInt(1, Integer.parseInt(txtdeletekid.getText().toString()));
						preparedStmt.execute();

						int index = -1;
						for (int i = 0; i < s.gardens.size(); i++)
							if (s.gardens.get(i).getId() == gardenid)
								index = i;

						int capacity = s.gardens.get(index).getCapacity() - 1;
						preparedStmt = conn.prepareStatement("UPDATE kindergarden SET capacity=? WHERE id=?");
						preparedStmt.setInt(1, capacity);
						preparedStmt.setInt(2, s.gardens.get(index).getId());
						preparedStmt.execute();
						conn.close();
						new System();// update all array lists
						// if there are database problems throw exceptions
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "There is database problem try again later",
								"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
						return;
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "sql error connection", "error message: " + "exit",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					txtdeletekid.setText("");
					// show successfully add message
					JOptionPane.showMessageDialog(null, "kid deleted succefully", "system message: " + "exit",
					JOptionPane.INFORMATION_MESSAGE);
					//refresh the frame
					f.dispose();
					new gardenAndKids(gardenid);
				}
			} else {//if kid id text is empty show message
				JOptionPane.showMessageDialog(null, "please set id ", "error: " + "exit",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		
		// if click button equals btndeleteassis then
		if (ev.getSource() == btndeleteassis) {
			// if assistant id text is not empty then
			if (!txtdeleteassis.getText().isEmpty()) {
				try {// if id text all chars is digits continue
					char[] chars = txtdeleteassis.getText().toCharArray();
					boolean res = true;
					for (char c : chars)
						if (!Character.isDigit((c)))
							res = false;
					if (res == false)// else throw exception
						throw new Exception();
				} catch (Exception e) {//show error message
					txtdeleteassis.setText("");
					JOptionPane.showMessageDialog(null, "id cannot contain chars or special characters",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// save into ind the index of selected garden in array list
				System s = new System();
				int ind = -1;
				for (int i = 0; i < s.gardens.size(); i++) {
					if (s.gardens.get(i).getId() == gardenid)
						ind = i;
				}
				// search into the assistents array list from system class if assistant id is exist if
				// yes continue
				int size = s.gardens.get(ind).assistents.size();
				boolean res = false;
				for (int i1 = 0; i1 < size; i1++) {
					if (s.gardens.get(ind).assistents.get(i1).getId() == Integer.parseInt(txtdeleteassis.getText()))
						res = true;
				}
				if (res == false) {
					txtdeleteassis.setText("");
					JOptionPane.showMessageDialog(null, "id not found in system", "error message: " + "exit",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} 
				else {// if assistent id is exists then connect to data base and delete the row and update
					// array lists from system class
					try {
						Connection conn = connClass.getConn();
						PreparedStatement preparedStmt = conn.prepareStatement("DELETE FROM assistent WHERE id=?");
						preparedStmt.setInt(1, Integer.parseInt(txtdeleteassis.getText().toString()));
						preparedStmt.execute();
						conn.close();
						new System();// update all array lists
						// if there are database problems throw exceptions
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "There is database problem try again later",
								"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
						return;
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "sql error connection", "error message: " + "exit",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					txtdeleteassis.setText("");
					// show successfully add message
					JOptionPane.showMessageDialog(null, "assistent deleted succefully", "error message: " + "exit",
						JOptionPane.INFORMATION_MESSAGE);
					//refresh the frame
					f.dispose();
					new gardenAndKids(gardenid);
				}
			} else {//if assistant id text is empty show message
				JOptionPane.showMessageDialog(null, "please set id ", "error: " + "exit",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

}
