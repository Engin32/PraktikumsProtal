package controllers;

import model.CreateDatabase;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	static CreateDatabase daten = new CreateDatabase(); // hier ein Objetk
														// erzeugen damit einmal
														// datenbank erzeugt
														// wird!!!!!

	public static Result index() {

		return ok(index.render("Your new application is ready.."));

	}

	public static Result startseite() {

		if (daten != null) { // falls datem = null , existiert die DB 
			daten.erzeuge();
		}

		daten = null;  // daten werden für immer auf null gesetzt damit die methode nicht mehr aufgerufen wird
		
		return ok(startseite.render());
	}

	public static Result tipps() {
		return ok(bewerbungstipps.render());
	}

	public static Result register() {
		return ok(ure.render());
	}

	public static Result suche() {
		return ok(Suche.render());
	}

	public static Result impressum() {
		return ok(Impressum.render());
	}

	public static Result kontakt() {
		return ok(Kontakt.render());
	}

	public static Result firmenzeiger() {
		return ok(firmenzeiger.render());
	}

}
