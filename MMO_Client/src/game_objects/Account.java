package game_objects;

import java.io.Serializable;

import game_objects.Charakter;
import BCrypt.BCrypt;

public class Account implements Serializable{
	
	private String name;
	private String pw;
	private Charakter[] charakters;
	
	public Account(String name, String clearpw){
		this.name=name;
		String hashw = BCrypt.hashpw(clearpw, "$2a$10$1tjJ32aihFFX5ST2Y9L6W.");
		this.pw = hashw;
		charakters = new Charakter[0];
	}
	
	public boolean check_login(String name, String Klartextpw){
		String pw = BCrypt.hashpw(Klartextpw, "$2a$10$1tjJ32aihFFX5ST2Y9L6W.");
		if ((this.name.equals(name))&&(this.pw.equals(pw))) return true;
		return false;
	}
	
	public void Char_hinzu(Charakter newCharakter){
		Charakter[] neuesArray = new Charakter[charakters.length];
		for (int i=0; i<charakters.length;i++){
			neuesArray[i]=charakters[i];
		}
		neuesArray[charakters.length]=newCharakter;
		charakters=neuesArray;
	}
	
	public String get_name(){
		return name;
	}
}
