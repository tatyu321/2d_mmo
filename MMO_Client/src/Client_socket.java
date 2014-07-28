import java.awt.Label;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import events.Login;
import events.Register;
import game_objects.Account;



public class Client_socket{
	
	private Socket so;
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private String IP = "127.0.0.1";
	
	
	public Client_socket(){
		try {
			so = new Socket( IP, 42000 ) ;
			out = new ObjectOutputStream(so.getOutputStream()) ; 
			in = new ObjectInputStream(so.getInputStream()) ;		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Account login(Login data){
		try {
			out.writeObject(data);
			return (Account) in.readObject();
		} catch (Exception e) {
			System.out.println("Fehler!");
			return null;
		}finally{
			try {
				so.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public Account register(Register data){
		try {
			out.writeObject(data);
			return (Account) in.readObject();
		} catch (Exception e) {
			System.out.println("Fehler!");
			return null;
		}finally{
			try {
				so.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
