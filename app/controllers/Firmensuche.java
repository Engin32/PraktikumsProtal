package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.sql.*;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class Firmensuche extends Controller {

	public static Result suche() throws SQLException {

		Map<String, String[]> a =request().body().asFormUrlEncoded();
		String daten = a.get("unternehmen")[0];
		
		String ergebnis = model.Model.getInstance().getFirmensuche().suche(daten);
		

	
		
		
		return ok(ergebnis);

	}

}