package controllers;

import java.sql.*;
import java.util.Observable;
import java.util.Observer;

import play.mvc.Controller;
import play.mvc.WebSocket;
import play.libs.F.Callback;
import play.libs.F.Callback0;

public class Websocket extends Controller implements Observer {

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

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("hallo " + arg1);
	}

}
