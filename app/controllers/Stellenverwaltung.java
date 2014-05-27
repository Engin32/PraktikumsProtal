package controllers;

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

	
	
	
	
	
	
	
	
}
