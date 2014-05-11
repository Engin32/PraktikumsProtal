package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import play.db.ebean.Model;
import play.mvc.Result;

public class Create {

	public static void erzeuge() {
		
		
		Statement stmt;
		ResultSet rs;
		Connection con;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");
			con.setAutoCommit(false);
			

			stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE `Praktikumsportal`.`Adresse` ( `adrID` INT NOT NULL, "
					+ "`land` VARCHAR(45) NOT NULL,"
					+ "`ort` VARCHAR(45) NOT NULL,"
					+ "`strasse` VARCHAR(45) NOT NULL,"
					+ "`plz` VARCHAR(45) NOT NULL,"
					+ "`bundesland` VARCHAR(45) NOT NULL,"
					+ "PRIMARY KEY (`adrID`));");
			
			
			stmt.executeUpdate("CREATE TABLE `Unternehmen` ("
					+ "`untID` int(11) NOT NULL,"
					+ "`untname` varchar(45) NOT NULL,"
					+ "`passwort` varchar(45) NOT NULL,"
					+ "`branche` varchar(45) NOT NULL,"
					+ "`telefon` varchar(45) NOT NULL,"
					+ "`homepage` varchar(45) NOT NULL,"
					+ "`adresse` int(11) NOT NULL,"
					+ "PRIMARY KEY (`untID`),"
					+ "KEY `adresse_idx` (`adresse`),"
					+ "CONSTRAINT `Adresse_Unternehmen` FOREIGN KEY (`adresse`) REFERENCES `Adresse` (`adrID`) ON DELETE NO ACTION ON UPDATE NO ACTION"
					+ ") ");
			
			
			
			
			
			stmt.executeUpdate("CREATE TABLE `Praktikumsportal`.`Stellenausschreibung` ("
					+ "`stellenID` INT NOT NULL,"
					+ "`beschreibung` VARCHAR(450) NOT NULL,"
					+ "`dauer` INT NOT NULL,"
					+ "`ansprechpartner` VARCHAR(45) NOT NULL,"
					+ "`telefon` INT NOT NULL,"
					+ "`abteilung` VARCHAR(45) NOT NULL,"
					+ "`von` INT NOT NULL,"
					+ "`adresse` INT NOT NULL,"
					+ "`ab` VARCHAR(45) NOT NULL,"
					+ "PRIMARY KEY (`stellenID`),"
					+ "INDEX `Stellen_Adresse_idx` (`adresse` ASC),"
					+ "INDEX `Stellen_Unternehmen_idx` (`von` ASC),"
					+ "CONSTRAINT `Stellen_Adresse`"
					+ "FOREIGN KEY (`adresse`)"
					+ "REFERENCES `Praktikumsportal`.`Adresse` (`adrID`)"
					+ "ON DELETE NO ACTION"
					+ "ON UPDATE NO ACTION,"
					+ "CONSTRAINT `Stellen_Unternehmen`"
					+ "FOREIGN KEY (`von`)"
					+ "REFERENCES `Praktikumsportal`.`Unternehmen` (`untID`)"
					+ "ON DELETE NO ACTION"
					+ "ON UPDATE NO ACTION);");
			
			
			
			
			
			

		} catch (Exception e) {

		}

	

	}

}
