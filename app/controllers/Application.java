package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready.."));
    }
    

    public static Result startseite(){
    	model.Create.erzeuge();  // schau ob es tabellen gibt oder nicht
    	return ok(startseite.render());
    }
    
    public static Result ueberuns(){
    	return ok(ueberuns.render());
    }
    
    public static Result tipps(){
    	return ok(bewerbungstipps.render());
    }
    
    public static Result register(){
    	return ok(ure.render());
    }
    

    public static Result suche(){
    	return ok(Suche.render());
    }
    
    
    public static Result impressum(){
    	return ok(Impressum.render());
    }
    public static Result kontakt(){
    	return ok(Kontakt.render());
    }
  
    public static Result firmenzeiger(){
    	return ok(firmenzeiger.render());
    }
    
    public static Result facebook(){
    	return redirect("http://www.facebook.com");
    }
    
    public static Result twitter(){
    	return redirect("http://www.twitter.com"); 
    }
    
    public static Result htwg(){
    	return redirect("http://www.htwg-konstanz.de");
    }
 

    
}
