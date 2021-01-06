package projectSof;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class addgarden implements ActionListener {

	JFrame f;
	private JButton back = new JButton("back");// back button
	private JButton add = new JButton("add");// add garden button
	private JTextField textName = new JTextField();// garden name text
	private JTextField mgfname = new JTextField();// manager first name text
	private JTextField mglname = new JTextField();// manager last name text
	private JTextField mgstartdate = new JTextField();// manager start date text
	// labels
	private JLabel gardenName = new JLabel("kindergarden name");
	private JLabel lblmgfname = new JLabel("manager first name");
	private JLabel lblmglname = new JLabel("manager last name");
	private JLabel lblmgStartdate = new JLabel("manager start Date");
	private JLabel lblblock = new JLabel("belong to block name");
	private JLabel NewKinTitle = new JLabel("Add new kindergarden");
	//
	private JPanel newKindergarden = new JPanel();// add garden panel
	private int bid;// to save block id selected comboBox
	private String[] blockss = null;// blocks names string
	private JComboBox blockitems;// block items comboBox

	// constructor
	public addgarden() {
		createMyWindow(); // create the window function
		setLocation(); // set location function
		addComponents(); // add components function
		actionEventFunc(); // action event function
	}

	public void createMyWindow() {
		System s = new System();
		blockss = new String[s.blocks.size()];
		// set all blocks names into string "blockss"
		for (int i = 0; i < s.blocks.size(); i++) {
			blockss[i] = s.blocks.get(i).gettName();
		}
		blockitems = new JComboBox(blockss);// add string "blockss" to comboBox
		bid = blockitems.getSelectedIndex();// save the index of block in array list
		// insert frame and his properties
		f = new JFrame();
		f.setTitle("Add New Garden");
		f.setBounds(0, 0, 1000, 700);
		f.getContentPane().setLayout(null);
		f.getContentPane().setBackground(Color.CYAN);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		newKindergarden.setBackground(Color.GREEN);
		newKindergarden.setLayout(null);
	}

	public void setLocation() {
		NewKinTitle.setBounds(30, 5, 150, 30);
		gardenName.setBounds(10, 0, 200, 50);
		textName.setBounds(150, 20, 150, 20);
		lblmgfname.setBounds(10, 30, 200, 50);
		mgfname.setBounds(150, 45, 150, 20);
		lblmglname.setBounds(10, 60, 200, 50);
		mglname.setBounds(150, 70, 150, 20);
		lblmgStartdate.setBounds(10, 90, 200, 50);
		mgstartdate.setBounds(150, 105, 150, 20);
		lblblock.setBounds(10, 120, 200, 50);
		blockitems.setBounds(150, 135, 150, 20);
		add.setBounds(160, 175, 80, 20);
		back.setBounds(30, 600, 80, 30);
		newKindergarden.setBounds(30, 30, 320, 200);
	}

	public void addComponents() {
		f.add(NewKinTitle);
		f.add(back);
		newKindergarden.add(gardenName);
		newKindergarden.add(textName);
		newKindergarden.add(lblmgfname);
		newKindergarden.add(mgfname);
		newKindergarden.add(lblmglname);
		newKindergarden.add(mglname);
		newKindergarden.add(lblmgStartdate);
		newKindergarden.add(mgstartdate);
		newKindergarden.add(lblblock);
		newKindergarden.add(blockitems);
		newKindergarden.add(add);
		f.add(newKindergarden);
	}

	public void actionEventFunc() {
		back.addActionListener(this);
		add.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ev) {
		// if click button equals back then hide this page and return to manager page
		if (ev.getSource() == back) {
			f.dispose();
			new managerPage();
		}
		// if click button equals add then
		if (ev.getSource() == add) {
			// if all textFields is not empty then
			if (!textName.getText().isEmpty() && !mgfname.getText().isEmpty() && !mglname.getText().isEmpty()
					&& !mgstartdate.getText().isEmpty() && blockitems.getSelectedIndex() > -1) {
				// if garden name all chars is letters continue
				boolean res = true, exist = false;// to check input
				try {
					char[] chars = textName.getText().toCharArray();
					 res = true;
					for (char c : chars) {
						if (!Character.isLetter(c))
							res = false;
						if (res == false)// else throw exception
							throw new Exception();
					}
					// if garden name exist in system then throw exception
					System s = new System();
					for (int i = 0; i < s.gardens.size(); i++) {
						if (s.gardens.get(i).getName().equals(textName.getText().toString()))
							exist = true;
					}
					if (exist == true)
						throw new Exception();// if true throw exception
				} catch (Exception e) {// show error message
					if (res == false) {
						textName.setText("");
						JOptionPane.showMessageDialog(null, "garden name cannot contain digits or special characters",
								"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					}

					else if (exist == true) {
						textName.setText("");// show error message
						JOptionPane.showMessageDialog(null, "garden name exist into the system",
								"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					}
					return;
				}
				DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {// if date format is invalid then throw parse exception
					date = simpleDateFormat.parse(mgstartdate.getText());
				} catch (ParseException e) {// show error message
					JOptionPane.showMessageDialog(null, "set date format yyyy-MM-dd ", "Date error" + "exit",
							JOptionPane.INFORMATION_MESSAGE);
					mgstartdate.setText("");
					return;

				}

				try {// if first name all chars is letters continue
					char[] chars = mgfname.getText().toCharArray();
					 res = true;
					for (char c : chars) {
						if (!Character.isLetter(c))
							res = false;
						if (res == false)// else throw exception
							throw new Exception();
					}
				} catch (Exception e) {// show error message
					mgfname.setText("");
					JOptionPane.showMessageDialog(null,
							"manager first name cannot contain digits or special characters",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				try {// if last name all chars is letters continue
					char[] chars = mglname.getText().toCharArray();
					 res = true;
					for (char c : chars)
						if (!Character.isLetter(c))
							res = false;
					if (res == false)// else throw exception
						throw new Exception();
				} catch (Exception e) {// show error message
					mglname.setText("");
					JOptionPane.showMessageDialog(null, "manager last name cannot contain digits or special characters",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				System s = new System();
				bid = blockitems.getSelectedIndex();// save into bid the index of selected block in array list
				Kindergarden k = new Kindergarden(textName.getText(), s.blocks.get(bid));// call garden constructor and
																							// insert new
																							// garden in database
				Manager m = new Manager(mgfname.getText(), mglname.getText(), "female", date, k);// call manager
																									// constructor and
																									// insert new
																									// garden manager in
																									// database
				// show successfully add message
				textName.setText("");
				mglname.setText("");
				mgfname.setText("");
				mgstartdate.setText("");
				JOptionPane.showMessageDialog(null, "garden add succesfully ", "" + "exit",
						JOptionPane.INFORMATION_MESSAGE);
			} else {// if one or more text fields is empty show message
				JOptionPane.showMessageDialog(null, "fill all fields", " " + "exit", JOptionPane.INFORMATION_MESSAGE);

			}
		}
	}

}
