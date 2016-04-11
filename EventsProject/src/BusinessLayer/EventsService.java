package BusinessLayer;

import java.sql.Connection;

import DataLayer.EventsDAOMySQL;
import DataLayer.MySqlConnection;
import DomainModel.EventTheatre;

public class EventsService {

	private EventsDAOMySQL eD;
	
	public EventsService() {
		Connection connection = MySqlConnection.openConnection("clujT", "root", "");
		this.eD = new EventsDAOMySQL(connection);
	}
	
	public boolean insertEvent(EventTheatre ev) {
		
		if (eD.insertEvent(ev) != 0)
			return true;
		return false;
	}
	
	public EventTheatre selectEvent(String eventTitle) {
		
		if (eD.selectEvent(eventTitle) != null) {
			return eD.selectEvent(eventTitle);
		}
		
		return null;
	}
	
	
	public boolean updateEvent (String eventTitle, EventTheatre evNew) {
		
		EventTheatre ev = evNew;;
		if (evNew.getEventTitle() == "")
		{	
			ev.setEventTitle(eD.selectEvent(eventTitle).getEventTitle());
		}
		
		if (evNew.getEventDirector() == "")
		{	
			ev.setEventDirector(eD.selectEvent(eventTitle).getEventDirector());
		}
		
		if (evNew.getEventDistribution() == "")
		{	
			ev.setEventDistribution(eD.selectEvent(eventTitle).getEventDistribution());
			
		}
		
		if (evNew.getEventReleaseDate() == "")
		{	
			ev.setEventReleaseDate(eD.selectEvent(eventTitle).getEventReleaseDate());
		}
		
		if (evNew.getEventTickets() == 0)
		{	
			ev.setEventTickets(eD.selectEvent(eventTitle).getEventTickets());
		}
		
		if (eD.updateEvent(eventTitle, evNew) != 0) {
			return true;
		}
		return false;
	}
	
	public boolean deleteEvent(EventTheatre ev) {
		
		if (eD.deleteEvent(ev) != -1) {
			return true;
		}
		
		return false;
	}

}
