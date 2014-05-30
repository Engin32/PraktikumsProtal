package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.codec.digest.DigestUtils;

import play.db.ebean.Model;
import play.mvc.Result;

public class CreateDatabase {

	public static void erzeuge() {

		Statement stmt;
		ResultSet rs;
		Connection con;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306",
					"root", "");

			stmt = con.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS `Praktikumsportal`");

			// bei conf application ist praktikumsportal nicht mehr da nur
			// localhost
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");

			con.setAutoCommit(false);

			stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `Praktikumsportal`.`Adresse` ( "
					+ "`adrID` INT NOT NULL  AUTO_INCREMENT, "
					+ "`land` VARCHAR(45) NOT NULL,"
					+ "`ort` VARCHAR(45) NOT NULL,"
					+ "`strasse` VARCHAR(45) NOT NULL,"
					+ "`plz` VARCHAR(45) NOT NULL,"
					+ "`bundesland` VARCHAR(45) NOT NULL,"
					+ "PRIMARY KEY (`adrID`));");

			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `Unternehmen` ("
					+ "`untID` varchar(45) NOT NULL,"
					+ "`untname` varchar(45) NOT NULL,"
					+ "`passwort` varchar(45) NOT NULL,"
					+ "`branche` varchar(45) NOT NULL,"
					+ "`telefon` varchar(45) NOT NULL,"
					+ "`homepage` varchar(45) NOT NULL,"
					+ "`adresse` int(11) NOT NULL," // hier aufpassen immer die
													// abrID mit sql statement
													// holen und dann einfügen
					+ "PRIMARY KEY (`untID`),"
					+ "KEY `adresse_idx` (`adresse`),"
					+ "CONSTRAINT `Adresse_Unternehmen` FOREIGN KEY (`adresse`) REFERENCES `Adresse` (`adrID`) ON DELETE NO ACTION ON UPDATE NO ACTION"
					+ ") ");

			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `Praktikumsportal`.`Stellenausschreibung` ("
					+ "`stellenID` INT NOT NULL  AUTO_INCREMENT,"
					+ "`Aufgaben` VARCHAR(1000) NOT NULL,"
					+ "`Qualifikationen` VARCHAR(1000) NOT NULL,"
					+ "`dauer` INT NOT NULL,"
					+ "`Identifikator` VARCHAR(30) NOT NULL,"
					+ "`ansprechpartner` VARCHAR(45) NOT NULL,"
					+ "`telefon` INT NOT NULL,"
					+ "`abteilung` VARCHAR(45) NOT NULL,"
					+ "`von` varchar(45) NOT NULL,"
					+ "`adresse` INT NOT NULL ," // hier aufpassen immer die
													// abrID mit sql statement
													// holen und dann einfügen
					+ "`ab` VARCHAR(45) NOT NULL,"
					+ "PRIMARY KEY (`stellenID`),"
					+ "INDEX `Stellen_Adresse_idx` (`adresse` ASC),"
					+ "INDEX `Stellen_Unternehmen_idx` (`von` ASC),"
					+ "CONSTRAINT `Stellen_Adresse` FOREIGN KEY (`adresse`) REFERENCES `Adresse` (`adrID`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
					+ "CONSTRAINT `Stellen_Unternehmen` FOREIGN KEY (`von`) REFERENCES `Praktikumsportal`.`Unternehmen` (`untID`) ON DELETE NO ACTION ON UPDATE NO ACTION);");

			rs = stmt
					.executeQuery("select Praktikumsportal.Adresse.adrID "
							+ "from Praktikumsportal.Adresse where land='Deutschland' and "
							+ "ort='Stuttgart' and strasse='Daimlerstraße 10' and "
							+ "plz='1234'and bundesland='Baden-Würtemberg'");
			
			
			System.out.println("Bin da da da da da");

			int counter8 = 0;
			while (rs.next()) {

				counter8++;

			}
			System.out.println("Bin da da da da da2");

			if (counter8 == 0) {
				System.out.println("Bin da da da da da3");

				/*
				 * ############################################################
				 * ################ Unternehmen einfügen Daimler###############
				 * ############################################################
				 */

				stmt.executeUpdate("INSERT INTO Praktikumsportal.Adresse "
						+ "(land,ort,strasse,plz,bundesland)"
						+ "VALUES"
						+ "('Deutschland','Stuttgart','Daimlerstraße 10','1234','Baden-Würtemberg');");

				rs = stmt
						.executeQuery("select Praktikumsportal.Adresse.adrID "
								+ "from Praktikumsportal.Adresse where land='Deutschland' and "
								+ "ort='Stuttgart' and strasse='Daimlerstraße 10' and "
								+ "plz='1234'and bundesland='Baden-Würtemberg'");

				/*
				 * falls eine adresse doppelt gespeichert ist, stellen wir jetzt
				 * sicher, dass man auf den erst gefundenen Eintrag verweist
				 * falls unternehmen tatsächlich eine gleiche adresse haben
				 * sollte, dann verweisen beide auf die selbe adresse
				 */

				int i1 = 0;
				int counter1 = 0;
				while (rs.next()) {
					if (counter1 == 0) {
						i1 = rs.getInt(1);
						counter1++;
					} else {
						break;
					}

				}

				String hashtext = DigestUtils.md5Hex("Daimler");

				PreparedStatement ps1 = con
						.prepareStatement("INSERT INTO Praktikumsportal.Unternehmen"
								+ "(untID,untname,passwort,branche,telefon,homepage,adresse)"
								+ "VALUES"
								+ "('hr@daimler.de','Daimler',?,'Automobil','12345','www.daimler.de',?);");

				ps1.setString(1, hashtext);
				ps1.setInt(2, i1);
				ps1.executeUpdate();
				/*
				 * ############################################################
				 * ################Stelle einfügen NR:1 für Daimler############
				 * ############################################################
				 */

				stmt.executeUpdate("INSERT INTO Praktikumsportal.Adresse "
						+ "(land,ort,strasse,plz,bundesland)"
						+ "VALUES"
						+ "('Deutschland','Rastatt','Mercedesstraße 1','76437','Baden-Würtemberg');");

				rs = stmt
						.executeQuery("select Praktikumsportal.Adresse.adrID "
								+ "from Praktikumsportal.Adresse where land='Deutschland' and "
								+ "ort='Rastatt' and strasse='Mercedesstraße 1' and "
								+ "plz='76437'and bundesland='Baden-Würtemberg'");

				int i4 = 0;
				int counter4 = 0;
				while (rs.next()) {
					if (counter4 == 0) {
						i4 = rs.getInt(1);
						counter4++;
					} else {
						break;
					}

				}

				PreparedStatement ps4 = con
						.prepareStatement("INSERT INTO Praktikumsportal.Stellenausschreibung"
								+ "(Aufgaben,Qualifikationen,dauer,Identifikator,ansprechpartner,telefon,abteilung,von,adresse,ab)"
								+ "VALUES"
								+ "('Unterstützung bei der Entwicklung von strategischen und operativen Konzepten.Mitwirkung bei der Koordination, Vor- und Nachbereitung und Durchführung von Projektleitersitzungen inklusive der Erstellung relevanter Dokumentationen.Unterstützung bei der Planung, Vorbereitung, Durchführung und Nachbereitung von Projekt-Workshops',"
								+ "'Studiengang: Studium der Informatik, Wirtschaftsinformatik, Betriebswirtschaft oder vergleichbare Ausbildung.IT-Kenntnisse: Kenntnisse der Daimler IT-Organisation (Strukturen, Abläufe, Organisation) von Vorteil.Persönliche Kompetenzen: Selbständige und zielgerichtete Arbeitsweise.Sprachkenntnisse: Sehr gute Englisch Kenntnisse, weitere Fremdsprache von Vorteil; Gute Kenntnisse in Projekt Management (Houston); Sehr gute MS-Office-Kenntnisse.',"
								+ "6,'D1','Frau Maier',1293084084,'IT','hr@daimler.de',?,'01.09.2014');");

				ps4.setInt(1, i4);
				ps4.executeUpdate();

				/*
				 * ############################################################
				 * ################Stelle einfügen NR:2 für Daimler############
				 * ############################################################
				 */

				stmt.executeUpdate("INSERT INTO Praktikumsportal.Adresse "
						+ "(land,ort,strasse,plz,bundesland)"
						+ "VALUES"
						+ "('Deutschland','Düsseldorf','Ratherstraße 51','40476','Nordrhein-Westfalen');");

				rs = stmt
						.executeQuery("select Praktikumsportal.Adresse.adrID "
								+ "from Praktikumsportal.Adresse where land='Deutschland' and "
								+ "ort='Düsseldorf' and strasse='Ratherstraße 51' and "
								+ "plz='40476'and bundesland='Nordrhein-Westfalen'");

				int i5 = 0;
				int counter5 = 0;
				while (rs.next()) {
					if (counter5 == 0) {
						i5 = rs.getInt(1);
						counter5++;
					} else {
						break;
					}

				}

				PreparedStatement ps5 = con
						.prepareStatement("INSERT INTO Praktikumsportal.Stellenausschreibung"
								+ "(Aufgaben,Qualifikationen,dauer,Identifikator,ansprechpartner,telefon,abteilung,von,adresse,ab)"
								+ "VALUES"
								+ "('Weiterentwicklung von Methoden & Prozessen für Projekt- und Programmmanagement.Ausgestalten von Workshops für die internationale Program- und Projekt Management Community.Operatives Programm- und Projekt Management im Programm Management Office.Support beim Release Management der PM Prozesse und Methoden',"
								+ "'Studiengang: Wirtschaftsinformatik oder Betriebswirtschaftslehre mit dem Schwerpunkten Business Process Management oder Programm und Projekt Management.Sprachkenntnisse: Sehr gute Deutsch- und Englischkenntnisse in Wort und Schrift.Persönliche Kompetenzen: Analytisches Denken, Teamfähigkeit und Bereitschaft zum selbständigen Arbeiten',"
								+ "6,'D2','Frau Rau',234522144,'BWL','hr@daimler.de',?,'01.09.2014');");

				ps5.setInt(1, i5);
				ps5.executeUpdate();

				/*
				 * ############################################################
				 * ############ nächstes Unternehmen einfügen BMW##############
				 * ############################################################
				 */

				stmt.executeUpdate("INSERT INTO Praktikumsportal.Adresse "
						+ "(land,ort,strasse,plz,bundesland)"
						+ "VALUES"
						+ "('Deutschland','München','BMWstraße 123','4321','Bayern');");

				rs = stmt
						.executeQuery("select Praktikumsportal.Adresse.adrID "
								+ "from Praktikumsportal.Adresse where land='Deutschland' and "
								+ "ort='München' and strasse='BMWstraße 123' and "
								+ "plz='4321'and bundesland='Bayern'");
				int i2 = 0;
				int counter2 = 0;
				while (rs.next()) {
					if (counter2 == 0) {
						i2 = rs.getInt(1);
						counter2++;
					} else {
						break;
					}

				}

				hashtext = DigestUtils.md5Hex("Bmw");

				PreparedStatement ps2 = con
						.prepareStatement("INSERT INTO Praktikumsportal.Unternehmen"
								+ "(untID,untname,passwort,branche,telefon,homepage,adresse)"
								+ "VALUES"
								+ "('hr@bmw.de','BMW',?,'Automobil','54321','www.bmw.de',?);");

				ps2.setString(1, hashtext);
				ps2.setInt(2, i2);
				ps2.executeUpdate();
				/*
				 * ############################################################
				 * ################Stelle einfügen NR:1 für BMW############
				 * ############################################################
				 */

				stmt.executeUpdate("INSERT INTO Praktikumsportal.Adresse "
						+ "(land,ort,strasse,plz,bundesland)"
						+ "VALUES"
						+ "('Deutschland','Leipzig','Werkstraße 51','34543','Sachsen');");

				rs = stmt
						.executeQuery("select Praktikumsportal.Adresse.adrID "
								+ "from Praktikumsportal.Adresse where land='Deutschland' and "
								+ "ort='Leipzig' and strasse='Werkstraße 51' and "
								+ "plz='34543'and bundesland='Sachsen'");

				int i6 = 0;
				int counter6 = 0;
				while (rs.next()) {
					if (counter6 == 0) {
						i6 = rs.getInt(1);
						counter6++;
					} else {
						break;
					}

				}

				PreparedStatement ps6 = con
						.prepareStatement("INSERT INTO Praktikumsportal.Stellenausschreibung"
								+ "(Aufgaben,Qualifikationen,dauer,Identifikator,ansprechpartner,telefon,abteilung,von,adresse,ab)"
								+ "VALUES"
								+ "('Während des Praktikums erhalten Sie einen groben Überblick der umfangreichen Toolslandschaft des Fachbereichs Grundauslegung Fahrdynamik. Automatisierung und Vernetzung ist ein bedeutender Aspekt für IT-Systeme und benötigt zukunftsorientierte Systemarchitekturen und Technologien. Hierzu können Sie Themenvorschläge einbringen und Ihre konstruktiven Ideen prototypisch verwirklichen.',"
								+ "'Studium der Informatik oder ein vergleichbarer Studiengang. Zweite Hälfte Bachelor-, Master- oder Diplomstudiengang.Erste praktische Erfahrung im Bereich der Softwareentwicklung.- Kenntnisse im Programmieren Design Pattern, Software-Architektur mit Datenbankbezug.- Sichere Umgang mit MS Office.',"
								+ "6,'B1','Herr Meer',234568721,'IT','hr@bmw.de',?,'01.10.2014');");

				ps6.setInt(1, i6);
				ps6.executeUpdate();

				/*
				 * ############################################################
				 * ############ nächstes Unternehmen einfügen
				 * Bayer##################
				 * ############################################################
				 */

				stmt.executeUpdate("INSERT INTO Praktikumsportal.Adresse "
						+ "(land,ort,strasse,plz,bundesland)"
						+ "VALUES"
						+ "('Deutschland','Leverkusen','bayerstraße 12','98432','Nordrhein-Westfalen');");

				rs = stmt
						.executeQuery("select Praktikumsportal.Adresse.adrID "
								+ "from Praktikumsportal.Adresse where land='Deutschland' and "
								+ "ort='Leverkusen' and strasse='bayerstraße 12' and "
								+ "plz='98432'and bundesland='Nordrhein-Westfalen'");

				int i3 = 0;
				int counter3 = 0;
				while (rs.next()) {
					if (counter3 == 0) {
						i3 = rs.getInt(1);
						counter3++;
					} else {
						break;
					}

				}

				hashtext = DigestUtils.md5Hex("Bayer");

				PreparedStatement ps3 = con
						.prepareStatement("INSERT INTO Praktikumsportal.Unternehmen"
								+ "(untID,untname,passwort,branche,telefon,homepage,adresse)"
								+ "VALUES"
								+ "('hr@bayer.de','Bayer',?,'Arzneimittel','234234','www.bayer.de',?);");
				ps3.setString(1, hashtext);
				ps3.setInt(2, i3);
				ps3.executeUpdate();

				/*
				 * ############################################################
				 * ################Stelle einfügen NR:1 für Bayer ############
				 * ############################################################
				 */

				stmt.executeUpdate("INSERT INTO Praktikumsportal.Adresse "
						+ "(land,ort,strasse,plz,bundesland)"
						+ "VALUES"
						+ "('Schweiz','Zürich','Züricherstraße 32','9213','Zürich');");

				rs = stmt
						.executeQuery("select Praktikumsportal.Adresse.adrID "
								+ "from Praktikumsportal.Adresse where land='Schweiz' and "
								+ "ort='Zürich' and strasse='Züricherstraße 32' and "
								+ "plz='9213'and bundesland='Zürich'");

				int i7 = 0;
				int counter7 = 0;
				while (rs.next()) {
					if (counter7 == 0) {
						i7 = rs.getInt(1);
						counter7++;
					} else {
						break;
					}

				}

				PreparedStatement ps7 = con
						.prepareStatement("INSERT INTO Praktikumsportal.Stellenausschreibung"
								+ "(Aufgaben,Qualifikationen,dauer,Identifikator,ansprechpartner,telefon,abteilung,von,adresse,ab)"
								+ "VALUES"
								+ "('Als Praktikant bei Business Consulting bekommen Sie die Möglichkeit, in einem Zeitraum von mindestens drei Monaten die Arbeit und die Kollegen der internen Managementberatung intensiv kennenzulernen. Sie erhalten einen fundierten Einblick in den Beratungsalltag und können sich gleichzeitig mit Ihrer Kompetenz und Persönlichkeit einbringen. Praktikanten unterstützen als vollwertiges Teammitglied ein oder mehrere Beraterprojekte',"
								+ "'Studium der Wirtschafts-, Ingenieur- oder Naturwissenschaften.ausgezeichnete akademische Leistungen.erste Praxis- und Auslandserfahrung, beispielsweise durch Praktika, Auslandssemester. herausragende analytische Fähigkeiten',"
								+ "6,'Bayer1','Herr Werkmann',842987123,'BWL','hr@bayer.de',?,'01.03.2015');");

				ps7.setInt(1, i7);
				ps7.executeUpdate();

			}

			con.commit();
			System.out.println("Bin hier durchgegeangen!!!!");

		} catch (Exception e) {
			System.out.println("Folgendes Problem: " + e.getMessage());

		}

	}

}
