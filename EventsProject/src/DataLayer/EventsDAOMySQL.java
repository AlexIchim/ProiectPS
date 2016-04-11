package DataLayer;

import DomainModel.EventTheatre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventsDAOMySQL implements EventsRepository {

	private Connection connection;
	   
	public EventsDAOMySQL (Connection connection) {
		this.connection = connection;
	}
	
	
	@Override
	public int insertEvent(EventTheatre ev) {
		// TODO Auto-generated method stub
		
		try {
			String sql = "INSERT into events (eventTitle, eventDirector, eventDistribution, eventReleaseDate, eventTickets) VALUES(?, ?, ?, ?, ?);";
			
			EventTheatre ev1 = selectEvent(ev.getEventTitle());
			if (ev1 == null) {
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setString(1, ev.getEventTitle());
				statement.setString(2, ev.getEventDirector());
				statement.setString(3, ev.getEventDistribution());
				statement.setString(4, ev.getEventReleaseDate());
				statement.setInt(5, ev.getEventTickets());
				return statement.executeUpdate();
			}
			else {
				return 0;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public EventTheatre selectEvent(String eventTitle) {
		// TODO Auto-generated method stub
		EventTheatre ev = null;
		try {
			String sql = "SELECT * FROM events WHERE eventTitle = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, eventTitle);
			ResultSet results = statement.executeQuery();
			
			if (results.next()) {
				ev = new EventTheatre(
						results.getString("eventTitle"),
						results.getString("eventDirector"),
						results.getString("eventDistribution"),
						results.getString("eventReleaseDate"),
						results.getInt("eventTickets"));	
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ev;
	}


	@Override
	public int updateEvent (String title, EventTheatre newEv) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE events SET eventTitle = ?, eventDirector = ?, eventDistribution = ?, eventReleaseDate = ?, eventTickets = ? WHERE eventTitle = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			
			statement.setString(1, newEv.getEventTitle());
			statement.setString(2, newEv.getEventDirector());
			statement.setString(3, newEv.getEventDistribution());
			statement.setString(4, newEv.getEventReleaseDate());
			statement.setInt(5, newEv.getEventTickets());
			
			statement.setString(6, title);
			
			return statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEvent(EventTheatre ev) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM events WHERE eventTitle = ?;";
			String sql2 = "DELETE FROM tickets WHERE ticketEvent = ?;";
					
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, ev.getEventTitle());
			
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setString(1, ev.getEventTitle());
			
			statement2.executeUpdate();
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
