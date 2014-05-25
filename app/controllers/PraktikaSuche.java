package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;

public class PraktikaSuche extends Controller {
	
	
public static Result praktikaSuche(){
	int dauer1 = 0;
	String beliebig ="beliebig";
	
	
	//Werte aus der URL holen
	String fakultät=request().getQueryString("fakultät");
	String bundesland=request().getQueryString("bundesland");
	String stadt=request().getQueryString("stadt");
	String dauer= request().getQueryString("dauer");
	if(!dauer.equals(beliebig)){
	 dauer1 =Integer.parseInt(dauer);
	}
	
	//String größe=request().getQueryString("größe");
	String unternehmen=request().getQueryString("unternehmen");
	String anfang=request().getQueryString("anfang");

	
	
	
	System.out.println(bundesland+fakultät+stadt+dauer1+dauer+anfang);
	
	
	
	//In der db vergleichen
	ResultSet rs;
	Connection con;
	Statement stmt;
	String ergebnis = "Kein Ergebnis";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/Praktikumsportal", "root", "");
		
		
		stmt =con.createStatement();
		rs = stmt.executeQuery("select * from Stellenausschreibung, Adresse, Unternehmen wehre  ");
		
		
		
		
		
		

	} catch (Exception e) {

	}

	
	
	
	
	
	
	
	
	
return ok(ergebnis);
	
	
	
	
	
	
	
}
	
	

}
