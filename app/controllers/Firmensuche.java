package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Firmensuche extends Controller {


	
	public static Result suche(String daten){
		System.out.println("Hier werden die firmen abgerufen");
		String[] unternehmen = {"Daimler","BMW","Bosch" };
		System.out.println(daten);
		String ergebnis="";
		
		for(int i =0; i<unternehmen.length; i++){
			
			if(daten.equals(unternehmen[i])){
				ergebnis=unternehmen[i];
				break;
			}else{
				ergebnis="kein Ergebnis";
			}
			
		}
		
		
		return ok("Hier ist das gesuchte Ergbenis: " +ergebnis);
		
		
	}
	
	
	

}
