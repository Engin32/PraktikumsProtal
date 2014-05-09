package DB;

import java.sql.*;

public class DBConnector {

	Statement stmt;
	ResultSet rs;
	Connection con;

	public DBConnector() {

		try {
			
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");
			System.out.println("alles in Ordnung");

		} catch (Exception ex) {
			System.out.println("Dieser Fehler ist aufgetreten: "
					+ ex.getMessage());

		}

	}
}
