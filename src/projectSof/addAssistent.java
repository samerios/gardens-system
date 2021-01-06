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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import projectSof.System;

public class addAssistent implements ActionListener, ItemListener {

	JFrame f;
	private JButton back = new JButton("back");// back button
	private JButton addassis = new JButton("add assistent");//add assistant button
	private JTextField txtassisfname = new JTextField();// first name text
	private JTextField txtassislname = new JTextField();// last name text
	private JTextField txtassishours = new JTextField();// work hours text
	private JTextField txtassisperhour = new JTextField();// per hour text
	// labels
	private JLabel lblnewassis = new JLabel("Add New Assistent");
	private JLabel lblassisfname = new JLabel("first name");
	private JLabel lblassislname = new JLabel("last name");
	private JLabel lblassishours = new JLabel("work hours");
	private JLabel lblassisperhour = new JLabel("Salary per hour");
	private JLabel lblblock = new JLabel("belong to block");
	private JLabel lblgarden = new JLabel("belong to garden");
	//
	private JPanel newassis = new JPanel();// add new assistant panel
	private String[] blockss = null;// blocks string
	private JComboBox blockitems;// blocks comboBox
	private String[] gardenss = null;// gardens string
	private JComboBox gadensitems;// gardens comboBox
	private int bid, gid;// two int to save select block id and garden id
	// constructor
	public addAssistent() {
		try{//if no any block and garden then show error message
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
		// set all gardens name that belong selected block into string "gardenss"
		gardenss = new String[s.blocks.get(bid).gardens.size()];
		for (int i = 0; i < s.blocks.get(bid).gardens.size(); i++) {
			gardenss[i] = s.blocks.get(bid).gardens.get(i).getName();
		}
		gadensitems = new JComboBox(gardenss);// add all string values to comboBox

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
		f.setTitle("Add New Assistant");
		f.setBounds(0, 0, 1000, 700);
		f.getContentPane().setLayout(null);
		f.getContentPane().setBackground(Color.CYAN);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		newassis.setBackground(Color.GREEN);
		newassis.setLayout(null);

	}

	public void setLocation() {
		back.setBounds(30, 620, 80, 30);
		lblnewassis.setBounds(30, 5, 150, 30);
		newassis.setBounds(30, 30, 360, 250);
		lblassisfname.setBounds(10, 0, 200, 50);
		txtassisfname.setBounds(150, 20, 150, 20);
		lblassislname.setBounds(10, 30, 200, 50);
		txtassislname.setBounds(150, 45, 150, 20);
		lblassishours.setBounds(10, 60, 200, 50);
		txtassishours.setBounds(150, 70, 150, 20);
		lblassisperhour.setBounds(10, 90, 200, 50);
		txtassisperhour.setBounds(150, 105, 150, 20);
		addassis.setBounds(160, 220, 115, 20);
		lblblock.setBounds(10, 125, 200, 50);
		lblgarden.setBounds(10, 165, 200, 50);
		blockitems.setBounds(150, 140, 150, 20);
		gadensitems.setBounds(150, 170, 150, 20);
	}

	public void addComponents() {
		newassis.add(lblassisfname);
		newassis.add(txtassisfname);
		newassis.add(lblassislname);
		newassis.add(txtassislname);
		newassis.add(lblassishours);
		newassis.add(txtassishours);
		newassis.add(lblassisperhour);
		newassis.add(txtassisperhour);
		newassis.add(addassis);
		newassis.add(lblblock);
		newassis.add(lblgarden);
		newassis.add(blockitems);
		newassis.add(gadensitems);
		f.add(lblnewassis);
		f.add(newassis);
		f.add(back);
	}

	public void actionEventFunc() {
		back.addActionListener(this);
		addassis.addActionListener(this);
		blockitems.addItemListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		// if click button equals back then hide this page and return to manager page
		if (ev.getSource() == back) {
			f.dispose();
			new managerPage();
		}
		// if click button equals addassis then
		if (ev.getSource() == addassis) {
			int hours = 0;
			double perhour = 0;
			// if all textFields is not empty then
			if (!txtassisfname.getText().isEmpty() && !txtassislname.getText().isEmpty()
					&& !txtassishours.getText().isEmpty() && !txtassisperhour.getText().isEmpty()
					&& blockitems.getSelectedIndex() > -1 && gadensitems.getSelectedIndex() > -1) {
				// if first name all chars is letters continue
				try {
					char[] chars = txtassisfname.getText().toCharArray();
					boolean res = true;
					for (char c : chars) {
						if (!Character.isLetter(c))
							res = false;
						if (res == false)// else throw exception
							throw new Exception();
					}
				} catch (Exception e) {
					txtassisfname.setText("");// show error message
					JOptionPane.showMessageDialog(null, "first name cannot contain digits or special characters",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				// if last name all chars is letters continue
				try {
					char[] chars = txtassislname.getText().toCharArray();
					boolean res = true;
					for (char c : chars)
						if (!Character.isLetter(c))
							res = false;
					if (res == false)// else throw exception
						throw new Exception();
				} catch (Exception e) {
					txtassislname.setText("");// show error message
					JOptionPane.showMessageDialog(null, "last name cannot contain digits or special characters",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// if hours text all chars is letters continue
				try {
					hours = Integer.parseInt(txtassishours.getText().toString());
					perhour = Double.parseDouble(txtassisperhour.getText().toString());
					if (hours < 0 || hours > 220)// if hours number bigger than 220 or less than 0 then throw exception
						throw new Exception();
					if (perhour < 0 || perhour < 29.12)// if per hour number less than 29.12 or less than 0 then throw
														// exception
						throw new Exception();
				} catch (ParseException e) {
					//if hours text contain any character show message
					JOptionPane.showMessageDialog(null, "hours or salary per hour must be numbers ", "error" + "exit",
							JOptionPane.INFORMATION_MESSAGE);
					txtassishours.setText("");
					txtassisperhour.setText("");
					return;
				} catch (Exception e) {//hours number exceptions
					if (hours < 0) {//if hours number is negative show message
						txtassishours.setText("");
						JOptionPane.showMessageDialog(null, "hours must be positive", "error message: " + "exit",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (hours > 220) {
						txtassishours.setText("");
						JOptionPane.showMessageDialog(null, "hours cannot be bigger than 220 ",
								"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					} else if (perhour < 0) {
						txtassisperhour.setText("");
						JOptionPane.showMessageDialog(null, "salary per hour must be positive",
								"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					} else {
						txtassisperhour.setText("");
						JOptionPane.showMessageDialog(null, "salary per hour cannot be less than 29.12",
								"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					}
					return;
				}
				System s = new System(); 
				//save into bid the index of selected block in array list
				bid = blockitems.getSelectedIndex();
				//save into gid the index of selected garden in array list
				int ind = -1;
				for (int i = 0; i < s.gardens.size(); i++) {
					if (s.gardens.get(i).getName().equals(gadensitems.getSelectedItem().toString()))
						ind = i;
				}
				gid = ind;
				//send details to constructor to insert new assistant in database
				Assistent a1 = new Assistent(txtassisfname.getText(), txtassislname.getText(), "female", hours, perhour,
				s.gardens.get(gid));
				txtassisfname.setText("");
				txtassislname.setText("");
				txtassishours.setText("");
				txtassisperhour.setText("");
				//show successfully add message
				JOptionPane.showMessageDialog(null, "add succesfully ", "no: " + "exit",
						JOptionPane.INFORMATION_MESSAGE);
			} else {//if one or more text fields is empty  show message
				JOptionPane.showMessageDialog(null, "fill all fields", "error: " + "exit", JOptionPane.INFORMATION_MESSAGE);

			}

		}

	}
	//item listener if changed selected block then update gid and bid and show different options 
	public void itemStateChanged(ItemEvent event) {
		// TODO Auto-generated method stub
		if (event.getStateChange() == ItemEvent.SELECTED) {
			bid = blockitems.getSelectedIndex();
			System s = new System();
			gadensitems.removeAllItems();
			gardenss = new String[s.blocks.get(bid).gardens.size()];

			for (int i = 0; i < s.blocks.get(bid).gardens.size(); i++) {
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
