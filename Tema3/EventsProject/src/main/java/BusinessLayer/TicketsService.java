package BusinessLayer;

import java.util.List;
import java.sql.Connection;

import DataLayer.MySqlConnection;
import DataLayer.TicketsDAOMySQL;
import DomainModel.*;
import DomainModel.Export.ExportFactory;
import DomainModel.Export.ExportFactoryImpl;
import DomainModel.Export.Exporter;


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
	
	
	public  void generateFile(String fileName, List<Ticket> tickets, ExportFactoryImpl.Type t)
    {
		ExportFactory fact = new ExportFactoryImpl();
		Exporter expFactory = fact.createExporterObject(t);

		expFactory.exportTickets(fileName, tickets);
		//a.export(fileName, tickets);
    }
}
