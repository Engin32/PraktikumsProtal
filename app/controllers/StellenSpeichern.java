package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import play.api.mvc.Session;
import play.mvc.Controller;
import play.mvc.Result;

public class StellenSpeichern extends Controller {
	
	
	public static Result speichern(){
		
		// Daten rausholen und in Map reinspeichern
		Map<String, String[]> daten = request().body().asFormUrlEncoded();
		// Daetn rausnehemen von der Map
		String fakultät = daten.get("fakultät")[0];
		String bundesland = daten.get("bundesland")[0];
		String stadt = daten.get("stadt")[0];
		String dauer = daten.get("dauer")[0];
		
		
		
		String aufgaben = daten.get("aufgaben")[0];
		String anforderungen = daten.get("anforderungen")[0];
				
		System.out.println(fakultät+ " " + bundesland+ " "+stadt+ " "+dauer+ " " +aufgaben+ " "+anforderungen+ " ");
		
		
		
		
		
		
		ResultSet rs;
		Connection con;
		Statement stmt;
		String ergebnis = "Kein Ergebnis";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");
			
			// hier werden die Daten eingefügt
			
			
			stmt =con.createStatement();
			stmt.executeUpdate("insert into xxx values();");
			stmt.executeUpdate("insert into xxx values();");
			stmt.executeUpdate("insert into xxx values();");
			
			
			
			

		} catch (Exception e) {

		}

		
		
		
		
		
		
		
		
		
		
		
		return ok("OKKK");
		
		
		
		
		
		
		
		
		
		
	}

}
