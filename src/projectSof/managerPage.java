package projectSof;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class managerPage implements ActionListener {
	JFrame f;
	private JButton enter = new JButton("enter");// get details button
	private JTextField code = new JTextField(); // get details text
	private String[] items = { "block", "garden", "kid", "assistent", "block manager", "garden manager" }; // get
																											// details
																											// items
	private JComboBox searchItems = new JComboBox(items);// get details combo items
	private JPanel pnlsearch = new JPanel(); // get details panel
	private JButton blockpage = new JButton("Add Block"); // add new block button
	private JButton addgarden = new JButton("Add Garden");// add new garden button
	private JButton addkid = new JButton("Add Kid");// add new kid button
	private JButton addassistent = new JButton("Add Assistent"); // add new assistant button
	private JPanel pnlfile = new JPanel();// insert file panel
	private JButton btnfile = new JButton("insert file");// insert file button
	private JTextField txtfile = new JTextField();// insert file text
	private JLabel lblfile = new JLabel("garden id: ");// insert file label
	private JLabel lbldetails = new JLabel("Get Details For: ");// insert file label
	private JLabel lblfiletitle = new JLabel("insert file contain all garden details");// insert file panel title
	private int gardenid;// to get garden id for insert file
	private PrintWriter writer;// insert file class

	// constructor
	public managerPage() {
		createMyWindow(); // create the window function
		setLocation(); // set location function
		addComponents(); // add components function
		actionEventFunc(); // action event function

	}

	public void createMyWindow() {
		f = new JFrame("manager of city");
		f.setBounds(0, 0, 1000, 700);
		f.getContentPane().setLayout(null);
		f.getContentPane().setBackground(Color.CYAN);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		pnlsearch.setBackground(Color.GREEN);
		pnlsearch.setLayout(null);
		pnlfile.setBackground(Color.YELLOW);
		pnlfile.setLayout(null);
	}

	public void setLocation() {
		pnlsearch.setBounds(200, 200, 420, 70);
		searchItems.setBounds(5, 20, 130, 30);
		code.setBounds(140, 20, 175, 30);
		enter.setBounds(325, 20, 80, 30);
		blockpage.setBounds(30, 30, 120, 30);
		addgarden.setBounds(150, 30, 120, 30);
		addkid.setBounds(270, 30, 120, 30);
		addassistent.setBounds(390, 30, 120, 30);
		pnlfile.setBounds(600, 20, 350, 50);
		lblfile.setBounds(10, 10, 120, 30);
		txtfile.setBounds(80, 10, 135, 30);
		btnfile.setBounds(220, 10, 95, 30);
		lblfiletitle.setBounds(670, 0, 250, 20);
		lbldetails.setBounds(330, 165, 250, 50);

	}

	public void addComponents() {
		pnlsearch.add(searchItems);
		pnlsearch.add(code);
		pnlsearch.add(enter);
		pnlfile.add(lblfile);
		pnlfile.add(txtfile);
		pnlfile.add(btnfile);
		f.add(pnlfile);
		f.add(blockpage);
		f.add(addgarden);
		f.add(addkid);
		f.add(addassistent);
		f.add(pnlsearch);
		f.add(lblfiletitle);
		f.add(lbldetails);
	}

	public void actionEventFunc() {
		enter.addActionListener(this);
		blockpage.addActionListener(this);
		addgarden.addActionListener(this);
		addkid.addActionListener(this);
		addassistent.addActionListener(this);
		btnfile.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ev) {

		if (ev.getSource() == enter) { // if click enter button (get details) then

			if (!code.getText().isEmpty()) {// if search text is not empty then
				try {
					char[] chars = code.getText().toCharArray();// if all search text chars is not digits show error
																// message "code cannot contains characters " else
																// continue
					boolean res = true;
					for (char c : chars) {
						if (!Character.isDigit(c))
							res = false;
						if (res == false)
							throw new Exception();
					}
				} catch (Exception e) {
					code.setText("");
					JOptionPane.showMessageDialog(null, "code cannot contain characters", "error message: " + "exit",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				boolean res = false;
				System s = new System();

				// if selected item equals block then search in block array list if is exists if
				// yes enter to KindergardebByBlock
				if (searchItems.getSelectedItem().toString() == "block") {
					for (int j = 0; j < s.blocks.size(); j++) {
						if (Integer.toString(s.blocks.get(j).getId()).equals(code.getText().toString()))
							res = true;
					}
					if (res == true) {

						f.dispose();
						new KindergardebByBlock(Integer.parseInt(code.getText()));
					} else {// else show message "code not found"
						code.setText("");
						JOptionPane.showMessageDialog(null, "code not found", "no: " + "exit",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}

				// if selected item equals garden then search in garden array list if is exists
				// if yes enter to gardenAndKids
				if (searchItems.getSelectedItem().toString() == "garden") {
					for (int j = 0; j < s.gardens.size(); j++) {
						if (Integer.toString(s.gardens.get(j).getId()).equals(code.getText().toString()))
							res = true;
					}
					if (res == true) {

						f.dispose();
						new gardenAndKids(Integer.parseInt(code.getText()));
					} else {// else show message "code not found"
						code.setText("");// clear search text
						JOptionPane.showMessageDialog(null, "code not found", "no: " + "exit",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}

				// if selected item equals kid then search in garden array list if is exists if
				// yes enter to KidDetails
				if (searchItems.getSelectedItem().toString() == "kid") {
					for (int j = 0; j < s.kids.size(); j++) {
						if (Integer.toString(s.kids.get(j).getId()).equals(code.getText().toString()))
							res = true;
					}
					if (res == true) {

						f.dispose();
						new KidDetails(Integer.parseInt(code.getText()));
					} else {// else show message "code not found"
						code.setText("");// clear search text
						JOptionPane.showMessageDialog(null, "code not found", "no: " + "exit",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}

				// if selected item equals assistent then search in garden array list if is
				// exists if yes enter to AssistentDetails
				if (searchItems.getSelectedItem().toString() == "assistent") {
					for (int j = 0; j < s.assistents.size(); j++) {
						if (Integer.toString(s.assistents.get(j).getId()).equals(code.getText().toString()))
							res = true;
					}
					if (res == true) {

						f.dispose();
						new AssistentDetails(Integer.parseInt(code.getText()));
					} else {// else show message "code not found"
						code.setText("");// clear search text
						JOptionPane.showMessageDialog(null, "code not found", "no: " + "exit",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}

				// if selected item equals block manager then search in garden array list if is
				// exists if yes enter to BlockManagerDetails
				if (searchItems.getSelectedItem().toString() == "block manager") {
					for (int j = 0; j < s.blockManagers.size(); j++) {
						if (Integer.toString(s.blockManagers.get(j).getId()).equals(code.getText().toString()))
							res = true;
					}
					if (res == true) {

						f.dispose();
						new BlockManagerDetails(Integer.parseInt(code.getText()));
					} else {// else show message "code not found"
						code.setText("");// clear search text
						JOptionPane.showMessageDialog(null, "code not found", "no: " + "exit",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}

				// if selected item equals garden manager then search in garden array list if is
				// exists if yes enter to GardenManagerDetails
				if (searchItems.getSelectedItem().toString() == "garden manager") {
					for (int j = 0; j < s.gardenManagers.size(); j++) {
						if (Integer.toString(s.gardenManagers.get(j).getId()).equals(code.getText().toString()))
							res = true;
					}
					if (res == true) {
						f.dispose();
						new GardenManagerDetails(Integer.parseInt(code.getText()));
					} else {// else show message "code not found"
						code.setText("");// clear search text
						JOptionPane.showMessageDialog(null, "code not found", "no: " + "exit",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}

			} else// if search text is empty show message "code cannot be empty"
				JOptionPane.showMessageDialog(null, "code cannot be empty", "no: " + "exit",
						JOptionPane.INFORMATION_MESSAGE);
		}
		if (ev.getSource() == blockpage) {// if click button equals block page then hide this page and enter to
											// block page
			f.dispose();
			new addBlock();

		}

		if (ev.getSource() == addgarden) {// if click button equals add garden then hide this page and enter to add
											// garden page
			f.dispose();
			new addgarden();

		}

		if (ev.getSource() == addkid) {// if click button equals add kid then hide this page and enter to add
									   // kid page
			f.dispose();
			new addKid();

		}

		if (ev.getSource() == addassistent) {// if click button equals add assistant then hide this page and enter to add
											// assistent page
			f.dispose();
			new addAssistent();

		}

		//if click button equals btnfile then
		if (ev.getSource() == btnfile) {//if file text is not empty then
			if (!txtfile.getText().isEmpty()) {
				// if garden id contain characters show error message
				try {
					char[] chars = txtfile.getText().toCharArray();
					boolean res = true;
					for (char c : chars) {
						if (!Character.isDigit(c))
							res = false;
						if (res == false)
							throw new Exception();
					}
				} catch (Exception e) {
					txtfile.setText("");
					JOptionPane.showMessageDialog(null, "garden id cannot contain characters",
							"error message: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				// if garden id is not exist into the system show message "garden id not found"
				try {
					System s = new System();
					int ind = -1;
					for (int i = 0; i < s.gardens.size(); i++)
						if (s.gardens.get(i).getId() == Integer.parseInt(txtfile.getText()))
							ind = i;
					if (ind == -1)
						throw new Exception();
					else
						gardenid = ind; //if garden id exist then save index of garden into "gardenid" 
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "garden id not found", "error message: " + "exit",
							JOptionPane.INFORMATION_MESSAGE);
					txtfile.setText("");
					return;
				}
				// send file name garden id and index into array list to the function "putDetails" to insert file
				try {
					putDetails("src/gardenDetails.txt", Integer.parseInt(txtfile.getText()), gardenid);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "file error", "error message: " + "exit",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else//if file text is empty show error message
				JOptionPane.showMessageDialog(null, "set garden id", "error message: " + "exit",
						JOptionPane.INFORMATION_MESSAGE);

		}

	}

	//putDetails function (show all garden details into text file)
	public void putDetails(String c, int gid, int index) throws IOException {
		try {
			System s = new System();
			writer = new PrintWriter(new PrintWriter(c));
			writer.println("garden Details:");
			writer.println("" + s.gardens.get(index).toString());
			writer.println();
			writer.println("assistents:");
			for (int i = 0; i < s.gardens.get(index).assistents.size(); i++)
				writer.println(s.gardens.get(index).assistents.get(i).toString());
			writer.println();
			writer.println("kids:");
			for (int i = 0; i < s.gardens.get(index).kids.size(); i++)
				writer.println(s.gardens.get(index).kids.get(i).toString());
			txtfile.setText("");
			JOptionPane.showMessageDialog(null, "file inserted succesfully", "message: " + "exit",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "exit: " + "exit", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(), "exit: " + "exit", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			writer.close();

		}
	}
}
