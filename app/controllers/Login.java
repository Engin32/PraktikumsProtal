package controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Policy.Parameters;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import play.*;
import play.api.mvc.Session;
import play.mvc.*;
import play.mvc.Http.Cookie;
import play.mvc.Http.RequestBody;
import views.html.*;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpRequest;

public class Login extends Controller {

	@SuppressWarnings("deprecation")
	public static Result abmeldenUnternehmen() {

		System.out.println("abgemeldet");
		response().discardCookies("data");
		session().clear();

		return ok(startseite.render("Sie haben sich erfolgreich abgemeldet"));

	}

	public static Result anmeldenUnternehmen() {

		// Daten rausholen und in Map reinspeichern
		Map<String, String[]> daten = request().body().asFormUrlEncoded();
		// Daetn rausnehemen von der Map
		String email = daten.get("email")[0];
		String password = daten.get("password")[0];

		boolean ergebnis = model.Model.getInstance().getAnmelden()
				.login(email, password);

		if (ergebnis == true) {
			response().setCookie("data", email);
			session("a", email);
			String user = session("a");
			return ok(afterloginUnternehmen.render(email));

		} else {
			return unauthorized(startseite
					.render("Falsches Passwort oder Email"));
		}

	}

}
