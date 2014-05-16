package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.sql.*;

public class Firmensuche extends Controller {

	public static Result suche(String daten) throws SQLException {

		ResultSet rs;
		Connection con;
		String ergebnis = "Kein Ergebnis";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");
			System.out.println("alles in Ordnung");

			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("select untname from Unternehmen");

			while (rs.next()) {
				String unternehmen = rs.getString("untname");

				if (daten.equals(unternehmen)) {
					ergebnis = unternehmen +" ist bei uns registriert!";
					break;
				} else {
					ergebnis = "Kein Ergebnis";
				}

			}

		} catch (Exception ex) {
			System.out.println("Dieser Fehler ist aufgetreten: "
					+ ex.getMessage());

		}

		System.out.println("Hier werden die firmen abgerufen");
		/*
		String[] unternehmen = { "Daimler", "BMW", "Bosch" };
		System.out.println(daten);

		for (int i = 0; i < unternehmen.length; i++) {

			if (daten.equals(unternehmen[i])) {
				ergebnis = unternehmen[i] +" ist bei uns regisrtiert!";
				break;
			} else {
				ergebnis = "kein Ergebnis";
			}

		}*/

		return ok("Hier ist das gesuchte Ergbenis: " + ergebnis);

	}

}