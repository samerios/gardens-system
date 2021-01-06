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

public class KidDetails implements ActionListener {

	JFrame f;
	String data[][] = null;//kid details array
	String column[] = { "ID", "FIRST NAME", "LAST NAME", "GENDER", "DATE OF BIRTH" };//columns titles of kid table 
	String garden[][] = null;//garden details array
	String col[] = { "ID", "NAME", "CAPACITY", "BLOCK NAME" };//columns titles of garden table 
	private static int kidid;//save kid id
	private JButton back = new JButton("back");//back button
	//
	private JPanel details1 = new JPanel();//kid details panel
	private JTable myTable1 = new JTable();//kid details table
	private JScrollPane sp1 = new JScrollPane();//add kid table to scrolPane
	//
	private JPanel details2 = new JPanel();//garden details panel
	private JTable myTable2 = new JTable();//garden details table
	private JScrollPane sp2 = new JScrollPane();//add garden table to scrolPane
	//
	private JLabel titletblgardens = new JLabel("Garden Details");//garden details title
	private JLabel titletblkids = new JLabel("Kid List");//kid details title
	
	// constructor
	public KidDetails(int kid) {
		kidid = kid;///save into kidid the id of kid 
		createMyWindow(); // create the window function
		setLocation(); // set location function
		addComponents(); // add components function
		actionEventFunc(); // action event function
	}

	public void createMyWindow() {
		//get the index of kid id from array list in system class
		System s = new System();
		int ind = -1,k=0;
		for (int i = 0; i < s.kids.size(); i++) {
			if (s.kids.get(i).getId() == kidid)
				ind = i;
		}
		//save all details of kid into the string "data"
		data = new String[1][5];
		k = 0;
		data[0][k++] = "" + s.kids.get(ind).getId();
		data[0][k++] = s.kids.get(ind).getFirstName();
		data[0][k++] = "" + s.kids.get(ind).getLastName();
		data[0][k++] = "" + s.kids.get(ind).getGender();
		data[0][k++] = "" + s.kids.get(ind).getBirthdate();
		//
		//save all details of garden into the string "garden"
		garden = new String[1][4];
		int gid = s.kids.get(ind).garden.getId();
		for (int i = 0; i < s.gardens.size(); i++) {
			if (s.gardens.get(i).getId() == gid)
				ind = i;
		}
		k = 0;
		garden[0][k++] = "" + s.gardens.get(ind).getId();
		garden[0][k++] = s.gardens.get(ind).getName();
		garden[0][k++] = "" + s.gardens.get(ind).getCapacity();
		garden[0][k++] = "" + s.gardens.get(ind).block.gettName();
		//
		myTable1 = new JTable(data, column);// put the values of string "data" into the kid table
		myTable1.getTableHeader().setReorderingAllowed(false);// to lock columns
		myTable1.setEnabled(false);// to lock rows
		sp1 = new JScrollPane(myTable1);// add kid table to scrolPane
		//
		myTable2 = new JTable(garden, col);// put the values of string "garden" into the garden table
		myTable2.getTableHeader().setReorderingAllowed(false);// to lock columns
		myTable2.setEnabled(false);// to lock rows
		sp2 = new JScrollPane(myTable2);// add garden table to scrolPane
		// insert frame and his properties
		f = new JFrame();
		f.setTitle("Kid Details");
		f.setBounds(0, 0, 1000, 700);
		f.getContentPane().setLayout(null);
		f.getContentPane().setBackground(Color.CYAN);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		details1.setLayout(new BorderLayout());
		details2.setLayout(new BorderLayout());
	}

	public void setLocation() {
		back.setBounds(30, 620, 80, 30);
		details1.setBounds(450, 150, 480, 50);
		details2.setBounds(450, 30, 480, 50);
		titletblgardens.setBounds(650, 120, 100, 40);
		titletblkids.setBounds(650, 0, 100, 40);
	}

	public void addComponents() {
		details2.add(sp1);
		details1.add(sp2);
		f.add(details1);
		f.add(details2);
		f.add(back);
		f.add(titletblgardens);
		f.add(titletblkids);
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
