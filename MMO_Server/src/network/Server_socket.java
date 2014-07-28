package network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import main.Main;



public class Server_socket extends Thread{
		
	private Main control;
	private ServerSocket ss;
	
	
	public Server_socket(Main main){
		
		control=main;
		try {
			ss = new ServerSocket(42000);
		} catch (IOException e) {
			System.out.println("Socket is not created!");
		}
	}
	
	 public void run() {	
		while(true){
				try {
					
					System.out.println("Server waiting");
					Socket client = ss.accept(); 
					System.out.println("Incoming user");
		            (new Server_Thread(control, client)).start();
		            
			} catch (IOException e) {
				System.out.println("Error while connection!");
			}
		}      
	}
	
}
