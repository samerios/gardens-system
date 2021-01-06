package projectSof;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Assistent extends Person {
	Kindergarden garden;// belong garden
	private int workhours;// work hours
	private double salaryPerHour;// salary per hour

	// copy constructor used to copy data from database to array lists in system
	// class
	public Assistent(int id, String firstName, String lastName, String gender, int hours, double salary, int gardenid) {
		super(id, firstName, lastName, gender);
		this.workhours = hours;
		this.salaryPerHour = salary;
		this.garden = new Kindergarden();
		this.garden.setId(gardenid);
	}

	// constructor used to insert new assistant in database
	public Assistent(String firstName, String lastName, String gender, int hours, double salary, Kindergarden garden) {
		super(firstName, lastName, gender);
		setWorkhours(hours);
		setSalaryPerHour(salary);
		this.garden = new Kindergarden(garden);
		try {
			Connection conn = connClass.getConn();
			PreparedStatement preparedStmt = conn.prepareStatement(
					"insert into assistent (firstname,lastname,gender,workhours,perhour,gardenid) values(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setString(3, gender);
			preparedStmt.setInt(4, hours);
			preparedStmt.setDouble(5, salary);
			preparedStmt.setInt(6, garden.getId());
			preparedStmt.execute();

			ResultSet rs = preparedStmt.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
			conn.close();
			new System();// update all array lists
			// if there are database problems throw exceptions
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "There is database problem try again later", "error message: " + "exit",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "sql error connection", "error message: " + "exit",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	/////// work hours get&&set
	public int getWorkhours() {
		return this.workhours;
	}

	public void setWorkhours(int workhours) {
		this.workhours = workhours;
	}
	/////////////

	/////// salary Per Hour get&&set
	public double getSalaryPerHour() {
		return this.salaryPerHour;
	}

	public void setSalaryPerHour(double salaryPerHour) {
		this.salaryPerHour = salaryPerHour;

	}
	///////////

	/////// Kinder_garden get&&set
	public Kindergarden getGarden() {
		return garden;
	}

	public void setGarden(Kindergarden garden) {

		this.garden = new Kindergarden(garden);
	}
	/////////

	/// Kindergarden set&&get
	public Kindergarden getKindergarden() {
		return this.garden;
	}

	public boolean setKindergarden(Kindergarden k) {
		if (this.garden != k) {
			this.garden = k;
			if (this.garden != null)
				garden = new Kindergarden(k);

			return true;
		}
		return false;
	}
	////////

	// to string
	public String toString() {
		return super.toString() + "  gender: " + this.getGender() + " workhours: " + workhours + " salary Per Hour: "
				+ salaryPerHour;
	}
	/////////////
}