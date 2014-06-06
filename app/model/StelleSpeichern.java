package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import play.mvc.Http.Cookie;

public class StelleSpeichern {

	public boolean speichern(String bld, String ort, String str, String land,
			String plz, String stellennummer, String fakultät, String dauer,
			String ansprechparnter, String telefon, String aufgaben,
			String quali, String ab, String uname) {

		int dauer1 = Integer.parseInt(dauer);
		int telefon1 = Integer.parseInt(telefon);

		ResultSet rs;
		Connection con;
		Statement stmt;

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
			return true;

		} catch (Exception e) {
			System.out.println("Fehler bei der Stellenspeicherung."
					+ e.getMessage());
			return false;
		}

	}

}
