package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import play.api.mvc.Cookies;
import play.api.mvc.Session;
import play.mvc.Controller;
import play.mvc.Http.Cookie;
import play.mvc.Result;
import views.html.afterloginUnternehmen;

public class StellenSpeichern extends Controller {

	public static Result speichern() {

		// Daten rausholen und in Map reinspeichern
		Map<String, String[]> daten = request().body().asFormUrlEncoded();
		// Daetn rausnehemen von der Map

		/* hole die notwendigen adressen */

		String bld = daten.get("bundesland")[0];
		String ort = daten.get("stadt")[0];
		String str = daten.get("strasse")[0];
		String land = daten.get("land")[0];
		String plz = daten.get("plz")[0];

		/* hole die notwendigen Stellenbeschreibungen */

		String stellennummer = daten.get("stellennummer")[0];
		String fakultät = daten.get("fakultät")[0];
		String dauer = daten.get("dauer")[0];
		String ansprechparnter = daten.get("ansprechpartner")[0];
		String telefon = daten.get("telefonnummer")[0];
		String aufgaben = daten.get("aufgaben")[0];
		String quali = daten.get("anforderungen")[0]; // anforderunngen auf
														// quali ändern
		String ab = daten.get("ab")[0];

		Cookie name = request().cookies().get("data");
		String uname = name.value();
		
		boolean ergebnis = model.Model.getInstance().getSpeichern().speichern(bld, ort, str, land, plz, stellennummer, fakultät, dauer, ansprechparnter, telefon, aufgaben, quali, ab, uname);
		
		if(ergebnis == true){
			
			return ok(afterloginUnternehmen.render(uname));
		}
		System.out.println("Nicht gespeicher!!!!");
		return ok(afterloginUnternehmen.render(uname));

	}
	

	
	
	
	


}
