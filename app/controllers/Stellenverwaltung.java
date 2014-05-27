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
		
		
		ps = con.prepareStatement("select adresse.land,adresse.ort,adresse.strasse,adresse.plz,adresse.bundesland, Stellenausschreibung.* "
				+ "from Praktikumsportal.Adresse, Praktikumsportal.Unternehmen, Praktikumsportal.Stellenausschreibung "
				+ "where adresse.adrID=Stellenausschreibung.adresse and Stellenausschreibung.von = Unternehmen.untID and Stellenausschreibung.von=?");

		ps.setString(1, name1);
		
		rs = ps.executeQuery();

		System.out.println("Bin da bei zeige unt seine stelle");
		
		
		
		
		
		
		
		return ok("<h1>okk</h1>");
		
		}catch(Exception e){
			
			System.out.println("Bei der Auflistung der eigenen Stellen des Unternehmenn ist dieser Fehler aufgetreten: " + e.getMessage());
			
			
			return ok("<h1>Es sind keine Stellen von Ihrem Unternehmen vorhanden</h1>");
			
			
		}

		
		
	}

	
	
	
	
	
	
	
	
}
