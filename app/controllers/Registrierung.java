package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.Cookie;
import views.html.StelleErstellen;
import views.html.afterloginUnternehmen;
import views.html.startseite;
import views.html.ure;

import org.apache.commons.codec.digest.DigestUtils;

public class Registrierung extends Controller {

	public static Result register() {

		Map<String, String[]> daten = request().body().asFormUrlEncoded();

		String uname = daten.get("Unternehmensname")[0];
		String branche = daten.get("Branche")[0];
		String passsw = daten.get("Passwort")[0];
		String passsw2 = daten.get("Passwort2")[0];
		String tel = daten.get("Festnetznummer")[0];
		String email = daten.get("E-Mail")[0];
		String land = daten.get("Land")[0];
		String ort = daten.get("Ort")[0];
		String str = daten.get("Strasse")[0];
		String plz = daten.get("Plz")[0];
		String bld = daten.get("Bundesland")[0];
		String homepage = daten.get("Homepage")[0];

		if(!passsw.equals(passsw2)){
			return ok(ure.render("Passwort stimmt nicht überein!"));
			
		}
		
		
		
		boolean ergebnis =model.Model.getInstance().getRegistrieren().registrieren(uname,branche,passsw,tel,email,land,ort,str,plz,bld,homepage);
		
		if(ergebnis==true){
		// den user merken da er ja weitergeleitet wird
		response().setCookie("data", email);
		session("a",email);
		String user = session("a");
		
		return ok(afterloginUnternehmen.render(email));
		}else{
		
		return ok(ure.render("Fehler versuchen sie es erneut"));
		}

	}
	
	
	public static Result löschen(){
		

		Cookie name = request().cookies().get("data");
		String uname = name.value();
		
		
		boolean erfolg = model.Model.getInstance().getUntlöschen().löschen(uname);
		
		
		if (erfolg==true){
		return ok(startseite.render(null));
		
		}else{
			return ok(afterloginUnternehmen.render(uname));
		}
		
		
	}
	
	
	

}
