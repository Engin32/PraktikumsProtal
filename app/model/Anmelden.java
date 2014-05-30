package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.codec.digest.DigestUtils;

import play.mvc.Http.Cookie;

public class Anmelden {

	public boolean login(String email, String password) {

		ResultSet rs;
		Connection con;
		boolean regisrtiert = false;
		String unternehmen = "";
		String passwort = "";
		String name = "";
		try {

			String hashtext = DigestUtils.md5Hex(password);

			System.out.println("Bin beim logging");

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");
			System.out.println("alles in Ordnung");

			Statement stmt = con.createStatement();
			rs = stmt
					.executeQuery("select untID,untname, passwort from Unternehmen");

			while (rs.next()) {

				unternehmen = rs.getString("untID");
				name = rs.getString("untname");
				passwort = rs.getString("passwort");

				if (email.equals(unternehmen) && hashtext.equals(passwort)) {

					regisrtiert = true;
					break;

				}

			}

		} catch (Exception ex) {
			System.out.println("Dieser Fehler ist aufgetreten: "
					+ ex.getMessage());

		}

		System.out.println("hallo das ist deine Email: " + email
				+ "und das dein Password: " + password);

		if (regisrtiert == true) {
			return true;
		} else {
			return false;
		}

	}

}
