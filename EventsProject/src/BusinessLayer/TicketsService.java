package BusinessLayer;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

import DataLayer.MySqlConnection;
import DataLayer.TicketsDAOMySQL;
import DomainModel.EventTheatre;
import DomainModel.Ticket;


public class TicketsService {
	
	private TicketsDAOMySQL tD;

	public TicketsService () {
		Connection connection = MySqlConnection.openConnection("clujt", "root", "");
		this.tD = new TicketsDAOMySQL(connection);
	}
	
	public int addBoughtTickets(Ticket t) {
		List<Ticket> lT = tD.getTickets(t.getTicketEvent());
		lT.size();
		if (tD.existTicket(t)) {
			if (lT.size() < t.getTicketEvent().getEventTickets()) {
				if (tD.insertTicket(t) != 0);
					return 1;
			}
			else {
				return -1;
			}
		}
		return 0;
		
	}
	
	public List<Ticket> getTicketsList(EventTheatre ev) {
		
		
		if (!tD.getTickets(ev).isEmpty()) {
			List<Ticket> ticketList = tD.getTickets(ev);
			for (Ticket t : ticketList) {
				System.out.println(t.toString());
			}
			return ticketList;
		}
		
		return null;
		
	}
	
	
	public boolean existTicket(Ticket t) {
		
		return tD.existTicket(t);
	}
	
	
	public  void generateCsvFile(String fileName, List<Ticket> tickets)
    {
			try
           {
                FileWriter writer = new FileWriter(fileName);
                writer.append(tickets.get(0).getTicketEvent().getEventTitle());
                writer.append('\n');
                writer.append("Row");
                writer.append(',');
                writer.append("Column");
                writer.append('\n');

                for (Ticket tick : tickets) {
                     writer.append(Integer.toString(tick.getRow()));
                     writer.append(',');
                     writer.append(Integer.toString(tick.getColumn()));
                     writer.append('\n');
                }

                writer.flush();
                writer.close();
           } catch(IOException e) {
                 e.printStackTrace();
           } 
      }
}
