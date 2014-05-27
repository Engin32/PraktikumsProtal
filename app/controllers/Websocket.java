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


			

				out.write("");

				

			}
		};

		return ws;
	}

}




