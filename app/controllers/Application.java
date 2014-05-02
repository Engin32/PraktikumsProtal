package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready.."));
    }
    
    public static Result startseite(){
    	
    	return ok(startseite.render());
    }
    
    
    public static Result tipps(){
    	return ok(bewerbungstipps.render());
    }
    
    public static Result register(){
    	return ok(ure.render());
    }
    
    public static Result impressum(){
    	return ok(Impressum.render());
    }
    
    public static Result kontakt(){
    	return ok(Kontakt.render());
    }
    
    public static Result suche(){
    	return ok(Suche.render());
    }
    
}
