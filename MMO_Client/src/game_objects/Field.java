package game_objects;

import java.io.Serializable;

public class Field implements Serializable{
	
	private Junk [][] the_junks;
		
	public Field(){
		the_junks = new Junk[100][100];
	}
	
}

