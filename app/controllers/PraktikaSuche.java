package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import play.mvc.Controller;
import play.mvc.Result;

public class PraktikaSuche extends Controller {

	public static Result praktikaSuche() {
		
		
		Map<String, String[]> daten =request().body().asFormUrlEncoded();
		String fakultät = daten.get("fakultät")[0];
		String bundesland = daten.get("bundesland")[0];
		String anfang = daten.get("anfang")[0];
		String stadt = daten.get("stadt")[0];
		String dauer = daten.get("dauer")[0];
		String unternehmen = daten.get("unternehmen")[0];
		
		
		
		
		
		String ergebnis = model.Model.getInstance().getSuche().suchen(fakultät, bundesland, stadt, dauer, unternehmen, anfang);
		


		
		return ok(ergebnis);
		
		

	}

}
