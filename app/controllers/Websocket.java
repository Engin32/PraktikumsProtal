package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

import play.mvc.Controller;
import play.mvc.WebSocket;
import play.libs.F.Callback;
import play.libs.F.Callback0;

public class Websocket extends Controller {

	public static WebSocket<String> websocket() {

		System.out.println("hallo1");

		 

		WebSocket<String> ws = null;
		ws = new WebSocket<String>() {
			public void onReady(WebSocket.In<String> in,
					final WebSocket.Out<String> out) {
				//wenn ein insert getätigt wird, wird diese Methode aufgerufen
				//Schickt nichts, sorgt nur das die Funktion recieveEvent() aufgerufen wird
				//recieveEvent ruft dann die methode laden() auf!!!
				
				out.write("");
				
				

			}
		};

		return ws;
	}

}




