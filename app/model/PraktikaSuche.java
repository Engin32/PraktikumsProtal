package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PraktikaSuche {

	
public String suchen(String fakultät, String bundesland, String stadt, String dauer,String unternehmen,String anfang){
	
	System.out.println("--------suchennnnn---------");
	int dauer1 = 0;
	String beliebig = "beliebig";
	
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
						System.out.println("suche nach wo dauer fakultät");
						while(rs.next()){
							
						
						ergebnis+="<h1 id='gefundeneStellen'>Gefundene Stellen</h1>";
						ergebnis+="<p><span>Das Unternehmen:</span> "+ rs.getString("untname")  +"</p>";	
						ergebnis+="<p><span>Identifikator der Stelle : </span>"+ rs.getString("Identifikator")  +"</p>";	
						ergebnis+="<p><span> Ihre Aufgaben sind: </span> "+ rs.getString("Aufgaben")  +"</p>";	
						ergebnis+="<p><span>Das sollten sie mitbringen:</span> "+ rs.getString("Qualifikationen")  +"</p>";	
						ergebnis+="<p><span>Die Dauer beträgt:</span> "+ rs.getString("dauer")  +"</p>";	
							
						ergebnis+="<p><span>Ihr Ansprechpartner/in: </span>"+ rs.getString("ansprechpartner")  +"</p>";	
						ergebnis+="<p><span>Die Abteilung </span>:  "+ rs.getString("abteilung")  +"</p>";		
						ergebnis+="<p><span>Das Praktikum fängt an am:</span>"+ rs.getString("ab")  +"</p>";
						ergebnis+="<p><span>Bei Rückfragen können Sie uns anrufen:</span> "+ rs.getString("telefon")  +"</p>";
						ergebnis+="<p><span>Mehr erfahren sie unter:</span> "+ rs.getString("homepage")  +"</p>";
						ergebnis+="<p><span>Ort: </span> "+ rs.getString("ort")+"</p>";
						ergebnis+="<p><span>PLZ: </span> "+ rs.getString("plz")+"</p>";
						ergebnis+="<p><span>Straße: </span> "+ rs.getString("strasse")+"</p>";
						ergebnis+="<p><span>Bundesland: </span> "+ rs.getString("bundesland")+"</p>";
						ergebnis+="<p><span>Land: </span> "+ rs.getString("land")+"</p>";

						
						ergebnis+="<p><span>Senden sie bitte mit der Angabe des Identifikators die Bewerbung an:</span> "+ rs.getString("UntID")  +"</p>";	
						
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
							ergebnis+="<h1 id='gefundeneStellen'>Gefundene Stellen</h1>";
							ergebnis+="<p><span>Das Unternehmen:</span> "+ rs.getString("untname")  +"</p>";	
							ergebnis+="<p><span>Identifikator der Stelle : </span>"+ rs.getString("Identifikator")  +"</p>";	
							ergebnis+="<p><span> Ihre Aufgaben sind: </span> "+ rs.getString("Aufgaben")  +"</p>";	
							ergebnis+="<p><span>Das sollten sie mitbringen:</span> "+ rs.getString("Qualifikationen")  +"</p>";	
							ergebnis+="<p><span>Die Dauer beträgt:</span> "+ rs.getString("dauer")  +"</p>";	
								
							ergebnis+="<p><span>Ihr Ansprechpartner/in: </span>"+ rs.getString("ansprechpartner")  +"</p>";	
							ergebnis+="<p><span>Die Abteilung </span>:  "+ rs.getString("abteilung")  +"</p>";		
							ergebnis+="<p><span>Das Praktikum fängt an am:</span>"+ rs.getString("ab")  +"</p>";
							ergebnis+="<p><span>Bei Rückfragen können Sie uns anrufen:</span> "+ rs.getString("telefon")  +"</p>";
							ergebnis+="<p><span>Mehr erfahren sie unter:</span> "+ rs.getString("homepage")  +"</p>";
							ergebnis+="<p><span>Ort: </span> "+ rs.getString("ort")+"</p>";
							ergebnis+="<p><span>PLZ: </span> "+ rs.getString("plz")+"</p>";
							ergebnis+="<p><span>Straße: </span> "+ rs.getString("strasse")+"</p>";
							ergebnis+="<p><span>Bundesland: </span> "+ rs.getString("bundesland")+"</p>";
							ergebnis+="<p><span>Land: </span> "+ rs.getString("land")+"</p>";

							
							ergebnis+="<p><span>Senden sie bitte mit der Angabe des Identifikators die Bewerbung an:</span> "+ rs.getString("UntID")  +"</p>";	
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
							ergebnis+="<h1 id='gefundeneStellen'>Gefundene Stellen</h1>";
							ergebnis+="<p><span>Das Unternehmen:</span> "+ rs.getString("untname")  +"</p>";	
							ergebnis+="<p><span>Identifikator der Stelle : </span>"+ rs.getString("Identifikator")  +"</p>";	
							ergebnis+="<p><span> Ihre Aufgaben sind: </span> "+ rs.getString("Aufgaben")  +"</p>";	
							ergebnis+="<p><span>Das sollten sie mitbringen:</span> "+ rs.getString("Qualifikationen")  +"</p>";	
							ergebnis+="<p><span>Die Dauer beträgt:</span> "+ rs.getString("dauer")  +"</p>";	
								
							ergebnis+="<p><span>Ihr Ansprechpartner/in: </span>"+ rs.getString("ansprechpartner")  +"</p>";	
							ergebnis+="<p><span>Die Abteilung </span>:  "+ rs.getString("abteilung")  +"</p>";		
							ergebnis+="<p><span>Das Praktikum fängt an am:</span>"+ rs.getString("ab")  +"</p>";
							ergebnis+="<p><span>Bei Rückfragen können Sie uns anrufen:</span> "+ rs.getString("telefon")  +"</p>";
							ergebnis+="<p><span>Mehr erfahren sie unter:</span> "+ rs.getString("homepage")  +"</p>";
							ergebnis+="<p><span>Ort: </span> "+ rs.getString("ort")+"</p>";
							ergebnis+="<p><span>PLZ: </span> "+ rs.getString("plz")+"</p>";
							ergebnis+="<p><span>Straße: </span> "+ rs.getString("strasse")+"</p>";
							ergebnis+="<p><span>Bundesland: </span> "+ rs.getString("bundesland")+"</p>";
							ergebnis+="<p><span>Land: </span> "+ rs.getString("land")+"</p>";

							
							ergebnis+="<p><span>Senden sie bitte mit der Angabe des Identifikators die Bewerbung an:</span> "+ rs.getString("UntID")  +"</p>";	

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
						
							ergebnis+="<h1 id='gefundeneStellen'>Gefundene Stellen</h1>";
							ergebnis+="<p><span>Das Unternehmen:</span> "+ rs.getString("untname")  +"</p>";	
							ergebnis+="<p><span>Identifikator der Stelle : </span>"+ rs.getString("Identifikator")  +"</p>";	
							ergebnis+="<p><span> Ihre Aufgaben sind: </span> "+ rs.getString("Aufgaben")  +"</p>";	
							ergebnis+="<p><span>Das sollten sie mitbringen:</span> "+ rs.getString("Qualifikationen")  +"</p>";	
							ergebnis+="<p><span>Die Dauer beträgt:</span> "+ rs.getString("dauer")  +"</p>";	
								
							ergebnis+="<p><span>Ihr Ansprechpartner/in: </span>"+ rs.getString("ansprechpartner")  +"</p>";	
							ergebnis+="<p><span>Die Abteilung </span>:  "+ rs.getString("abteilung")  +"</p>";		
							ergebnis+="<p><span>Das Praktikum fängt an am:</span>"+ rs.getString("ab")  +"</p>";
							ergebnis+="<p><span>Bei Rückfragen können Sie uns anrufen:</span> "+ rs.getString("telefon")  +"</p>";
							ergebnis+="<p><span>Mehr erfahren sie unter:</span> "+ rs.getString("homepage")  +"</p>";
							ergebnis+="<p><span>Ort: </span> "+ rs.getString("ort")+"</p>";
							ergebnis+="<p><span>PLZ: </span> "+ rs.getString("plz")+"</p>";
							ergebnis+="<p><span>Straße: </span> "+ rs.getString("strasse")+"</p>";
							ergebnis+="<p><span>Bundesland: </span> "+ rs.getString("bundesland")+"</p>";
							ergebnis+="<p><span>Land: </span> "+ rs.getString("land")+"</p>";

							
							ergebnis+="<p><span>Senden sie bitte mit der Angabe des Identifikators die Bewerbung an:</span> "+ rs.getString("UntID")  +"</p>";	

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
						
							ergebnis+="<h1 id='gefundeneStellen'>Gefundene Stellen</h1>";
							ergebnis+="<p><span>Das Unternehmen:</span> "+ rs.getString("untname")  +"</p>";	
							ergebnis+="<p><span>Identifikator der Stelle : </span>"+ rs.getString("Identifikator")  +"</p>";	
							ergebnis+="<p><span> Ihre Aufgaben sind: </span> "+ rs.getString("Aufgaben")  +"</p>";	
							ergebnis+="<p><span>Das sollten sie mitbringen:</span> "+ rs.getString("Qualifikationen")  +"</p>";	
							ergebnis+="<p><span>Die Dauer beträgt:</span> "+ rs.getString("dauer")  +"</p>";	
								
							ergebnis+="<p><span>Ihr Ansprechpartner/in: </span>"+ rs.getString("ansprechpartner")  +"</p>";	
							ergebnis+="<p><span>Die Abteilung </span>:  "+ rs.getString("abteilung")  +"</p>";		
							ergebnis+="<p><span>Das Praktikum fängt an am:</span>"+ rs.getString("ab")  +"</p>";
							ergebnis+="<p><span>Bei Rückfragen können Sie uns anrufen:</span> "+ rs.getString("telefon")  +"</p>";
							ergebnis+="<p><span>Mehr erfahren sie unter:</span> "+ rs.getString("homepage")  +"</p>";
							ergebnis+="<p><span>Ort: </span> "+ rs.getString("ort")+"</p>";
							ergebnis+="<p><span>PLZ: </span> "+ rs.getString("plz")+"</p>";
							ergebnis+="<p><span>Straße: </span> "+ rs.getString("strasse")+"</p>";
							ergebnis+="<p><span>Bundesland: </span> "+ rs.getString("bundesland")+"</p>";
							ergebnis+="<p><span>Land: </span> "+ rs.getString("land")+"</p>";

							
							ergebnis+="<p><span>Senden sie bitte mit der Angabe des Identifikators die Bewerbung an:</span> "+ rs.getString("UntID")  +"</p>";	
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
							ergebnis+="<h1 id='gefundeneStellen'>Gefundene Stellen</h1>";
							ergebnis+="<p><span>Das Unternehmen:</span> "+ rs.getString("untname")  +"</p>";	
							ergebnis+="<p><span>Identifikator der Stelle : </span>"+ rs.getString("Identifikator")  +"</p>";	
							ergebnis+="<p><span> Ihre Aufgaben sind: </span> "+ rs.getString("Aufgaben")  +"</p>";	
							ergebnis+="<p><span>Das sollten sie mitbringen:</span> "+ rs.getString("Qualifikationen")  +"</p>";	
							ergebnis+="<p><span>Die Dauer beträgt:</span> "+ rs.getString("dauer")  +"</p>";	
								
							ergebnis+="<p><span>Ihr Ansprechpartner/in: </span>"+ rs.getString("ansprechpartner")  +"</p>";	
							ergebnis+="<p><span>Die Abteilung </span>:  "+ rs.getString("abteilung")  +"</p>";		
							ergebnis+="<p><span>Das Praktikum fängt an am:</span>"+ rs.getString("ab")  +"</p>";
							ergebnis+="<p><span>Bei Rückfragen können Sie uns anrufen:</span> "+ rs.getString("telefon")  +"</p>";
							ergebnis+="<p><span>Mehr erfahren sie unter:</span> "+ rs.getString("homepage")  +"</p>";
							ergebnis+="<p><span>Ort: </span> "+ rs.getString("ort")+"</p>";
							ergebnis+="<p><span>PLZ: </span> "+ rs.getString("plz")+"</p>";
							ergebnis+="<p><span>Straße: </span> "+ rs.getString("strasse")+"</p>";
							ergebnis+="<p><span>Bundesland: </span> "+ rs.getString("bundesland")+"</p>";
							ergebnis+="<p><span>Land: </span> "+ rs.getString("land")+"</p>";

							
							ergebnis+="<p><span>Senden sie bitte mit der Angabe des Identifikators die Bewerbung an:</span> "+ rs.getString("UntID")  +"</p>";	

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
						
							ergebnis+="<h1 id='gefundeneStellen'>Gefundene Stellen</h1>";
							ergebnis+="<p><span>Das Unternehmen:</span> "+ rs.getString("untname")  +"</p>";	
							ergebnis+="<p><span>Identifikator der Stelle : </span>"+ rs.getString("Identifikator")  +"</p>";	
							ergebnis+="<p><span> Ihre Aufgaben sind: </span> "+ rs.getString("Aufgaben")  +"</p>";	
							ergebnis+="<p><span>Das sollten sie mitbringen:</span> "+ rs.getString("Qualifikationen")  +"</p>";	
							ergebnis+="<p><span>Die Dauer beträgt:</span> "+ rs.getString("dauer")  +"</p>";	
								
							ergebnis+="<p><span>Ihr Ansprechpartner/in: </span>"+ rs.getString("ansprechpartner")  +"</p>";	
							ergebnis+="<p><span>Die Abteilung </span>:  "+ rs.getString("abteilung")  +"</p>";		
							ergebnis+="<p><span>Das Praktikum fängt an am:</span>"+ rs.getString("ab")  +"</p>";
							ergebnis+="<p><span>Bei Rückfragen können Sie uns anrufen:</span> "+ rs.getString("telefon")  +"</p>";
							ergebnis+="<p><span>Mehr erfahren sie unter:</span> "+ rs.getString("homepage")  +"</p>";
							ergebnis+="<p><span>Ort: </span> "+ rs.getString("ort")+"</p>";
							ergebnis+="<p><span>PLZ: </span> "+ rs.getString("plz")+"</p>";
							ergebnis+="<p><span>Straße: </span> "+ rs.getString("strasse")+"</p>";
							ergebnis+="<p><span>Bundesland: </span> "+ rs.getString("bundesland")+"</p>";
							ergebnis+="<p><span>Land: </span> "+ rs.getString("land")+"</p>";

							
							ergebnis+="<p><span>Senden sie bitte mit der Angabe des Identifikators die Bewerbung an:</span> "+ rs.getString("UntID")  +"</p>";	

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
					
						ergebnis+="<h1 id='gefundeneStellen'>Gefundene Stellen</h1>";
						ergebnis+="<p><span>Das Unternehmen:</span> "+ rs.getString("untname")  +"</p>";	
						ergebnis+="<p><span>Identifikator der Stelle : </span>"+ rs.getString("Identifikator")  +"</p>";	
						ergebnis+="<p><span> Ihre Aufgaben sind: </span> "+ rs.getString("Aufgaben")  +"</p>";	
						ergebnis+="<p><span>Das sollten sie mitbringen:</span> "+ rs.getString("Qualifikationen")  +"</p>";	
						ergebnis+="<p><span>Die Dauer beträgt:</span> "+ rs.getString("dauer")  +"</p>";	
							
						ergebnis+="<p><span>Ihr Ansprechpartner/in: </span>"+ rs.getString("ansprechpartner")  +"</p>";	
						ergebnis+="<p><span>Die Abteilung </span>:  "+ rs.getString("abteilung")  +"</p>";		
						ergebnis+="<p><span>Das Praktikum fängt an am:</span>"+ rs.getString("ab")  +"</p>";
						ergebnis+="<p><span>Bei Rückfragen können Sie uns anrufen:</span> "+ rs.getString("telefon")  +"</p>";
						ergebnis+="<p><span>Mehr erfahren sie unter:</span> "+ rs.getString("homepage")  +"</p>";
						ergebnis+="<p><span>Ort: </span> "+ rs.getString("ort")+"</p>";
						ergebnis+="<p><span>PLZ: </span> "+ rs.getString("plz")+"</p>";
						ergebnis+="<p><span>Straße: </span> "+ rs.getString("strasse")+"</p>";
						ergebnis+="<p><span>Bundesland: </span> "+ rs.getString("bundesland")+"</p>";
						ergebnis+="<p><span>Land: </span> "+ rs.getString("land")+"</p>";

						
						ergebnis+="<p><span>Senden sie bitte mit der Angabe des Identifikators die Bewerbung an:</span> "+ rs.getString("UntID")  +"</p>";	

					}

				}
					System.out.println(ergebnis+"ist das ergebnissssssssss");
				System.out.println("Bin durch gegangen bei der Praktikumssuche");
				return ergebnis;
			} catch (Exception e) {
				System.out.println("Ein Fehler bei der Suche des Praktikums");
				return "Fehler";

			}
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	
}
