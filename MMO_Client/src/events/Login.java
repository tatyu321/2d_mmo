package events;

import events.Events;

public class Login extends Events{

	public String name;
	public String password;

	public Login(Event_type Art, String name, String password) {
		super(Art);
		this.name=name;
		this.password=password;
	}


}
