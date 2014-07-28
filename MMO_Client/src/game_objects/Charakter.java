package game_objects;

import java.io.Serializable;

import game_objects.Charakter;
import game_objects.Charakter_class;

public class Charakter implements Serializable{
	private int x;
	private int y;
	private String name;
	
	private Charakter_class char_class;
	
	private Charakter nextChar;
	
	public Charakter(int startx, int starty, String name, Charakter_class char_class){
		x=startx;
		y=starty;
		this.name=name;
		this.char_class=char_class;
		nextChar=null;
	}

//***********List funktionen *************************
	
	public void insert_charakter(Charakter newSpieler){
		if (nextChar==null) nextChar=newSpieler;
		else nextChar.insert_charakter(newSpieler);
	}
	
	public Charakter get_charakter_name(String name){
		if (name.equals(this.name)) return this;
		if (nextChar==null) return null;
		return nextChar.get_charakter_name(name);
	}
	
	public Charakter get_char_by_position(int pos){
		if (pos==0) return this;
		if (nextChar==null) return null;
		pos--;
		return nextChar.get_char_by_position(pos);
	}
	
	public int count_charakters(){
		if (nextChar==null) return 1;
		return (nextChar.count_charakters()+1);
	}
	
	public int get_charakter_position(String name){
		if (name.equals(this.name)) return 0;
		if (nextChar==null) return -2147483648;
		return (nextChar.get_charakter_position(name)+1);
	}
	
	public boolean delete(Charakter vorheriges, int pos){
		if (pos==0){		
			vorheriges.set_next_charakter(nextChar);
			return true;
		}
		
		if (nextChar==null) return false;
		return nextChar.delete(this,pos-1);
	}
	
	public void set_next_charakter(Charakter next){
		nextChar=next;
	}
	
	public Charakter clone(){
		return new Charakter(x,y,name,char_class);
	}
	
//***********Spieler Funktion*******************	
	
	public int get_x(){
		return x;
	}
	
	public int get_y(){
		return y;
	}
	
	public void go_left(int schritte){
		x=x-1;
	}
	
	public void go_right(int schritte){
		x=x+schritte;
	}
	
	public void go_forward(int schritte){
		y=y-schritte;
	}
	
	public void go_back(int schritte){
		y=y+schritte;
	}
	
	


}
