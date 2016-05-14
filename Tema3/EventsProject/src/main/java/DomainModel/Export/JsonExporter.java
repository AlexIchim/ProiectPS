package DomainModel.Export;

import DomainModel.Ticket;

import java.io.FileWriter;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class JsonExporter implements Exporter {

	@Override
	public void exportTickets(String fileName, List<Ticket> tickets) {
		// TODO Auto-generated method stub
		//String json = new Gson().toJson(tickets );

		JsonObjectBuilder rootBuilder = Json.createObjectBuilder();
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		try {
			FileWriter writer = new FileWriter(fileName);

			for (Ticket tick : tickets) {

				//Create a JSON object for each ticket
				JsonObject ticketObject = Json.createObjectBuilder()
						.add("event", Json.createObjectBuilder().add("eventTitle", tick.getTicketEvent().getEventTitle())
								.add("eventDirector", tick.getTicketEvent().getEventDirector())
								.add("eventDistribution", tick.getTicketEvent().getEventDistribution())
								.add("eventReleaseDate", tick.getTicketEvent().getEventReleaseDate())
								.add("eventTickets", tick.getTicketEvent().getEventTickets())
						)
						.add("column", tick.getColumn())
						.add("row", tick.getRow())
						.build();

				//add ticket to array
				arrayBuilder.add(ticketObject);
			}

			//add the array of tickets to a root JSON object
			JsonObject root = rootBuilder.add("tickets", arrayBuilder).build();
			writer.append(root.toString());


			writer.flush();
			writer.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		//write JSON
	}

}
