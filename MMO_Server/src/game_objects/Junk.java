package game_objects;

import java.io.Serializable;

public class Junk implements Serializable{
	
	private int[][] background;
	private int[][] foreground;
	
	public Junk(){
		background = new int[100][100];
			array_init(background);
		foreground = new int[100][100];
			array_init(foreground);
	}
	
	private void array_init(int[][] array){
		for (int i=0; i<array.length;i++){
			for (int j=0; j<array[i].length;j++){
				array[i][j]=0;
			}
		}
	}

}
