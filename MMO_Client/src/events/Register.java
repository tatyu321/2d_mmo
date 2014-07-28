package events;

import events.Events;

public class Register extends Events{
	public String name;
	public String password;
	
	public Register(Event_type Art, String name, String password){
		super(Art);
		this.name=name;
		this.password=password;
	}

}
