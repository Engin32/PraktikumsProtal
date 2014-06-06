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
		return ok(StelleErstellen.render(null));
		}
	}
	
	
	
	
	public static Result eigeneStellen(){
		
		
		
		Cookie name = request().cookies().get("data");
		String name1= name.value();

		
		
		String ergebnis = model.Model.getInstance().getLaden().ladeStellen(name1);
		
		return ok(ergebnis);

		
		
	}
	
	public static Result löschen(){
		String id =request().getQueryString("wert");
		int id1 = Integer.parseInt(id);
		
		
		boolean ergebnis = model.Model.getInstance().getLöschen().löschen(id1);
		
		
		if(ergebnis==true){return ok("Wurde gelöscht");}else{return ok("Wurde nicht gelöscht");}
		
		
		
	}

	
	
	
	
	
	
	
	
}
