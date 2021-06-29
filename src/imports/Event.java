package imports;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.time.Duration;
import java.time.LocalDateTime;
import java.lang.Math.*;

public abstract class Event {
	
	protected int id;
	
	//Constructor to create an Event object, an event will always have an id
	public Event(int id){	
			this.id = id;
	}
	
	
	//Getter and Setter for ID attribute
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	

	




}
