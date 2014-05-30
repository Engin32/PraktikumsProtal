package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import play.mvc.Controller;
import play.mvc.Result;

public class PraktikaSuche extends Controller {

	public static Result praktikaSuche() {
		int dauer1 = 0;
		String beliebig = "beliebig";
		
		Map<String, String[]> daten =request().body().asFormUrlEncoded();
		String fakultät = daten.get("fakultät")[0];
		String bundesland = daten.get("bundesland")[0];
		String stadt = daten.get("stadt")[0];
		String dauer = daten.get("dauer")[0];
		String unternehmen = daten.get("unternehmen")[0];
		String anfang = daten.get("anfang")[0];

		
System.out.println(fakultät+bundesland+stadt+dauer+unternehmen+anfang);
		
		
		// Werte aus der URL holen
		//String fakultät = request().getQueryString("fakultät");
		//String bundesland = request().getQueryString("bundesland");
	//	String stadt = request().getQueryString("stadt");
	//	String dauer = request().getQueryString("dauer");

		System.out.println(fakultät + " " + dauer + " hallo" + stadt + "hallo"); // welche
																					// werte

		if (!dauer.equals(beliebig)) {
			dauer1 = Integer.parseInt(dauer);
			dauer = "nicht null";
		} else {
			dauer = null;
		}
		if (stadt.equals("")) {
			stadt = null;
		}

		System.out.println(stadt + "hallöö"); // ist es null

		//String unternehmen = request().getQueryString("unternehmen");
		//String anfang = request().getQueryString("anfang");

		if (unternehmen.equals("")) {
			unternehmen = null;
		}

		System.out.println(bundesland + fakultät + stadt + dauer1 + dauer
				+ anfang); // alle werte anschauen

		// In der db vergleichen
		ResultSet rs;
		Connection con;
		PreparedStatement ps;
		
		
		
		
		
		// fragen : ist auch alles null was sein muss, geht das mit dem parser?

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
					System.out.println("Bin hier bei 1");
					
					ergebnis="";
					
					while(rs.next()){
						ergebnis+="<div class='stelle'>";
					ergebnis+="<h2>Gefundene Stellen</h2>";
					ergebnis+="<p>Das Unternehmen: "+ rs.getString("untname")  +"</p>";	
					ergebnis+="<p>Identifikator der Stelle "+ rs.getString("Identifikator")  +"</p>";	
					ergebnis+="<p> Ihre Aufgaben sind: "+ rs.getString("Aufgaben")  +"</p>";	
					ergebnis+="<p>Das sollten sie mitbringen: "+ rs.getString("Qualifikationen")  +"</p>";	
					ergebnis+="<p>Die Dauer beträgt: "+ rs.getString("dauer")  +"</p>";	
						
					ergebnis+="<p>Ihr Ansprechpartner/in"+ rs.getString("ansprechpartner")  +"</p>";	
					ergebnis+="<p>Die Abteilung:  "+ rs.getString("abteilung")  +"</p>";		
					ergebnis+="<p>Das Praktikum fängt an am:"+ rs.getString("ab")  +"</p>";
					ergebnis+="<p>Bei Rückfragen können Sie uns anrufen: "+ rs.getString("telefon")  +"</p>";
					ergebnis+="<p>Mehr erfahren sie unter: "+ rs.getString("homepage")  +"</p>";
					ergebnis+="<p>Die Anschrift:  Ort: "+ rs.getString("ort")+ " PLZ: "  +rs.getString("plz") +" Straße: " +rs.getString("strasse") +" Bundesland " +rs.getString("bundesland") +" Land " +rs.getString("land")  +"</p>";
					
					ergebnis+="<p>Senden sie bitte die Bewerbung an: "+ rs.getString("UntID")  +"</p>";
					ergebnis+="<div/>";

					}
					
					

				}

				else if (stadt == null && dauer == null) {
					ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
							+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and untname=?");

					ps.setString(1, bundesland);
					ps.setString(2, fakultät);
					ps.setString(3, anfang);
					ps.setString(4, unternehmen);

					rs = ps.executeQuery();
					System.out.println("Bin hier bei 2");
					
					
					
					ergebnis="";
					
					while(rs.next()){
					ergebnis+="<div class='stelle'>";
					ergebnis+="<h2>Gefundene Stellen</h2>";
					ergebnis+="<p>Das Unternehmen: "+ rs.getString("untname")  +"</p>";	
					ergebnis+="<p>Identifikator der Stelle "+ rs.getString("Identifikator")  +"</p>";	
					ergebnis+="<p> Ihre Aufgaben sind: "+ rs.getString("Aufgaben")  +"</p>";	
					ergebnis+="<p>Das sollten sie mitbringen: "+ rs.getString("Qualifikationen")  +"</p>";	
					ergebnis+="<p>Die Dauer beträgt: "+ rs.getString("dauer")  +"</p>";	
						
					ergebnis+="<p>Ihr Ansprechpartner/in"+ rs.getString("ansprechpartner")  +"</p>";	
					ergebnis+="<p>Die Abteilung:  "+ rs.getString("abteilung")  +"</p>";		
					ergebnis+="<p>Das Praktikum fängt an am:"+ rs.getString("ab")  +"</p>";
					ergebnis+="<p>Bei Rückfragen können Sie uns anrufen: "+ rs.getString("telefon")  +"</p>";
					ergebnis+="<p>Mehr erfahren sie unter: "+ rs.getString("homepage")  +"</p>";
					ergebnis+="<p>Die Anschrift:  Ort: "+ rs.getString("ort")+ " PLZ: "  +rs.getString("plz") +" Straße: " +rs.getString("strasse") +" Bundesland " +rs.getString("bundesland") +" Land " +rs.getString("land")  +"</p>";
					
					ergebnis+="<p>Senden sie bitte die Bewerbung an: "+ rs.getString("UntID")  +"</p>";	
					ergebnis+="<div/>";

					}

				} else if (stadt == null && unternehmen == null) {
					ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
							+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and dauer=?");

					ps.setString(1, bundesland);
					ps.setString(2, fakultät);
					ps.setString(3, anfang);
					ps.setInt(4, dauer1);

					rs = ps.executeQuery();

					System.out.println("Bin hier bei 3");
					ergebnis="";
					
					while(rs.next()){
					ergebnis+="<div class='stelle'>";
					ergebnis+="<h2>Gefundene Stellen</h2>";
					ergebnis+="<p>Das Unternehmen: "+ rs.getString("untname")  +"</p>";	
					ergebnis+="<p>Identifikator der Stelle "+ rs.getString("Identifikator")  +"</p>";	
					ergebnis+="<p> Ihre Aufgaben sind: "+ rs.getString("Aufgaben")  +"</p>";	
					ergebnis+="<p>Das sollten sie mitbringen: "+ rs.getString("Qualifikationen")  +"</p>";	
					ergebnis+="<p>Die Dauer beträgt: "+ rs.getString("dauer")  +"</p>";	
						
					ergebnis+="<p>Ihr Ansprechpartner/in"+ rs.getString("ansprechpartner")  +"</p>";	
					ergebnis+="<p>Die Abteilung:  "+ rs.getString("abteilung")  +"</p>";		
					ergebnis+="<p>Das Praktikum fängt an am:"+ rs.getString("ab")  +"</p>";
					ergebnis+="<p>Bei Rückfragen können Sie uns anrufen: "+ rs.getString("telefon")  +"</p>";
					ergebnis+="<p>Mehr erfahren sie unter: "+ rs.getString("homepage")  +"</p>";
					ergebnis+="<p>Die Anschrift:  Ort: "+ rs.getString("ort")+ " PLZ: "  +rs.getString("plz") +" Straße: " +rs.getString("strasse") +" Bundesland " +rs.getString("bundesland") +" Land " +rs.getString("land")  +"</p>";
					
					ergebnis+="<p>Senden sie bitte die Bewerbung an: "+ rs.getString("UntID")  +"</p>";	
					ergebnis+="<div/>";

					}

				} else if (unternehmen == null && dauer == null) {
					ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
							+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and ort=?");

					ps.setString(1, bundesland);
					ps.setString(2, fakultät);
					ps.setString(3, anfang);
					ps.setString(4, stadt);

					rs = ps.executeQuery();
					System.out.println("Bin hier bei 4");
					ergebnis="";
					
					while(rs.next()){
						ergebnis+="<div class='stelle'>";
					ergebnis+="<h2>Gefundene Stellen</h2>";
					ergebnis+="<p>Das Unternehmen: "+ rs.getString("untname")  +"</p>";	
					ergebnis+="<p>Identifikator der Stelle "+ rs.getString("Identifikator")  +"</p>";	
					ergebnis+="<p> Ihre Aufgaben sind: "+ rs.getString("Aufgaben")  +"</p>";	
					ergebnis+="<p>Das sollten sie mitbringen: "+ rs.getString("Qualifikationen")  +"</p>";	
					ergebnis+="<p>Die Dauer beträgt: "+ rs.getString("dauer")  +"</p>";	
						
					ergebnis+="<p>Ihr Ansprechpartner/in"+ rs.getString("ansprechpartner")  +"</p>";	
					ergebnis+="<p>Die Abteilung:  "+ rs.getString("abteilung")  +"</p>";		
					ergebnis+="<p>Das Praktikum fängt an am:"+ rs.getString("ab")  +"</p>";
					ergebnis+="<p>Bei Rückfragen können Sie uns anrufen: "+ rs.getString("telefon")  +"</p>";
					ergebnis+="<p>Mehr erfahren sie unter: "+ rs.getString("homepage")  +"</p>";
					ergebnis+="<p>Die Anschrift:  Ort: "+ rs.getString("ort")+ " PLZ: "  +rs.getString("plz") +" Straße: " +rs.getString("strasse") +" Bundesland " +rs.getString("bundesland") +" Land " +rs.getString("land")  +"</p>";
					
					ergebnis+="<p>Senden sie bitte die Bewerbung an: "+ rs.getString("UntID")  +"</p>";	
					ergebnis+="<div/>";

					}

				} else if (unternehmen == null) {
					ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
							+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and ort=? and dauer=?");

					ps.setString(1, bundesland);
					ps.setString(2, fakultät);
					ps.setString(3, anfang);
					ps.setString(4, stadt);
					ps.setInt(5, dauer1);

					rs = ps.executeQuery();
					System.out.println("Bin hier bei 5");
					ergebnis="";
					
					while(rs.next()){
						ergebnis+="<div class='stelle'>";
					ergebnis+="<h2>Gefundene Stellen</h2>";
					ergebnis+="<p>Das Unternehmen: "+ rs.getString("untname")  +"</p>";	
					ergebnis+="<p>Identifikator der Stelle "+ rs.getString("Identifikator")  +"</p>";	
					ergebnis+="<p> Ihre Aufgaben sind: "+ rs.getString("Aufgaben")  +"</p>";	
					ergebnis+="<p>Das sollten sie mitbringen: "+ rs.getString("Qualifikationen")  +"</p>";	
					ergebnis+="<p>Die Dauer beträgt: "+ rs.getString("dauer")  +"</p>";	
						
					ergebnis+="<p>Ihr Ansprechpartner/in"+ rs.getString("ansprechpartner")  +"</p>";	
					ergebnis+="<p>Die Abteilung:  "+ rs.getString("abteilung")  +"</p>";		
					ergebnis+="<p>Das Praktikum fängt an am:"+ rs.getString("ab")  +"</p>";
					ergebnis+="<p>Bei Rückfragen können Sie uns anrufen: "+ rs.getString("telefon")  +"</p>";
					ergebnis+="<p>Mehr erfahren sie unter: "+ rs.getString("homepage")  +"</p>";
					ergebnis+="<p>Die Anschrift:  Ort: "+ rs.getString("ort")+ " PLZ: "  +rs.getString("plz") +" Straße: " +rs.getString("strasse") +" Bundesland " +rs.getString("bundesland") +" Land " +rs.getString("land")  +"</p>";
					
					ergebnis+="<p>Senden sie bitte die Bewerbung an: "+ rs.getString("UntID")  +"</p>";	
					ergebnis+="<div/>";

					}

				} else if (dauer == null) {
					ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
							+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and untname=? and ort=?");

					ps.setString(1, bundesland);
					ps.setString(2, fakultät);
					ps.setString(3, anfang);
					ps.setString(4, unternehmen);
					ps.setString(5, stadt);

					rs = ps.executeQuery();
					System.out.println("Bin hier bei 6");
					ergebnis="";
					
					while(rs.next()){
						ergebnis+="<div class='stelle'>";
					ergebnis+="<h2>Gefundene Stellen</h2>";
					ergebnis+="<p>Das Unternehmen: "+ rs.getString("untname")  +"</p>";	
					ergebnis+="<p>Identifikator der Stelle "+ rs.getString("Identifikator")  +"</p>";	
					ergebnis+="<p> Ihre Aufgaben sind: "+ rs.getString("Aufgaben")  +"</p>";	
					ergebnis+="<p>Das sollten sie mitbringen: "+ rs.getString("Qualifikationen")  +"</p>";	
					ergebnis+="<p>Die Dauer beträgt: "+ rs.getString("dauer")  +"</p>";	
						
					ergebnis+="<p>Ihr Ansprechpartner/in"+ rs.getString("ansprechpartner")  +"</p>";	
					ergebnis+="<p>Die Abteilung:  "+ rs.getString("abteilung")  +"</p>";		
					ergebnis+="<p>Das Praktikum fängt an am:"+ rs.getString("ab")  +"</p>";
					ergebnis+="<p>Bei Rückfragen können Sie uns anrufen: "+ rs.getString("telefon")  +"</p>";
					ergebnis+="<p>Mehr erfahren sie unter: "+ rs.getString("homepage")  +"</p>";
					ergebnis+="<p>Die Anschrift:  Ort: "+ rs.getString("ort")+ " PLZ: "  +rs.getString("plz") +" Straße: " +rs.getString("strasse") +" Bundesland " +rs.getString("bundesland") +" Land " +rs.getString("land")  +"</p>";
					
					ergebnis+="<p>Senden sie bitte die Bewerbung an: "+ rs.getString("UntID")  +"</p>";	
					ergebnis+="<div/>";

					}

				} else if (stadt == null) {
					ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
							+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and untname=? and dauer=?");

					ps.setString(1, bundesland);
					ps.setString(2, fakultät);
					ps.setString(3, anfang);
					ps.setString(4, unternehmen);
					ps.setInt(5, dauer1);

					rs = ps.executeQuery();
					System.out.println("Bin hier bei 7");
					ergebnis="";
					
					while(rs.next()){
						ergebnis+="<div class='stelle'>";
					ergebnis+="<h2>Gefundene Stellen</h2>";
					ergebnis+="<p>Das Unternehmen: "+ rs.getString("untname")  +"</p>";	
					ergebnis+="<p>Identifikator der Stelle "+ rs.getString("Identifikator")  +"</p>";	
					ergebnis+="<p> Ihre Aufgaben sind: "+ rs.getString("Aufgaben")  +"</p>";	
					ergebnis+="<p>Das sollten sie mitbringen: "+ rs.getString("Qualifikationen")  +"</p>";	
					ergebnis+="<p>Die Dauer beträgt: "+ rs.getString("dauer")  +"</p>";	
						
					ergebnis+="<p>Ihr Ansprechpartner/in"+ rs.getString("ansprechpartner")  +"</p>";	
					ergebnis+="<p>Die Abteilung:  "+ rs.getString("abteilung")  +"</p>";		
					ergebnis+="<p>Das Praktikum fängt an am:"+ rs.getString("ab")  +"</p>";
					ergebnis+="<p>Bei Rückfragen können Sie uns anrufen: "+ rs.getString("telefon")  +"</p>";
					ergebnis+="<p>Mehr erfahren sie unter: "+ rs.getString("homepage")  +"</p>";
					ergebnis+="<p>Die Anschrift:  Ort: "+ rs.getString("ort")+ " PLZ: "  +rs.getString("plz") +" Straße: " +rs.getString("strasse") +" Bundesland " +rs.getString("bundesland") +" Land " +rs.getString("land")  +"</p>";
					
					ergebnis+="<p>Senden sie bitte die Bewerbung an: "+ rs.getString("UntID")  +"</p>";	
					ergebnis+="<div/>";

					}

				}

			} else {// nichts ist null

				ps = con.prepareStatement("select * from Praktikumsportal.Stellenausschreibung,Praktikumsportal.Adresse,Praktikumsportal.Unternehmen "
						+ "where Praktikumsportal.Adresse.adrID=Praktikumsportal.Stellenausschreibung.adresse and von=untID and bundesland = ? and abteilung=? and ab =? and untname=? and ort=? and dauer=?");

				ps.setString(1, bundesland);
				ps.setString(2, fakultät);
				ps.setString(3, anfang);
				ps.setString(4, unternehmen);
				ps.setString(5, stadt);
				ps.setInt(6, dauer1);

				rs = ps.executeQuery();
				System.out.println("Bin hier bei 8");
				ergebnis="";
				
				while(rs.next()){
					ergebnis+="<div class='stelle'>";
				ergebnis+="<h2>Gefundene Stellen</h2>";
				ergebnis+="<p>Das Unternehmen: "+ rs.getString("untname")  +"</p>";	
				ergebnis+="<p>Identifikator der Stelle "+ rs.getString("Identifikator")  +"</p>";	
				ergebnis+="<p> Ihre Aufgaben sind: "+ rs.getString("Aufgaben")  +"</p>";	
				ergebnis+="<p>Das sollten sie mitbringen: "+ rs.getString("Qualifikationen")  +"</p>";	
				ergebnis+="<p>Die Dauer beträgt: "+ rs.getString("dauer")  +"</p>";	
					
				ergebnis+="<p>Ihr Ansprechpartner/in"+ rs.getString("ansprechpartner")  +"</p>";	
				ergebnis+="<p>Die Abteilung:  "+ rs.getString("abteilung")  +"</p>";		
				ergebnis+="<p>Das Praktikum fängt an am:"+ rs.getString("ab")  +"</p>";
				ergebnis+="<p>Bei Rückfragen können Sie uns anrufen: "+ rs.getString("telefon")  +"</p>";
				ergebnis+="<p>Mehr erfahren sie unter: "+ rs.getString("homepage")  +"</p>";
				ergebnis+="<p>Die Anschrift:  Ort: "+ rs.getString("ort")+ " PLZ: "  +rs.getString("plz") +" Straße: " +rs.getString("strasse") +" Bundesland " +rs.getString("bundesland") +" Land " +rs.getString("land")  +"</p>";
				
				ergebnis+="<p>Senden sie bitte die Bewerbung an: "+ rs.getString("UntID")  +"</p>";	
				ergebnis+="<div/>";

				}

			}

			System.out.println("Bin durch gegangen bei der Praktikumssuche");

		} catch (Exception e) {
			System.out.println("Ein Fehler bei der Suche des Praktikums");

		}

		String keinergebnis="Kein Ergebnis";
		if(ergebnis.equals("")){//überprüfen ob ncihts drin im suchergebnis
			System.out.println("nichts drinn");
			
			return ok(keinergebnis);
		}else{System.out.println("etwas drin");
		return ok(ergebnis);
		}
		

	}

}
