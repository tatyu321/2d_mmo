package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import main.Main;
import events.Login;
import events.Events;
import events.Event_type;
import events.Register;
import game_objects.Account;
import game_objects.Charakter;

public class Server_Thread extends Thread{
	
	
	private Main control;
	private Socket client;
	private boolean stopped;
	private Charakter charakter;
	private Account account;
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	Server_Thread(Main Strg, Socket client){
		control = Strg;
		this.client = client;
		stopped=false;
		charakter=null;
		account=null;
	}
	
	public void run() {
		
		Object input;		
			
		try {
			out = new ObjectOutputStream(client.getOutputStream()) ; 
			in = new ObjectInputStream(client.getInputStream()) ;
			input = in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			input=null;
		}	
				
		try {
			out.writeObject(request_handler(input));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  		      
    } 
	
	public void socket_close() throws IOException{
		stopped=true;
		client.close();
	}
	
	private Object request_handler(Object input){
		Events the_event = (Events) input;	
		try{
			switch (the_event.type){
			
				case register: 	return register(the_event);
			
				case login:		return login(the_event);
									
				case move:		return null;
									
				default: 			return null;
			
			}
		}catch (IOException e) {
			System.out.println("Connection failed!!!!!");
			return null;
		}
	}
	
	
	private Object register(Events the_event) throws IOException{
		Register the_new_account = (Register) the_event;
		account=control.new_account(the_new_account.name, the_new_account.password);
		if (account==null){
			return null;
		}
		else{
			return account;
		}
	}
	
	private Object login(Events the_event) throws IOException{
		Login the_login = (Login) the_event;
		account=control.login(the_login.name, the_login.password);
		if (account==null){
			return null;
		}
		else{
			return account;
		}
	}
	
	

}
