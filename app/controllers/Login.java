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

		ResultSet rs;
		Connection con;
		boolean regisrtiert = false;
		String unternehmen = "";
		String passwort = "";
		String name = "";

		try {

			String hashtext = DigestUtils.md5Hex(password);

			System.out.println("Bin beim logging");

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");
			System.out.println("alles in Ordnung");

			PreparedStatement stmt = con
					.prepareStatement("select untID,untname, passwort from Unternehmen where untID = ?");
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			
			
	
			
			while (rs.next()) {

				unternehmen = rs.getString("untID");
				name = rs.getString("untname");
				passwort = rs.getString("passwort");

				

				if (email.equals(unternehmen) && hashtext.equals(passwort)) {
					System.out.println("------------------hab verglcihennn-----------------------");
					
					response().setCookie("data", unternehmen);
					
					session("a", unternehmen);
					String user = session("a");
					
					regisrtiert = true;

					System.out.println(user + "hier ist der user");

					break;

				}

			}
			
			
			
			System.out.println("Das passwort in DB: " + passwort + " "
					+ "der eingegebne Passwort: " + hashtext);
			

		} catch (Exception ex) {
			System.out.println("Dieser Fehler ist aufgetreten: "
					+ ex.getMessage());

		}

		System.out.println("hallo das ist deine Email: " + email
				+ " und das dein Password: " + password);
		
		Cookie name1 = request().cookies().get("data");
		if(name1==null){System.out.println("nullll");}else{
		String a = name1.value();
 
		System.out.println(a+ "isch der Cookie");
	}
		
		if (regisrtiert == true & name1 != null) {

			return ok(afterloginUnternehmen.render(name, null));
		} else {

			return unauthorized(startseite
					.render("falscher Username oder Passwort"));

		}

	}

}
