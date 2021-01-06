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

public class AssistentDetails implements ActionListener {

	JFrame f;
	String asistent[][] = null;//assistants details array
	String ascol[] = { "ID", "FIRST NAME", "LAST NAME", "GENDER", "WORK HOURS", "PER HOUR", "garden name",//columns titles of table 
			"blockname" };
	private static int assistentid;//save assistant id
	private JButton back = new JButton("back");//back button
	private JPanel details1 = new JPanel();// details panel
	private JTable myTable1 = new JTable();//details table
	private JScrollPane sp1 = new JScrollPane();//add table to scrolPane
	private JLabel titletblgardens = new JLabel("Assistent Details");//details title
	// constructor
	public AssistentDetails(int assisid) {
		assistentid = assisid;///save into assistentid the id of assisid 
		createMyWindow(); // create the window function
		setLocation(); // set location function
		addComponents(); // add components function
		actionEventFunc(); // action event function
	}

	public void createMyWindow() {
		//get the index of assistant id from array list in system class
		System s = new System();
		int ind = -1;
		asistent = new String[1][8];
		for (int i = 0; i < s.assistents.size(); i++) {
			if (s.assistents.get(i).getId() == assistentid)
				ind = i;
		}
		
		int k = 0,gid = -1;
		//save all details of assistant into the string 
		for (int i = 0; i < s.gardens.size(); i++) {
			if (s.gardens.get(i).getId() == s.assistents.get(ind).garden.getId())
				gid = i;
		}
		asistent[0][k++] = "" + s.assistents.get(ind).getId();
		asistent[0][k++] = "" +s.assistents.get(ind).getFirstName();
		asistent[0][k++] = "" + s.assistents.get(ind).getLastName();
		asistent[0][k++] = "" + s.assistents.get(ind).getGender();
		asistent[0][k++] = "" + s.assistents.get(ind).getWorkhours();
		asistent[0][k++] = "" + s.assistents.get(ind).getSalaryPerHour();
		asistent[0][k++] = "" + s.gardens.get(gid).getName();
		asistent[0][k++] = "" + s.gardens.get(gid).block.gettName();

		myTable1 = new JTable(asistent, ascol);// put the values of string into the table
		myTable1.getTableHeader().setReorderingAllowed(false);// to lock columns
		myTable1.setEnabled(false);// to lock rows
		sp1 = new JScrollPane(myTable1);// add table to scrolPane
		// insert frame and his properties
		f = new JFrame();
		f.setTitle("Asistant Details");
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
		details1.setBounds(200, 150, 700, 50);
		titletblgardens.setBounds(500, 120, 250, 40);
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
