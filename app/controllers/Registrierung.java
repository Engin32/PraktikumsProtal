package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.sql.*;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.Cookie;
import views.html.StelleErstellen;
import views.html.afterloginUnternehmen;
import views.html.startseite;
import views.html.ure;

public class Registrierung extends Controller {

	public static Result register() {

		Map<String, String[]> daten = request().body().asFormUrlEncoded();

		String uname = daten.get("Unternehmensname")[0];
		String branche = daten.get("Branche")[0];
		String passsw = daten.get("Passwort")[0];
		String passsw2 = daten.get("Passwort2")[0];
		String tel = daten.get("Festnetznummer")[0];
		String email = daten.get("E-Mail")[0];
		String land = daten.get("Land")[0];
		String ort = daten.get("Ort")[0];
		String str = daten.get("Strasse")[0];
		String plz = daten.get("Plz")[0];
		String bld = daten.get("Bundesland")[0];
		String homepage = daten.get("Homepage")[0];
		
		
		
		if(!passsw.equals(passsw2)){
			return ok(ure.render("Passwörter stimmen nicht überein!"));
			
		}
			
			
		ResultSet rs;
		Connection con;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("hier");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");

			System.out.println("alles in Ordnung");

			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO Praktikumsportal.Adresse "
							+ "(land,ort,strasse,plz,bundesland)" + "VALUES"
							+ "(?,?,?,?,?);");

			ps.setString(1, land);
			ps.setString(2, ort);
			ps.setString(3, str);
			ps.setString(4, plz);
			ps.setString(5, bld);

			ps.execute();

			ps = con.prepareStatement("select Praktikumsportal.Adresse.adrID "
					+ "from Praktikumsportal.Adresse where land=? and "
					+ "ort=? and strasse=? and " + "plz=? and bundesland=?");

			ps.setString(1, land);
			ps.setString(2, ort);
			ps.setString(3, str);
			ps.setString(4, plz);
			ps.setString(5, bld);

			rs = ps.executeQuery();

			System.out.println("alles in Ordnung2");

			int i = 0;
			int counter = 0;
			while (rs.next()) {
				if (counter == 0) {
					i = rs.getInt(1);
					counter++;
				} else {
					break;
				}

			}

			ps = con.prepareStatement("INSERT INTO Praktikumsportal.Unternehmen"
					+ "(untID,untname,passwort,branche,telefon,homepage,adresse)"
					+ "VALUES" + "(?,?,?,?,?,?,?);");

			ps.setString(1, email);
			ps.setString(2, uname);
			ps.setString(3, passsw);
			ps.setString(4, branche);
			ps.setString(5, tel);
			ps.setString(6, homepage);
			ps.setInt(7, i);

			ps.executeUpdate();

			System.out.println("In db eingefügt");
			con.commit();

			// den user merken da er ja weitergeleitet wird
			response().setCookie("data", email);
			session("a", email);
			String user = session("a");

			return ok(afterloginUnternehmen.render(uname));

		} catch (Exception ex) {
			System.out.println("Dieser Fehler ist aufgetreten: "
					+ ex.getMessage());
			return ok(ure
					.render("Ein Fehler ist aufgetreten! Versuchen Sie es nochmal."));

		}

	}

	public static Result löschen() {

		Connection con;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("hier");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");
			con.setAutoCommit(false);

			Cookie name = request().cookies().get("data");
			String uname = name.value();

			PreparedStatement ps = con
					.prepareStatement("delete from Praktikumsportal.Stellenausschreibung where von = ?");

			ps.setString(1, uname);
			ps.executeUpdate();


			PreparedStatement ps1 = con
					.prepareStatement("delete from Praktikumsportal.unternehmen where untID =? ");

			ps1.setString(1, uname);
			ps1.executeUpdate();


			con.commit();
			
			System.out.println("hab das unternhemen gelöscht");

			return ok(startseite.render(null));

		} catch (Exception e) {

			System.out
					.println("Beim löschen des Unternehmens ist folgender Fehler aufgetreten: "
							+ e.getMessage());

			return ok("Fehler");

		}

	}

}
