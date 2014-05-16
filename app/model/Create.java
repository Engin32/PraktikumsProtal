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
					"jdbc:mysql://localhost:3306", "root", "");
			
			
			
			
			
		
			
			stmt = con.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS `Praktikumsportal`");
			
			// bei conf application ist praktikumsportal nicht mehr da nur localhost
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Praktikumsportal", "root", ""); 
			
			con.setAutoCommit(false);
			


			stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `Praktikumsportal`.`Adresse` ( "
					+ "`adrID` INT NOT NULL AUTO_INCREMENT, "
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
					+ "`adresse` int(11) NOT NULL,"       // hier aufpassen immer die abrID mit sql statement holen und dann einfügen
					+ "PRIMARY KEY (`untID`),"
					+ "KEY `adresse_idx` (`adresse`),"
					+ "CONSTRAINT `Adresse_Unternehmen` FOREIGN KEY (`adresse`) REFERENCES `Adresse` (`adrID`) ON DELETE NO ACTION ON UPDATE NO ACTION"
					+ ") ");
			
			
			
			
			
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `Praktikumsportal`.`Stellenausschreibung` ("
					+ "`stellenID` INT NOT NULL AUTO_INCREMENT,"
					+ "`beschreibung` VARCHAR(450) NOT NULL,"
					+ "`dauer` INT NOT NULL,"
					+ "`ansprechpartner` VARCHAR(45) NOT NULL,"
					+ "`telefon` INT NOT NULL,"
					+ "`abteilung` VARCHAR(45) NOT NULL,"
					+ "`von` varchar(45) NOT NULL,"
					+ "`adresse` INT NOT NULL ,"       // hier aufpassen immer die abrID mit sql statement holen und dann einfügen
					+ "`ab` VARCHAR(45) NOT NULL,"
					+ "PRIMARY KEY (`stellenID`),"
					+ "INDEX `Stellen_Adresse_idx` (`adresse` ASC),"
					+ "INDEX `Stellen_Unternehmen_idx` (`von` ASC),"
					+ "CONSTRAINT `Stellen_Adresse` FOREIGN KEY (`adresse`) REFERENCES `Adresse` (`adrID`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
					+ "CONSTRAINT `Stellen_Unternehmen` FOREIGN KEY (`von`) REFERENCES `Praktikumsportal`.`Unternehmen` (`untID`) ON DELETE NO ACTION ON UPDATE NO ACTION);");
			
			con.commit();
			
			System.out.println("Bin hier durchgegeangen!!!!");
			
			

		} catch (Exception e) {
			System.out.println("Folgendes Problem: " + e.getMessage() );

		}

	

	}

}
