package projectSof;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.JOptionPane;

public class Kid extends Person {

	Kindergarden garden;// belong garden
	private Date birthdate;// date of birth

	// copy constructor used to copy data from database to array lists in system
	// class
	public Kid(int id, String firstName, String lastName, String gender, Date birthdate, int gardenid) {
		super(id, firstName, lastName, gender);
		this.birthdate = birthdate;
		this.garden = new Kindergarden();
		this.garden.setId(gardenid);
	}

	// constructor used to insert new kid in database
	public Kid(String firstName, String lastName, String gender, Date birthdate, Kindergarden garden) {
		super(firstName, lastName, gender);
		setKindergarden(garden);
		setBirthdate(birthdate);
		java.util.Date utilStartDate = birthdate;
		java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
		this.garden = new Kindergarden(garden);

		try {
			Connection conn = connClass.getConn();
			PreparedStatement preparedStmt = conn.prepareStatement(
					"insert into kid (firstname,lastname,gender,dateofbirth,gardenid) values(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setString(3, gender);
			preparedStmt.setDate(4, (sqlStartDate));
			preparedStmt.setInt(5, garden.getId());
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
	////////

	/////// birth date get&&set
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
			this.birthdate = birthdate;
	}
	/////////

///////Kinder_garden get&&set
	public Kindergarden getGarden() {
		return garden;
	}

	public void setGarden(Kindergarden garden) {
		this.garden = new Kindergarden(garden);
	}
	/////////

	/// garden get&&set
	public Kindergarden getKindergarden() {
		return this.garden;
	}
	public boolean setKindergarden(Kindergarden k) {
		if (this.garden != k) {
			this.garden = k;
			if (this.garden != null)
				this.garden = new Kindergarden(k);

			return true;
		}
		return false;
	}
	////////

	// to string
	public String toString() {
		return super.toString() + " gender:  " + this.getGender() + "  birthdate: " + this.getBirthdate().toString();
	}
	/////////////

}