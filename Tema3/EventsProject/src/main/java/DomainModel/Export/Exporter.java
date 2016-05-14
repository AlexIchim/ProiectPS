package DomainModel.Export;

import java.util.List;

import DomainModel.Ticket;

public interface Exporter {
	void exportTickets(String fileName, List<Ticket> tickets);
}
