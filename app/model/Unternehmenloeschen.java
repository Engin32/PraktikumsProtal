package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Unternehmenloeschen {

	public boolean loeschen(String id) {

		PreparedStatement ps;
		ResultSet rs;
		Connection con;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");

			ps = con.prepareStatement("delete from Praktikumsportal.stellenausschreibung where von=?");

			ps.setString(1, id);

			ps.executeUpdate();

			ps = con.prepareStatement("delete from Praktikumsportal.unternehmen where untID=?");

			ps.setString(1, id);

			ps.executeUpdate();

			System.out.println("__________gelölscht______________");

			return true; // unternehmen gelöscht

		} catch (Exception e) {
			System.out
					.println("Beim löschen des Unternehmens ist folgender Fehler aufgetreten: "
							+ e.getMessage());
			return false;

		}

	}

}
