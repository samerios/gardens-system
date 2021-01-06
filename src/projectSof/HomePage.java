package projectSof;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import static java.lang.System.out;

public class HomePage implements ActionListener {
	JFrame frame;
	private JLabel manId = new JLabel("CODE");// code label
	private JLabel pass = new JLabel("PASSWORD");// password label
	private JLabel title = new JLabel("kindergarden System");// title label
	private JTextField code = new JTextField();// code text
	private JPasswordField passTitle = new JPasswordField();// password text
	private JButton enter = new JButton("enter");// enter button
	private JPanel pnlentry = new JPanel();// connect panel

	//constructor 
	public HomePage() {
		createMyWindow(); // create the window function
		setLocation(); // set location function
		addComponents(); // add components function
		enter.addActionListener(this);
	}

	public void createMyWindow() {
		// insert frame and his properties
		frame = new JFrame();
		frame.setTitle("Home Page");
		frame.setBounds(0, 0, 1000, 700);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.CYAN);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		pnlentry.setBackground(Color.GREEN);
		pnlentry.setLayout(null);
	}

	public void setLocation() {
		pnlentry.setBounds(300, 200, 350, 170);
		title.setBounds(430, 170, 300, 40);
		enter.setBounds(130, 130, 80, 30);
		manId.setBounds(20, 20, 200, 30);
		code.setBounds(100, 20, 200, 30);
		pass.setBounds(20, 80, 200, 30);
		passTitle.setBounds(100, 80, 200, 30);
	}

	public void addComponents() {
		frame.add(title);
		pnlentry.add(code);
		pnlentry.add(enter);
		pnlentry.add(manId);
		pnlentry.add(pass);
		pnlentry.add(passTitle);
		frame.add(pnlentry);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// if click button equals enter then then
		if (e.getSource() == enter) {
			int res = 0;
			String pas = null;
			// if all textFields is not empty then
			if (!code.getText().isEmpty() && !passTitle.getText().isEmpty()) {
				try {// connect to database and check if code and password is valid if yes go to home
						// page
					Connection conn = connClass.getConn();
					Statement stmt;
					ResultSet rs;
					stmt = conn.createStatement();
					String query = "SELECT * from citymanager";
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						res = rs.getInt("id");
						pas = rs.getString("password").toString();
					}
					conn.close();
					if (code.getText().equals(Integer.toString(res)) && passTitle.getText().toString().equals(pas)) {
						frame.dispose();
						new managerPage();
					} else// if invalid code or password show message
						JOptionPane.showMessageDialog(null, "code or password invalid try again", "no: " + "exit",
								JOptionPane.INFORMATION_MESSAGE);
					// if there are database problems throw exceptions
				} catch (ClassNotFoundException e2) {
					JOptionPane.showMessageDialog(null, "There is database problem try again later",
							"system not work: " + "exit", JOptionPane.INFORMATION_MESSAGE);
					code.setText("");
					passTitle.setText("");

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "sql error connection", "system not work: " + "exit",
							JOptionPane.INFORMATION_MESSAGE);
					code.setText("");
					passTitle.setText("");
				}
			} else {//if code or password is empty show message
				code.setText("");
				passTitle.setText("");
				JOptionPane.showMessageDialog(null, "please enter code and password", "error message: " + "exit",
						JOptionPane.INFORMATION_MESSAGE);

			}
		}
	}
}
