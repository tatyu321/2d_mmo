package main;

import game_objects.Field;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Field_gui extends JFrame{
	
	private Field the_field;
	
	
	private JScrollPane gesamt_scrollpanel;
	
	
	
	public Field_gui(Field the_field){
		super();
		this.the_field=the_field;
		setSize(1024,768);
		
		init();
		
		this.setLayout(null);
                   
        setVisible(true);
	}
	
	private void init(){
		gesamt_scrollpanel = new JScrollPane();
		gesamt_scrollpanel.setSize(1024, 768);
    	gesamt_scrollpanel.setLocation(0, 0);
    	gesamt_scrollpanel.setBackground(Color.WHITE);
    	gesamt_scrollpanel.setVisible(true);
    	this.add(gesamt_scrollpanel);
		
	}

}
