package DataLayer;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DomainModel.EventTheatre;
import DomainModel.Ticket;

public class TicketsDAOMySQL implements TicketsRepository {

	
	private Connection connection;
	   
	public TicketsDAOMySQL (Connection connection) {
		this.connection = connection;
	}
	
	
	@Override
	public int insertTicket(Ticket tick) {
		// TODO Auto-generated method stub
		try { /*
			String sql2 = "SELECT * FROM tickets WHERE ticketEvent = ?;";
			PreparedStatement statement1 = connection.prepareStatement(sql2);
			
			statement1.setString(1, tick.getTicketEvent().getEventTitle());
			ResultSet rst;
			rst = statement1.executeQuery();
			if (rst.next()) {
				if (rst.getInt("ticketRow") != tick.getRow() || rst.getInt("ticketColumn") != tick.getColumn()) { 
				*/
					String sql = "INSERT INTO tickets (ticketEvent, eventID, ticketRow, ticketColumn) SELECT ?, eventID, ?, ? FROM events WHERE eventTitle = ?;";
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setString(1, tick.getTicketEvent().getEventTitle());
					statement.setString(4, tick.getTicketEvent().getEventTitle());
					statement.setInt(2, tick.getRow());
					statement.setInt(3, tick.getColumn());
					return statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Ticket> getTickets(EventTheatre ev) {
		// TODO Auto-generated method stub
		List<Ticket> ticketList = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM tickets WHERE ticketEvent = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,ev.getEventTitle());
			ResultSet rst;
			rst = statement.executeQuery();
			
			while (rst.next()) {
				Ticket ticket = new Ticket(ev, rst.getInt("ticketRow"), rst.getInt("ticketColumn"));
				ticketList.add(ticket);
			}
			rst.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		return ticketList;
	}
	
	public boolean existTicket(Ticket p) {
		try {
			String sql = "SELECT * FROM tickets WHERE ticketRow=? AND ticketColumn=? AND ticketEvent=?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, p.getRow());
			statement.setInt(2, p.getColumn());
			statement.setString(3, p.getTicketEvent().getEventTitle());
			
			ResultSet rst;
			rst = statement.executeQuery();
			if (rst.next()) {
				return false;
			}
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
