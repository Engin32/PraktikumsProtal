package controllers;

import java.sql.*;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import play.mvc.Controller;
import play.mvc.WebSocket;
import play.mvc.WebSocket.Out;
import play.libs.F.Callback;
import play.libs.F.Callback0;

public class Websocket extends Controller implements Observer {

	private static HashSet<WebSocket.Out<String>> outs = new HashSet<WebSocket.Out<String>>();

	public static WebSocket<String> websocket() {

		System.out.println("hallo1");

		WebSocket<String> ws = null;
		ws = new WebSocket<String>() {

			public void onReady(WebSocket.In<String> in,
					final WebSocket.Out<String> out) {

				System.out.println("----------websocket----------");
				outs.add(out);
				out.write("");

			}

		};

		return ws;
	}

	public void registrieren() {
		System.out.println("---------registriert---------------");
		model.Model.getInstance().addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("---------update---------- " + arg1);
		for (WebSocket.Out<String> out : outs) {
			out.write("");
		}

	}

}
