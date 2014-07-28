package game_objects;

import java.io.Serializable;

public class Field implements Serializable{
	
	private int[][] background;
	private int[][] foreground;
	
	public Field(){
		background = new int[10000][10000];
			array_init(background);
		foreground = new int[10000][10000];
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
