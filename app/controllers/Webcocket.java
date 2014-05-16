package controllers;

import play.mvc.Controller;
import play.mvc.WebSocket;

import play.libs.F.Callback;
import play.libs.F.Callback0;

public class Webcocket extends Controller {

	public static WebSocket<String> websocket() {
		WebSocket<String> ws = null;
		
		
		ws = new WebSocket<String>() {
			public void onReady(WebSocket.In<String> in,
					final WebSocket.Out<String> out) {
				
				in.onMessage(new Callback<String>() {
					public void invoke(String g) {
						//auswerten aus DB 
						//es in write schreiben
						
						
						
						out.write("");
					
					}
				});
				
				in.onClose(new Callback0() {
					public void invoke() {
						System.out.println("Disconnected!");
						
					}
				});
			}
		};
		return ws;
	}

}
