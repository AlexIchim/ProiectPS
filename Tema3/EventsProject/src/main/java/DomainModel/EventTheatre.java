package DomainModel;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EventTheatre {

	private String eventTitle;
	private String eventDirector;
	private String eventDistribution;
	private String eventReleaseDate;
	private int eventTickets;
	
	public EventTheatre(String eT,String eDir, String eDis, String date, int tick) {
		this.eventTitle = eT;
		this.eventDirector = eDir;
		this.eventDistribution = eDis;
		this.eventReleaseDate = date;
		this.eventTickets = tick;

	}
	
	
	public String getEventDistribution() {
		return eventDistribution;
	}
	
	public void setEventDistribution(String eventDistribution) {
		this.eventDistribution = eventDistribution;
	}

	public String getEventDirector() {
		return eventDirector;
	}

	public void setEventDirector(String eventDirector) {
		this.eventDirector = eventDirector;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}


	public String getEventReleaseDate() {
		return eventReleaseDate;
	}


	public void setEventReleaseDate(String date) {
		this.eventReleaseDate = (String) date;
	}
	
	/*
	public String getEventDateTime() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return dateFormat.format(eventReleaseDate);
	}
	*/
	
	public int getEventTickets() {
		return eventTickets;
	}


	public void setEventTickets(int eventTickets) {
		this.eventTickets = eventTickets;
	}
	
	@Override
	public String toString() {
		String str = "Title: " + this.getEventTitle() + "\nDirector: " + this.getEventDirector() + "\nDisribution: " + this.getEventDistribution() + "\nReleaseDate: " + this.getEventReleaseDate() + "\nTicketsRemaining: " + this.getEventTickets() + "\n\n\n";
		return str;
	}
}
