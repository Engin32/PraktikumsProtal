package controllers;

import model.CreateDatabase;
import play.*;
import play.mvc.*;
import play.mvc.Http.Cookie;
import views.html.*;

public class Application extends Controller {

	static CreateDatabase daten = new CreateDatabase(); // hier ein Objekt
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

		daten = null; // daten werden für immer auf null gesetzt damit die
						// methode nicht mehr aufgerufen wird

		return ok(startseite.render(null));
	}

	public static Result tipps() {
		return ok(bewerbungstipps.render());
	}

	public static Result register() {
		return ok(ure.render(null));
	}

	public static Result suche() {
		Websocket socket = new Websocket();
		socket.registrieren();
		return ok(Suche.render());

	}

	public static Result impressum() {
		return ok(Impressum.render());
	}

	public static Result kontakt() {
		return ok(Kontakt.render());
	}

	/*
	 * public static Result wir(){ return ok(ueberuns.render()); }
	 */

	public static Result firmenzeiger() {
		return ok(firmenzeiger.render());
	}

	public static Result profil() {
		Cookie name = request().cookies().get("data");
		if (name != null) {
			String uname = name.value();

			return ok(afterloginUnternehmen.render(uname));
		} else {
			return ok(startseite.render("Melden Sie sich bitte an"));
		}
	}

	public static Result facebook() {
		return redirect("http://www.facebook.com");
	}

	public static Result twitter() {
		return redirect("http://www.twitter.com");
	}

	public static Result htwg() {
		return redirect("http://www.htwg-konstanz.de");
	}

}
