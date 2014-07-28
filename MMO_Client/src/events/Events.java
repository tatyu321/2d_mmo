package events;


import java.io.Serializable;

import events.Event_type;

public abstract class Events implements Serializable{
	
	public Event_type type;
	
	public Events(Event_type type){
		this.type=type;	
	}

}
