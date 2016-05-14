package DataLayer;

import DomainModel.EventTheatre;

public interface EventsRepository {
	
	public int insertEvent(EventTheatre ev);
	public EventTheatre selectEvent(String eventTitle);
	public int updateEvent(String title, EventTheatre newEv);
	public int deleteEvent(EventTheatre ev);
}
