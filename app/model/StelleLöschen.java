package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StelleLöschen {

	public boolean löschen(int id1) {

		PreparedStatement ps;
		Connection con;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");

			ps = con.prepareStatement("delete from Praktikumsportal.Stellenausschreibung where stellenID = ?");

			ps.setInt(1, id1);

			int z = ps.executeUpdate();

			return true;

		} catch (Exception e) {
			System.out.println("Fehler beim löschen einer Stelle  "
					+ e.getMessage());
			return false;
		}

	}

}
