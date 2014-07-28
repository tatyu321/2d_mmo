package main;

import game_objects.Account;
import game_objects.Charakter;
import game_objects.Charakter_class;
import game_objects.Field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import network.Server_Thread;
import network.Server_socket;
import BCrypt.BCrypt;
import DBMS.DBMS;

public class Main {
	
	
	private DBMS data_maintenance;
	private Server_socket Server;
	
	
	private Field the_field;
	private Account[] the_accounts;
	private Charakter the_aktiv_accounts;
	

	public static void main(String[] args) {		
		new Main();
	}
	
	
	public Main(){
		data_maintenance = new DBMS();
		the_accounts=data_maintenance.abfrage(Account.class);
		if (data_maintenance.abfrage(Field.class)!=null){
			the_field=data_maintenance.abfrage(Field.class)[0];
		}else{
			the_field=new Field();
		}
		
		Server = new Server_socket(this);
		Server.start();	
		
		
		//control Server by Console
		class Console_listener extends Thread{
			public void run(){
				while(true){
					BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
					try {
						String inputValue = in.readLine();
						
						if (inputValue.equals("test")){
							System.out.println("test_back");
						}
						
						if (inputValue.equals("Field")){
							
							new Field_gui(the_field);
						}
						
						
					} catch (IOException e) {
						System.out.println("No Matching");
						e.printStackTrace();
					}
				}
			}
		}
		new Console_listener().start();
	
	
	}
	
	private void set_charakter_aktiv(Charakter newChar){
		the_aktiv_accounts.insert_charakter(newChar);
	}
	
	public boolean set_charakter_inaktiv(int pos){
		if (pos==0) {
			the_aktiv_accounts.set_next_charakter(the_aktiv_accounts.get_char_by_position(1));
			return true;
		}
		return the_aktiv_accounts.delete(the_aktiv_accounts, pos);
	}
	
	public Account new_account(String name, String pw){
		if (the_accounts!=null){
			for (int i=0; i<the_accounts.length; i++){
				if (the_accounts[i].get_name().equals(name)) return null;
			}
		}
		
		Account newAccount = new Account(name, pw);
		data_maintenance.speichern(newAccount);
		return newAccount;
	}
	
	public Account login(String name, String pw){
		if (the_accounts==null) return null;
		for (int i=0; i<the_accounts.length; i++){
			if (the_accounts[i].get_name().equals(name)){
				if (the_accounts[i].check_login(name,pw)) return the_accounts[i];
				return null;
			}
		}
		return null;
	}
	
	public Charakter new_charakter(Account derAccount, String name, Charakter_class klasse){
		Charakter newChar = new Charakter(0,0,name,klasse);
		derAccount.Char_hinzu(newChar);
		return newChar;
	}

}
