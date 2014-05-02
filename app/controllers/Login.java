package controllers;

import java.security.Policy.Parameters;
import java.util.Map;

import play.*;
import play.mvc.*;
import play.mvc.Http.RequestBody;
import views.html.*;

public class Login extends Controller {
	
	
	
	
	
	
	
	
	
	
	
	

	public static Result anmeldenUnternehmen() {
		// Daten rausholen und in Map reinspeichern
		Map<String, String[]> daten = request().body().asFormUrlEncoded();
		// Daetn rausnehemen von der Map
		String email = daten.get("email")[0];
		String password = daten.get("password")[0];
		
		
		
		
		if (daten == null) {
			
			return null;

		} else {
		
		System.out.println("hallo das ist deine Email: "+email +"und das dein Password: "+password);
			return ok(afterloginUnternehmen.render());
		
		}
	}
	
	
	

	
	
	
	

}
