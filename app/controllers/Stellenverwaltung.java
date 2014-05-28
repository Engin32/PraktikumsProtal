package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;




import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.Cookie;
import views.html.*;

public class Stellenverwaltung extends Controller {

	public static Result auschreiben() {
		
		Cookie name = request().cookies().get("data");
		if(name==null){
			return ok(startseite.render(null));
			
		}else{
		return ok(StelleErstellen.render());
		}
	}
	
	
	
	
	public static Result eigeneStellen(){
		
		
		
		Cookie name = request().cookies().get("data");
		String name1= name.value();
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
			return ok(ergebnis);
		}
		
		
		
		return ok(ergebnis);
		
		}catch(Exception e){
			
			System.out.println("Bei der Auflistung der eigenen Stellen des Unternehmenn ist dieser Fehler aufgetreten: " + e.getMessage());
			
			
			return ok("<h1>Es sind keine Stellen von Ihrem Unternehmen vorhanden</h1>");
			
			
		}

		
		
	}
	
	public static Result löschen(){
		String id =request().getQueryString("wert");
		int id1 = Integer.parseInt(id);
		PreparedStatement ps;
		Connection con;


		try{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");
		
		
		ps = con.prepareStatement("delete from Praktikumsportal.Stellenausschreibung where stellenID = ?");

		ps.setInt(1, id1);
		
		int z= ps.executeUpdate();
		
		
		return ok("Wurde gelöscht");
		
		

		}catch(Exception e){
			System.out.println("Fehler beim löschen einer Stelle  " + e.getMessage());
			return ok("Wurde nicht gelöscht");
		 }
		
		
	}

	
	
	
	
	
	
	
	
}
