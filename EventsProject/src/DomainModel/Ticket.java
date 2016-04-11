package DomainModel;

public class Ticket {

	private EventTheatre ticketEvent;
	private int row;
	private int column;
	
	public Ticket(EventTheatre evn, int r, int c){
		this.ticketEvent = evn;
		this.row = r;
		this.column = c;
		
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public EventTheatre getTicketEvent() {
		return ticketEvent;
	}

	public void setTicketEvent(EventTheatre ticketEvent) {
		this.ticketEvent = ticketEvent;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	@Override
	public String toString () {
		String str = "Title: " + this.ticketEvent.getEventTitle() + "\nRow: " + this.row + "\nColumn: " + this.column + "\n\n\n";
		return str;
	}
	
}
