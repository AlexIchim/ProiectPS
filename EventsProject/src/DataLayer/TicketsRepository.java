package DataLayer;

import java.util.List;

import DomainModel.EventTheatre;
import DomainModel.Ticket;

public interface TicketsRepository  {
	public int insertTicket(Ticket t);
	public List<Ticket> getTickets(EventTheatre e);
}
