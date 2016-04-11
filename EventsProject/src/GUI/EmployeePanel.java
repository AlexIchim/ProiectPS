package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class EmployeePanel extends JPanel {

		private JButton addTicketsButton;
		private JButton viewTicketListButton;
		private JButton exportCSVButton;
		private JButton logOutButton;
		
		private JTable table;
		private DefaultTableModel tableModel;
		
		public JButton getLogOutButton () {
			return this.logOutButton;
		}
		
		public DefaultTableModel getTableModel() {
			return tableModel;
		}
		
		public JTable getTable() {
			return table;
		}
		
		public JButton getAddTicketsButton() {
			return this.addTicketsButton;
		}
		
		public JButton getViewTicketListButton() {
			return this.viewTicketListButton;
		}
		
		public JButton getExportCSVButton () {
			return this.exportCSVButton;
		}
	
		
		public EmployeePanel() {
			intialize();
		}
		
		private void intialize () {
			setLayout(new MigLayout("insets 5 5 5 5", "[150!][grow]", "[][][][][][][][grow][]"));
			addTicketsButton = new JButton("Add Ticket");
			viewTicketListButton = new JButton("View Tickets");
			logOutButton = new JButton("LogOut");
			exportCSVButton = new JButton("ExportCSV");
			tableModel = new DefaultTableModel();
			table = new JTable(tableModel);
			//table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane tablePane = new JScrollPane(table);
			
			add(addTicketsButton,				"cell 0 0, grow, span 2");
			add(viewTicketListButton,				"cell 0 2, grow, span 2");
			add(exportCSVButton,		"cell 0 4, grow, span 2");	
			add(logOutButton,		"cell 0 6, grow, span 2");	

			add(tablePane, "cell 2 0, span 2 9, grow");
			
		}
		
		
		
}
