package controllers;

import java.util.Map;

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
		String beschreibung = daten.get("beschreibung")[0];
		
		
		System.out.println(fakultät+ " " + bundesland+ " "+stadt+ " "+dauer+ " " +aufgaben+ " "+anforderungen+ " "+beschreibung);
		
		//if bedingung zb wenn was null ist aber vllt mit html pattern !!!!!!!!!!!!!!!!!!!!!!!!!
		
		//hier muss auch der websocket aufgerufen werden
		// die daten müssen hier eingefügt werden 
		
		
		
		
		
		
		
		
		
		return ok("OKKK");
		
		
		
		
		
		
		
		
		
		
	}

}
