package projectSof;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import projectSof.System;

public class addBlock implements ActionListener {

	JFrame f;
	String myblocks[][] = null;// blocks array to save blocks details
	String col[] = { "ID", "NAME", "manager" };// the titles table
	private JButton back = new JButton("back");// back button
	// labels
	private JLabel lblmglname = new JLabel("manager last name");
	private JLabel lbladdblock = new JLabel("Add new Block");
	private JLabel lblblockname = new JLabel("block name");
	private JLabel lblmgfname = new JLabel("manager first name");
	private JLabel lblmggender = new JLabel("manager gender");
	private JLabel lblmgstartdate = new JLabel("manager start date");
	private JLabel titletblgardens = new JLabel("blocks List Details");
	//
	private JTextField txtblockname = new JTextField();// block name text
	private JTextField txtmgfname = new JTextField();// manager of block first name text
	private JTextField txtmglname = new JTextField();// manager of block last name text
	String[] gender = { "Male", "Female" };// manager of block gender
	private JComboBox blockcombo = new JComboBox(gender);// gender comboBox
	private JTextField txtmgstartdate = new JTextField();// manager start date text
	//
	JPanel pnladdblock = new JPanel();// add new block panel
	private JButton add = new JButton("add");// add button
	private JPanel details1 = new JPanel();// block details panel
	private JTable myTable1 = new JTable();// block details table
	private JScrollPane sp1 = new JScrollPane();// scrolPane to the table

	// constructor
	public addBlock() {
		createMyWindow(); // create the window function
		setLocation(); // set location function
		addComponents(); // add components function
		actionEventFunc(); // action event function
	}

	public void createMyWindow() {
		// put into string "my blocks" all blocks details
		int k = 0;
		System s = new System();
		myblocks = new String[s.blocks.size()][3];
		for (int i = 0; i < s.blocks.size(); i++) {
			k = 0;
			myblocks[i][k++] = "" + s.blocks.get(i).getId();
			myblocks[i][k++] = s.blocks.get(i).gettName();
			myblocks[i][k++] = "" + s.blocks.get(i).manager.getFirstName() + " "
					+ s.blocks.get(i).manager.getLastName();
		}
		myTable1 = new JTable(myblocks, col);// put the values of string into the table
		myTable1.getTableHeader().setReorderingAllowed(false);// to lock columns
		myTable1.setEnabled(false);// to lock rows
		sp1 = new JScrollPane(myTable1);// add table to scrolPane
		// insert frame and his properties
		f = new JFrame();
		f.setTitle("Add New Block");
		f.setBounds(0, 0, 1000, 700);
		f.getContentPane().setLayout(null);
		f.getContentPane().setBackground(Color.CYAN);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		pnladdblock.setBackground(Color.GREEN);
		pnladdblock.setLayout(null);
		details1.setLayout(new BorderLayout());
	}

	public void setLocation() {
		back.setBounds(30, 620, 80, 30);
		details1.setBounds(500, 150, 400, 200);
		pnladdblock.setBounds(30, 55, 360, 200);
		lbladdblock.setBounds(30, 30, 150, 30);
		lblblockname.setBounds(10, 0, 200, 50);
		txtblockname.setBounds(130, 15, 150, 20);
		lblmgfname.setBounds(10, 25, 200, 50);
		txtmgfname.setBounds(130, 40, 150, 20);
		lblmglname.setBounds(10, 50, 200, 50);
		txtmglname.setBounds(130, 65, 150, 20);
		lblmggender.setBounds(10, 75, 200, 50);
		blockcombo.setBounds(130, 90, 150, 20);
		lblmgstartdate.setBounds(10, 100, 200, 50);
		txtmgstartdate.setBounds(130, 115, 150, 20);
		add.setBounds(150, 180, 90, 20);
		titletblgardens.setBounds(650, 120, 200, 40);
	}

	public void addComponents() {
		details1.add(sp1);
		pnladdblock.add(lblblockname);
		pnladdblock.add(txtblockname);
		pnladdblock.add(lblmgfname);
		pnladdblock.add(txtmgfname);
		pnladdblock.add(lblmglname);
		pnladdblock.add(txtmglname);
		pnladdblock.add(lblmggender);
		pnladdblock.add(blockcombo);
		pnladdblock.add(lblmgstartdate);
		pnladdblock.add(txtmgstartdate);
		pnladdblock.add(add);
		f.add(pnladdblock);
		f.add(lbladdblock);
		f.add(details1);
		f.add(back);
		f.add(titletblgardens);
	}

	public void actionEventFunc() {
		back.addActionListener(this);
		add.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		// if click button equals back then hide this page and return to manager page
		if (ev.getSource() == back) {
			f.dispose();
			new managerPage();

		}
		// if click button equals add then
		if (ev.getSource() == add) {
			// if all textFields is not empty then
			if (!txtblockname.getText().isEmpty() && !txtmgfname.getText().isEmpty() && !txtmglname.getText().isEmpty()
					&& !txtmgstartdate.getText().isEmpty()) {
				boolean res = true, exist = false;// to check input
				// if block name all chars is letters continue
				try {
					char[] chars = txtblockname.getText().toCharArray();
					for (char c : chars) {
						if (!Character.isLetter(c))
							res = false;
						if (res == false)// else throw exception
							throw new Exception();
						// if block name exist in system then throw exception
						System s = new System();
						for (int i = 0; i < s.blocks.size(); i++) {
							if (s.blocks.get(i).gettName().equals(txtblockname.getText().toString()))
								exist = true;
						}
						if (exist == true)
							throw new Exception();// if true throw exception

					}
				} catch (Exception e) {// show error message
					if (res == false) {
						txtblockname.setText("");
						JOptionPane.showMessageDialog(null, "block name cannot contain digits or special characters",
								"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					}

					else if (exist == true) {
						txtblockname.setText("");// show error message
						JOptionPane.showMessageDialog(null, "block name exist into the system",
								"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					}
					return;
				}

				DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {// if date format is invalid then throw parse exception
					date = simpleDateFormat.parse(txtmgstartdate.getText());
				} catch (ParseException e) {// show error message
					JOptionPane.showMessageDialog(null, "set date format yyyy-MM-dd ", "Date error" + "exit",
							JOptionPane.INFORMATION_MESSAGE);
					txtmgstartdate.setText("");
					return;

				}

				try {// if first name all chars is letters continue
					char[] chars = txtmgfname.getText().toCharArray();
					res = true;
					for (char c : chars) {
						if (!Character.isLetter(c))
							res = false;
						if (res == false)// else throw exception
							throw new Exception();
					}
				} catch (Exception e) {// show error message
					txtmgfname.setText("");
					JOptionPane.showMessageDialog(null,
							"manager first name cannot contain digits or special characters",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				try {// if first name all chars is letters continue
					char[] chars = txtmglname.getText().toCharArray();
					res = true;
					for (char c : chars)
						if (!Character.isLetter(c))
							res = false;
					if (res == false)// else throw exception
						throw new Exception();
				} catch (Exception e) {// show error message
					txtmglname.setText("");
					JOptionPane.showMessageDialog(null, "manager last name cannot contain digits or special characters",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				System s = new System();
				Block b = new Block(txtblockname.getText(), new Manager());// call block constructor and insert new
																			// block in database
				Manager m = new Manager(txtmgfname.getText(), txtmglname.getText(), // call manager constructor and
																					// insert new
																					// block manager in database
						blockcombo.getSelectedItem().toString(), date, b);
				// update block set manager id is equal manager id inserted
				try {
					Connection conn = connClass.getConn();
					PreparedStatement preparedStmt = conn.prepareStatement("UPDATE block SET managerid=? WHERE id=?");
					preparedStmt.setInt(1, m.getId());
					preparedStmt.setInt(2, b.getId());
					preparedStmt.execute();
					conn.close();
					new System();// update all array lists
					// if there are database problems throw exceptions
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "There is database problem try again later",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "sql error connection", "error message: " + "exit",
							JOptionPane.INFORMATION_MESSAGE);
				}
				txtblockname.setText("");
				txtmgfname.setText("");
				txtmglname.setText("");
				txtmgstartdate.setText("");
				// show successfully add message
				JOptionPane.showMessageDialog(null, "block add succesfully ", " " + "exit",
						JOptionPane.INFORMATION_MESSAGE);
				// update frame (show inserted row in table)
				f.dispose();
				new addBlock();

			} else {// if one or more text fields is empty show message
				JOptionPane.showMessageDialog(null, "fill all fields ", " " + "exit", JOptionPane.INFORMATION_MESSAGE);

			}
		}
	}

}
