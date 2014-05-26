package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;

public class PraktikaSuche extends Controller {

	public static Result praktikaSuche() {
		int dauer1 = 0;
		String beliebig = "beliebig";

		// Werte aus der URL holen
		String fakultät = request().getQueryString("fakultät");
		String bundesland = request().getQueryString("bundesland");
		String stadt = request().getQueryString("stadt");
		String dauer = request().getQueryString("dauer");
		if (!dauer.equals(beliebig)) {
			dauer1 = Integer.parseInt(dauer);
		}

		// String größe=request().getQueryString("größe");
		String unternehmen = request().getQueryString("unternehmen");
		String anfang = request().getQueryString("anfang");

		System.out.println(bundesland + fakultät + stadt + dauer1 + dauer
				+ anfang);

		// In der db vergleichen
		ResultSet rs;
		Connection con;
		PreparedStatement ps;

		String ergebnis = "Kein Ergebnis";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");

			if (stadt == null || dauer == null || unternehmen == null) { // alle
																			// Kombinationen
																			// durchgehen

				if (stadt == null && dauer == null && unternehmen == null) {
					ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
							+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =?");
					
					ps.setString(1, bundesland);
					ps.setString(2, fakultät);
					ps.setString(3, anfang);
					rs = ps.executeQuery();

				}

				else if (stadt == null && dauer == null) {
					ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
							+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and untname=?");
					
					ps.setString(1, bundesland);
					ps.setString(2, fakultät);
					ps.setString(3, anfang);
					ps.setString(4, unternehmen);

					rs = ps.executeQuery();

				} else if (stadt == null && unternehmen == null) {
					ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
							+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and dauer=?");
					
					ps.setString(1, bundesland);
					ps.setString(2, fakultät);
					ps.setString(3, anfang);
					ps.setInt(4, dauer1);

					rs = ps.executeQuery();


					rs = ps.executeQuery();

				} else if (unternehmen == null && dauer == null) {
					ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
							+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and ort=?");
					
					ps.setString(1, bundesland);
					ps.setString(2, fakultät);
					ps.setString(3, anfang);
					ps.setString(4, stadt);

					rs = ps.executeQuery();


				} else if (unternehmen == null) {
					ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
							+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and ort=? and dauer=?");
					
					ps.setString(1, bundesland);
					ps.setString(2, fakultät);
					ps.setString(3, anfang);
					ps.setString(4, stadt);
					ps.setInt(5, dauer1);


					rs = ps.executeQuery();


				} else if (dauer == null) {
					ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
							+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and untname=? and ort=?");
					
					ps.setString(1, bundesland);
					ps.setString(2, fakultät);
					ps.setString(3, anfang);
					ps.setString(4, unternehmen);
					ps.setString(5, stadt);

					rs = ps.executeQuery();


				} else if (stadt == null) {
					ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
							+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and untname=? and dauer=?");
					
					ps.setString(1, bundesland);
					ps.setString(2, fakultät);
					ps.setString(3, anfang);
					ps.setString(4, unternehmen);
					ps.setInt(5, dauer1);

					rs = ps.executeQuery();

				}

			} else {//nichts ist null
				
				
				ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
					+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and untname=? and ort=? and dauer=?");
			
			ps.setString(1, bundesland);
			ps.setString(2, fakultät);
			ps.setString(3, anfang);
			ps.setString(4, unternehmen);
			ps.setString(5, stadt);
			ps.setInt(6, dauer1);

			rs = ps.executeQuery();

			}
			
			System.out.println("Bin durch gegangen bei der Praktikumssuche");

		} catch (Exception e) {
			System.out.println("Ein Fehler bei der Suche des Praktikums");

		}

		return ok(ergebnis);

	}

}
