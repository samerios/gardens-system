package projectSof;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import projectSof.System;

public class addKid implements ActionListener, ItemListener {

	JFrame f;
	private JButton back = new JButton("back");// back button
	private JButton addkid = new JButton("add kid");//add kid button
	private JTextField txtfname = new JTextField();// first name text
	private JTextField txtlname = new JTextField();// last name text
	private JTextField txtdob = new JTextField();// date of birth text
	//labels
	private JLabel lblnekid = new JLabel("Add New Kid");
	private JLabel lblfname = new JLabel("first name");
	private JLabel lbllname = new JLabel("last name");
	private JLabel lblgender = new JLabel("Gender");
	private JLabel lbldob = new JLabel("Date of Birth");
	private JLabel lblblock = new JLabel("belong to block");
	private JLabel lblgarden = new JLabel("belong to garden");
	//
	private JPanel newkid = new JPanel();// add new kid panel
	String[] gender = { "Male", "Female" };////kid gender
	private JComboBox kidgenderCombo = new JComboBox(gender);// gender comboBox
	private String[] blockss = null;// blocks string
	private JComboBox blockitems;// blocks comboBox
	private String[] gardenss = null;// gardens string
	private JComboBox gadensitems;// gardens comboBox
	private int bid, gid;// two int to save select block id and garden id
	
	// constructor
	public addKid() {
		//if no any block and garden then show error message
		try{
		createMyWindow(); // create the window function
		setLocation(); // set location function
		addComponents(); // add components function
		actionEventFunc(); // action event function
		} catch (Exception e) { 	
			JOptionPane.showMessageDialog(null, "no blocks or gardens",//show error message
				"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
		f = new JFrame();
		f.dispose();
		new managerPage();
		return;
		}

	}

	public void createMyWindow() {
		// set all blocks names into string "blockss"
		System s = new System();
		blockss = new String[s.blocks.size()];
		for (int i = 0; i < s.blocks.size(); i++) {
			blockss[i] = s.blocks.get(i).gettName();
		}
		blockitems = new JComboBox(blockss);// add all string values to comboBox
		bid = blockitems.getSelectedIndex();// get selected index of block and save into bid
		//
		// set all gardens name if current capacity less 20 that belong selected block into string "gardenss"
		
		 gardenss = new String[s.blocks.get(bid).gardens.size()];
		for (int i = 0; i < s.blocks.get(bid).gardens.size(); i++) {
			if (s.gardens.get(i).getCapacity() < 20)
				gardenss[i] = s.blocks.get(bid).gardens.get(i).getName();
		}

		gadensitems = new JComboBox(gardenss);//add all string values to comboBox
		// get garden index in arrayList
		// if garden name equals garden name selected then save garden index
		int ind = -1;
		for (int i = 0; i < s.gardens.size(); i++) {
			if (s.gardens.get(i).getName().equals(gadensitems.getSelectedItem())) {
				ind = i;
			}
		}
		gid = ind;
		//insert frame and his properties
		f = new JFrame();
		f.setTitle("Add New Kid");
		f.setBounds(0, 0, 1000, 700);
		f.getContentPane().setLayout(null);
		f.getContentPane().setBackground(Color.CYAN);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		newkid.setBackground(Color.GREEN);
		newkid.setLayout(null);
	}

	public void setLocation() {
		lblnekid.setBounds(30, 5, 150, 30);
		lblfname.setBounds(10, 0, 200, 50);
		txtfname.setBounds(150, 20, 150, 20);
		lbllname.setBounds(10, 30, 200, 50);
		txtlname.setBounds(150, 45, 150, 20);
		lblgender.setBounds(10, 60, 200, 50);
		kidgenderCombo.setBounds(150, 70, 150, 20);
		lbldob.setBounds(10, 90, 200, 50);
		txtdob.setBounds(150, 105, 150, 20);
		addkid.setBounds(160, 220, 80, 20);
		back.setBounds(30, 620, 80, 30);
		newkid.setBounds(30, 30, 360, 250);
		lblblock.setBounds(10, 125, 200, 50);
		lblgarden.setBounds(10, 165, 200, 50);
		blockitems.setBounds(150, 140, 150, 20);
		gadensitems.setBounds(150, 170, 150, 20);
	}

	public void addComponents() {
		newkid.add(lblfname);
		newkid.add(txtfname);
		newkid.add(lbllname);
		newkid.add(txtlname);
		newkid.add(lblgender);
		newkid.add(kidgenderCombo);
		newkid.add(lbldob);
		newkid.add(txtdob);
		newkid.add(addkid);
		newkid.add(lblblock);
		newkid.add(lblgarden);
		newkid.add(blockitems);
		newkid.add(gadensitems);
		f.add(back);
		f.add(newkid);
		f.add(lblnekid);
	}

	public void actionEventFunc() {
		back.addActionListener(this);
		addkid.addActionListener(this);
		blockitems.addItemListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		// if click button equals back then hide this page and return to manager page
		if (ev.getSource() == back) {
			f.dispose();
			new managerPage();

		}
		// if click button equals addkid then
		if (ev.getSource() == addkid) {
			// if all textFields is not empty then
			if (!txtfname.getText().isEmpty() && !txtlname.getText().isEmpty() && !txtdob.getText().isEmpty()
					&& blockitems.getSelectedIndex() > -1 && gadensitems.getSelectedIndex() > -1) {
				// if first name all chars is letters continue
				try {
					char[] chars = txtfname.getText().toCharArray();
					boolean res = true;
					for (char c : chars) {
						if (!Character.isLetter(c))
							res = false;
						if (res == false)// else throw exception
							throw new Exception();
					}
				} catch (Exception e) {// show error message
					txtfname.setText("");
					JOptionPane.showMessageDialog(null, "first name cannot contain digits or special characters",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// if last name all chars is letters continue
				try {
					char[] chars = txtlname.getText().toCharArray();
					boolean res = true;
					for (char c : chars)
						if (!Character.isLetter(c))
							res = false;
					if (res == false)// else throw exception
						throw new Exception();
				} catch (Exception e) {// show error message
					txtlname.setText("");
					JOptionPane.showMessageDialog(null, "last name cannot contain digits or special characters",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {// if date format is invalid then throw parse exception
					date = simpleDateFormat.parse(txtdob.getText());
				} catch (ParseException e) {// show error message
					JOptionPane.showMessageDialog(null, "set date format yyyy-MM-dd ", "Date error" + "exit",
							JOptionPane.INFORMATION_MESSAGE);
					txtdob.setText("");
					return;

				}

				
				System s = new System();
				bid = blockitems.getSelectedIndex();//save into bid the index of selected block in array list
				//save into gid the index of selected garden in array list
				int ind = -1;
				for (int i = 0; i < s.gardens.size(); i++) {
					if (s.gardens.get(i).getName().equals(gadensitems.getSelectedItem().toString()))
						ind = i;
				}
				gid = ind;

				try {//if capacity in selected garden is 20 throw exception
					int capa = s.gardens.get(gid).getCapacity();
					if (capa == 20)
						throw new Exception();
				} catch (Exception e) {// show error message
					JOptionPane.showMessageDialog(null,
							"The garden your choosen is full you cannot add any kids into this garden you can remove kids and then add",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					//refresh frame
					f.dispose();
					new addKid();
					return;
				}

				//send details to constructor to insert new kid in database
				Kid k = new Kid(txtfname.getText(), txtlname.getText(), kidgenderCombo.getSelectedItem().toString(),
						date,s.gardens.get(gid));
				// update kindergarden set capacity=capacity+1
				int capacity = s.gardens.get(gid).getCapacity();
				try {
					Connection conn = connClass.getConn();
					PreparedStatement preparedStmt = conn
							.prepareStatement("UPDATE kindergarden SET capacity=? WHERE id=?");
					preparedStmt.setInt(1, capacity + 1);
					preparedStmt.setInt(2, s.gardens.get(gid).getId());
					preparedStmt.execute();
					conn.close();
					new System();//update all array lists
					//if there are  database problems throw exceptions
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "There is database problem try again later",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "sql error connection",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
				}
				
				txtfname.setText("");
				txtlname.setText("");
				txtdob.setText("");
				
				//show successfully add message
				JOptionPane.showMessageDialog(null, "kid add succesfully ", "no: " + "exit",
						JOptionPane.INFORMATION_MESSAGE);
			} else {//if one or more text fields is empty  show message
				JOptionPane.showMessageDialog(null, "fill all fields", "error: " + "exit", JOptionPane.INFORMATION_MESSAGE);

			}
		}

	}

	//item listener if changed selected block then update gid and bid and show different options 
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getStateChange() == ItemEvent.SELECTED) {
			bid = blockitems.getSelectedIndex();
			System s = new System();
			gadensitems.removeAllItems();
			gardenss = new String[s.blocks.get(bid).gardens.size()];

			for (int i = 0; i < s.blocks.get(bid).gardens.size(); i++) {
				if (s.gardens.get(i).getCapacity() < 20)
					gardenss[i] = s.blocks.get(bid).gardens.get(i).getName();
			}

			for (int j = 0; j < gardenss.length; j++)
				gadensitems.addItem(gardenss[j]);
			int ind = -1;
			if(gadensitems.getSelectedIndex()!=-1)//if there are one garden or more in selected block then
			{
			for (int i = 0; i < s.gardens.size(); i++) {
				if (s.gardens.get(i).getName().equals(gadensitems.getSelectedItem().toString()))
					ind = i;
			}
			gid = ind;
			}
		}
	}

}
