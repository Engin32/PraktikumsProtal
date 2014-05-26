package controllers;

import java.security.Policy.Parameters;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import play.*;
import play.api.mvc.Session;
import play.mvc.*;
import play.mvc.Http.RequestBody;
import views.html.*;

import java.sql.*;

public class Login extends Controller {

	
	public static Result abmeldenUnternehmen(){
		
		System.out.println("abgemeldet");
		session().clear();
		return ok(startseite.render(null));

	}
	
	
	
	
	
	public static Result anmeldenUnternehmen() {

		// Daten rausholen und in Map reinspeichern
		Map<String, String[]> daten = request().body().asFormUrlEncoded();
		// Daetn rausnehemen von der Map
		String email = daten.get("email")[0];
		String password = daten.get("password")[0];

		ResultSet rs;
		Connection con;
		boolean regisrtiert = false;
		String unternehmen="";
		String passwort="";
		String name="";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");
			System.out.println("alles in Ordnung");

			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("select untID,untname, passwort from Unternehmen");

			while (rs.next()) {
				
				 unternehmen = rs.getString("untID");
				 name = rs.getString("untname");
				 passwort = rs.getString("passwort");
				
				 
				if (email.equals(unternehmen) && password.equals(passwort)) {
						
					String user = session(email);
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
			
			return ok(afterloginUnternehmen.render(name));
		} else {
			
			return unauthorized(startseite.render("falscher Username oder Passwort"));
		
		}

	}

}
