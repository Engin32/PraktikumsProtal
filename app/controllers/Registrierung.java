package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.sql.*;

import play.mvc.Controller;
import play.mvc.Result;

public class Registrierung extends Controller {

	public static Result register() {

		Map<String, String[]> daten = request().body().asFormUrlEncoded();

		String uname = daten.get("Unternehmensname")[0];
		String branche = daten.get("Branche")[0];
		String passsw = daten.get("Passwort")[0];
		String tel = daten.get("Festnetznummer")[0];
		String email = daten.get("E-Mail")[0];
		String land = daten.get("Land")[0];
		String ort = daten.get("Ort")[0];
		String str = daten.get("Strasse")[0];
		String plz = daten.get("Plz")[0];
		String bld = daten.get("Bundesland")[0];
		
		Statement stmt;
		ResultSet rs;
		Connection con;

		try {
			
		

			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("hier");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");
			System.out.println("alles in Ordnung");
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("insert into Praktikumsportal.Adresse values(?,?,?,?,?,?)");

			ps.setInt(1, 11);
			ps.setString(2, land);
			ps.setString(3, ort);
			ps.setString(4, str);
			ps.setString(5, plz);
			ps.setString(6, bld);
			
			ps.execute();
			
			System.out.println("In db eingefügt");
			con.commit();

			return ok("test");

		} catch (Exception ex) {
			System.out.println("Dieser Fehler ist aufgetreten: "
					+ ex.getMessage());
			return ok("test");

		}

	}

}
