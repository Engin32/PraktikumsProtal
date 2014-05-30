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
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class Firmensuche extends Controller {

	public static Result suche() throws SQLException {

		ResultSet rs;
		Connection con;
		String daten="";
		String ergebnis = "<p>Kein Ergebnis</p>";
		Map<String, String[]> a =request().body().asFormUrlEncoded();
		daten = a.get("unternehmen")[0];
		
		
		System.out.println("nicht null" + a.size() + daten);
		

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");
			System.out.println("alles in Ordnung");

			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("select untname, branche, homepage, telefon from Unternehmen");

			while (rs.next()) {
				String unternehmen = rs.getString("untname");
				String branche = rs.getString("branche");
				String homepage = rs.getString("homepage");
				int telefon = rs.getInt("telefon");

				if (daten.equals(unternehmen)) {
					ergebnis = "<p>"+unternehmen +" ist bei uns registriert!</p>";
					ergebnis+="<p>Branche des Unternehmens : "+branche+"</p>";
					ergebnis+="<p>Homepage des Unternehmens : "+homepage+"</p>";
					ergebnis+="<p>Telefon des Unternehmens : "+telefon+"</p>";
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
		
		System.out.println(ergebnis);
		
		
		
		
		return ok(ergebnis);

	}

}