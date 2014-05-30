package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EigeneStellenLaden {

	public String ladeStellen(String name1){
		

		
		System.out.println("______HALLLLLLLLLLLLLLLLLLLO___________________");
		
		PreparedStatement ps;
		ResultSet rs;
		Connection con;

		
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");
		
		
		ps = con.prepareStatement("select * "
				+ "from Praktikumsportal.Adresse, Praktikumsportal.Unternehmen, Praktikumsportal.Stellenausschreibung "
				+ "where adresse.adrID=Stellenausschreibung.adresse and Stellenausschreibung.von = Unternehmen.untID and Stellenausschreibung.von=?");

		ps.setString(1, name1);
		
		rs = ps.executeQuery();

		System.out.println("Bin da bei zeige unt seine stelle");
		String ergebnis ="<h2>Ihre Stellen</h2>";
		int zähler=1;
		String id = "stellenID";
		while(rs.next()){
			
			
			ergebnis+="<h3>Ihre "+ zähler +". Stelle </h3>";
			
			
			ergebnis+="<p>Identifikator der Stelle :"+ rs.getString("Identifikator")  +"</p>";	
			ergebnis+="<p> Ihre Aufgaben sind: "+ rs.getString("Aufgaben")  +"</p>";	
			ergebnis+="<p>Das sollten sie mitbringen: "+ rs.getString("Qualifikationen")  +"</p>";	
			ergebnis+="<p>Die Dauer beträgt: "+ rs.getString("dauer")  +"</p>";	
				
			ergebnis+="<p>Ihr Ansprechpartner/in"+ rs.getString("ansprechpartner")  +"</p>";	
			ergebnis+="<p>Die Abteilung:  "+ rs.getString("abteilung")  +"</p>";		
			ergebnis+="<p>Das Praktikum fängt an am:"+ rs.getString("ab")  +"</p>";
			ergebnis+="<p>Telefonnr: "+ rs.getString("telefon")  +"</p>";
			ergebnis+="<p>Homepage: "+ rs.getString("homepage")  +"</p>";
			ergebnis+="<p>Die Anschrift:  Ort: "+ rs.getString("ort")+ " PLZ: "  +rs.getString("plz") +" Straße: " +rs.getString("strasse") +" Bundesland " +rs.getString("bundesland") +" Land " +rs.getString("land")  +"</p>";
			ergebnis+="<input type='button' value='löschen' onclick=löschen("+rs.getInt(id)+")>";
			zähler++;
				

			}
		
		if(zähler==1){
			ergebnis+="Sie haben keine Stellen";
			return ergebnis;
		}
		
		
		
		return ergebnis;
		
		}catch(Exception e){
			
			System.out.println("Bei der Auflistung der eigenen Stellen des Unternehmenn ist dieser Fehler aufgetreten: " + e.getMessage());
			
			
			return "Es sind keine Stellen von Ihrem Unternehmen vorhanden";
			
			
		}
		
		
		
		
	}
	
	
	
	
	
}
