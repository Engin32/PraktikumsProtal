package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.codec.digest.DigestUtils;

import views.html.afterloginUnternehmen;

public class Registrierung {

	public boolean registrieren(String uname, String branche, String passsw,
			String tel, String email, String land, String ort, String str,
			String plz, String bld, String homepage) {

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

			String hashtext = DigestUtils.md5Hex(passsw);

			ps = con.prepareStatement("INSERT INTO Praktikumsportal.Unternehmen"
					+ "(untID,untname,passwort,branche,telefon,homepage,adresse)"
					+ "VALUES" + "(?,?,?,?,?,?,?);");

			ps.setString(1, email);
			ps.setString(2, uname);
			ps.setString(3, hashtext);
			ps.setString(4, branche);
			ps.setString(5, tel);
			ps.setString(6, homepage);
			ps.setInt(7, i);

			ps.executeUpdate();

			System.out.println("In db eingefügt");
			con.commit();

			System.out.println("--------------REGISTRIERT--------------");
			return true;

		} catch (Exception ex) {
			System.out.println("Dieser Fehler ist aufgetreten: "
					+ ex.getMessage());
			return false;

		}

	}

}
