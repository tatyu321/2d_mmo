package events;

import events.Events;

public class Move extends Events{
	private int links;
	private int rechts;
	private int vor;
	private int zurueck;
	
	
	public Move(Event_type Art) {
		super(Art);
		
	}
}
