package projectSof;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class KindergardebByBlock implements ActionListener {

	JFrame f;
	private String data[][] = null;//details array
	private String column[] = { "ID", "NAME", "CAPACITY", "MANAGER" };//columns titles of table 
	private static int blockid;//save block id
	private JButton back = new JButton("back");//back button
	private JLabel address = new JLabel();//block title details
	private JPanel details = new JPanel();//garden details panel
	private JTable myTable = new JTable();//garden details table
	private JScrollPane sp = new JScrollPane();//add garden table to scrolPane
	private JLabel tableTitle = new JLabel("gardens Details");//garden details title
	
	// constructor
	public KindergardebByBlock(int bid) {
		blockid = bid;///save into blockid the id of block 
		createMyWindow(); // create the window function
		setLocation(); // set location function
		addComponents(); // add components function
		actionEventFunc(); // action event function
	}

	public void createMyWindow() {
		//get the index of block id from array list in system class
		System s = new System();
		int ind = -1,k=0;
		for (int i = 0; i < s.blocks.size(); i++) {
			if (s.blocks.get(i).getId() == blockid)
				ind = i;
		}
		//put block details to the string "address"
		address = new JLabel("BLOCK ID: "+ s.blocks.get(ind).getId()+" BLOCK NAME:  " + s.blocks.get(ind).gettName() + "  MANAGER:  "
				+ s.blocks.get(ind).manager.getFirstName() + "   " + s.blocks.get(ind).manager.getLastName());

		//save all details of garden into the string "data"
		data = new String[s.blocks.get(ind).gardens.size()][4];
		int size = s.blocks.get(ind).gardens.size();
		for (int i1 = 0; i1 < size; i1++) {

			k = 0;
			data[i1][k++] = "" + s.blocks.get(ind).gardens.get(i1).getId();
			data[i1][k++] = "" + s.blocks.get(ind).gardens.get(i1).getName();
			data[i1][k++] = "" + s.blocks.get(ind).gardens.get(i1).getCapacity();
			data[i1][k++] = "" + s.blocks.get(ind).gardens.get(i1).manager.getFirstName() + " "
					+ s.blocks.get(ind).gardens.get(i1).manager.getLastName();

		}
		//
		myTable = new JTable(data, column);// put the values of string "data" into the garden table
		myTable.getTableHeader().setReorderingAllowed(false);// to lock columns
		myTable.setEnabled(false);// to lock rows
		sp = new JScrollPane(myTable);// add garden table to scrolPane
		// insert frame and his properties
		f = new JFrame();
		f.setTitle("Gardens in block");
		f.setBounds(30, 30, 800, 600);
		f.getContentPane().setLayout(null);
		f.getContentPane().setBackground(Color.CYAN);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		details.setLayout(new BorderLayout());
	}

	public void setLocation() {
		address.setBounds(200, 5, 500, 20);
		back.setBounds(30, 480, 80, 30);
		details.setBounds(150, 150, 450, 200);
		tableTitle.setBounds(320, 130, 500, 20);
	}

	public void addComponents() {
		f.add(address);
		f.add(back);
		details.add(sp);
		f.add(details);
		f.add(tableTitle);
	}

	public void actionEventFunc() {
		back.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ev) {
		// if click button equals back then hide this page and return to manager page
		if (ev.getSource() == back) {
			f.dispose();
			new managerPage();

		}

	}

}
