package projectSof;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import projectSof.System;

public class BlockManagerDetails implements ActionListener {

	JFrame f;
	String blockManager[][] = null; // block manager details array
	String col[] = { "ID", "FIRST NAME", "LAST NAME", "GENDER", "start date", "blockname" };// columns titles of table
	private static int blockmanagerid;// save block manager id
	private JButton back = new JButton("back");// back button
	private JPanel details1 = new JPanel();// details panel
	private JTable myTable1 = new JTable();// details table
	private JScrollPane sp1 = new JScrollPane();// add table to scrolPane
	private JLabel titletblgardens = new JLabel("manager of block Details");// details title

	// constructor
	public BlockManagerDetails(int bmngid) {
		blockmanagerid = bmngid; /// save into blockmanagerid the id of bmngid
		createMyWindow(); // create the window function
		setLocation(); // set location function
		addComponents(); // add components function
		actionEventFunc(); // action event function
	}

	public void createMyWindow() {
		// get the index of block manager id from array list in system class
		System s = new System();
		int ind = -1;
		blockManager = new String[1][6];
		for (int i = 0; i < s.blockManagers.size(); i++) {
			if (s.blockManagers.get(i).getId() == blockmanagerid)
				ind = i;
		}
		// save all details of block manager into the string
		int k = 0, gid = -1;
		for (int i = 0; i < s.blocks.size(); i++) {
			if (s.blocks.get(i).getId() == s.blockManagers.get(ind).block.getId())
				gid = i;
		}
		blockManager[0][k++] = "" + s.blockManagers.get(ind).getId();
		blockManager[0][k++] = s.blockManagers.get(ind).getFirstName();
		blockManager[0][k++] = "" + s.blockManagers.get(ind).getLastName();
		blockManager[0][k++] = "" + s.blockManagers.get(ind).getGender();
		blockManager[0][k++] = "" + s.blockManagers.get(ind).getStartDate();
		blockManager[0][k++] = "" + s.blocks.get(gid).gettName();

		myTable1 = new JTable(blockManager, col);// put the values of string into the table
		myTable1.getTableHeader().setReorderingAllowed(false);// to lock columns
		myTable1.setEnabled(false);// to lock rows
		sp1 = new JScrollPane(myTable1); // add table to scrolPane

		// insert frame and his properties
		f = new JFrame();
		f.setTitle("Block Manager Details");
		f.setBounds(0, 0, 1000, 700);
		f.getContentPane().setLayout(null);
		f.getContentPane().setBackground(Color.CYAN);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		details1.setLayout(new BorderLayout());
	}

	public void setLocation() {
		back.setBounds(30, 620, 80, 30);
		details1.setBounds(200, 150, 620, 50);
		titletblgardens.setBounds(350, 120, 200, 40);
	}

	public void addComponents() {
		details1.add(sp1);
		f.add(details1);
		f.add(details1);
		f.add(back);
		f.add(titletblgardens);
	}

	public void actionEventFunc() {
		back.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		// if click button equals back then hide this page and return to manager page
		if (ev.getSource() == back) {
			f.dispose();
			new managerPage();

		}
	}

}
