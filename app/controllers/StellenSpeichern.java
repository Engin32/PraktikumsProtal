package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import play.api.mvc.Cookies;
import play.api.mvc.Session;
import play.mvc.Controller;
import play.mvc.Http.Cookie;
import play.mvc.Result;

public class StellenSpeichern extends Controller {

	public static Result speichern() {

		// Daten rausholen und in Map reinspeichern
		Map<String, String[]> daten = request().body().asFormUrlEncoded();
		// Daetn rausnehemen von der Map

		/* hole die notwendigen adressen */

		String bld = daten.get("bundesland")[0];
		String ort = daten.get("stadt")[0];
		String str = daten.get("strasse")[0];
		String land = daten.get("land")[0];
		String plz = daten.get("plz")[0];

		/* hole die notwendigen Stellenbeschreibungen */

		String stellennummer = daten.get("stellennummer")[0];
		String fakultät = daten.get("fakultät")[0];
		String dauer = daten.get("dauer")[0];
		String ansprechparnter = daten.get("ansprechpartner")[0];
		String telefon = daten.get("telefonnummer")[0];
		String aufgaben = daten.get("aufgaben")[0];
		String quali = daten.get("anforderungen")[0]; // anforderunngen auf
														// quali ändern
		String ab = daten.get("ab")[0];

		int dauer1 = parseInt(dauer);
		int telefon1 = parseInt(telefon);

		ResultSet rs;
		Connection con;
		Statement stmt;

		// hol dir die ID vom Unternehmen, die wir beim login gesetzt haben,
		Cookie name = request().cookies().get("data");
		String uname = name.value();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");

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

			ps = con.prepareStatement("INSERT INTO Praktikumsportal.Stellenausschreibung"
					+ "(Aufgaben,Qualifikationen,dauer,Identifikator,ansprechpartner,telefon,abteilung,von,adresse,ab)"
					+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?);");

			ps.setString(1, aufgaben);
			ps.setString(2, quali);
			ps.setInt(3, dauer1);
			ps.setString(4, stellennummer);
			ps.setString(5, ansprechparnter);
			ps.setInt(6, telefon1);
			ps.setString(7, fakultät);
			ps.setString(8, uname);
			ps.setInt(9, i);
			ps.setString(10, ab);

			ps.executeUpdate();
			
			
			con.commit();
			System.out.println("Stellenspeichern durchgegangen");

		} catch (Exception e) {
			System.out.println("Fehler bei der Stellenspeicherung." + e.getMessage());
		}

		return ok("OKKK");

	}

	private static int parseInt(String dauer) {
		// TODO Auto-generated method stub
		return 0;
	}

}
