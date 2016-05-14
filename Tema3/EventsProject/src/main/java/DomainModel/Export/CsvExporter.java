package DomainModel.Export;

import DomainModel.Ticket;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvExporter implements Exporter {

	@Override
	public void exportTickets(String fileName, List<Ticket> tickets) {
		// TODO Auto-generated method stub
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
