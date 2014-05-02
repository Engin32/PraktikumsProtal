package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Stellenverwaltung extends Controller {

	public static Result auschreiben() {

		return ok(StelleErstellen.render());
	}

	
	
	
	
	
	
	
	
}
