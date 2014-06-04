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
		String ergebnis ="<h1 id='ihreStellen'>Ihre Stellen</h1>";
		int zähler=1;
		String id = "stellenID";
		while(rs.next()){
			
			
			ergebnis+="<h3>Ihre "+ zähler +". Stelle </h3>";
			
			
			ergebnis+="<p><span>Identifikator der Stelle : </span>"+ rs.getString("Identifikator")  +"</p>";	
			ergebnis+="<p><span> Ihre Aufgaben sind: </span> "+ rs.getString("Aufgaben")  +"</p>";	
			ergebnis+="<p><span>Das sollten sie mitbringen: </span> "+ rs.getString("Qualifikationen")  +"</p>";	
			ergebnis+="<p><span>Die Dauer beträgt: </span> "+ rs.getString("dauer")  +"</p>";	
				
			ergebnis+="<p><span>Ihr Ansprechpartner/in: </span>"+ rs.getString("ansprechpartner")  +"</p>";	
			ergebnis+="<p><span>Die Abteilung </span>:  "+ rs.getString("abteilung")  +"</p>";		
			ergebnis+="<p><span>Das Praktikum fängt an am: </span>"+ rs.getString("ab")  +"</p>";
			ergebnis+="<p><span>Telefonnr: </span> "+ rs.getString("telefon")  +"</p>";
			ergebnis+="<p><span>Homepage: </span> "+ rs.getString("homepage")  +"</p>";
			ergebnis+="<p><span>Ort: </span> "+ rs.getString("ort")+"</p>";
			ergebnis+="<p><span>PLZ: </span> "+ rs.getString("plz")+"</p>";
			ergebnis+="<p><span>Straße: </span> "+ rs.getString("strasse")+"</p>";
			ergebnis+="<p><span>Bundesland: </span> "+ rs.getString("bundesland")+"</p>";
			ergebnis+="<p><span>Land: </span> "+ rs.getString("land")+"</p>";
			ergebnis+="<input id='loeschen' type='button' value='löschen' onclick=löschen("+rs.getInt(id)+")>";
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
