package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class AdminPanel extends JPanel {

	private JButton insertUserButton;
	private JButton updateEventButton;
	private JButton insertEventButton;
	private JButton deleteEventButton;
	private JButton logOutButton;
	
	public JButton getInsertUserButton() {
		return this.insertUserButton;
	}
	
	public JButton getLogOutButton () {
		return this.logOutButton;
	}
	
	public JButton getUpdateEventButton() {
		return this.updateEventButton;
	}
	
	public JButton getInsertEventButton () {
		return this.insertEventButton;
	}
	
	public JButton getDeleteEventButton() {
		return this.deleteEventButton;
	}
	
	public AdminPanel() {
		intialize();
	}
	
	private void intialize () {
		setLayout(new MigLayout("insets 5 5 5 5", 		//layout constraints 
				"[grow][][250!][grow]", 	//column constraints
				"[grow][][][][][][grow]"));	//row constraints

		insertUserButton = new JButton("Add New Employee");
		updateEventButton = new JButton("UpdateEvent");
		insertEventButton = new JButton("Add New Event");
		deleteEventButton = new JButton("Delete Existing Event");
		deleteEventButton = new JButton("Delete Existing Event");
		logOutButton = new JButton("LogOut");
		
		add(insertUserButton,				"cell 1 1, grow, span 2");
		add(updateEventButton,				"cell 1 2, grow, span 2");
		add(insertEventButton,		"cell 1 3, grow, span 2");	
		add(deleteEventButton,		"cell 1 4, grow, span 2");	
		add(logOutButton,		"cell 1 5, grow, span 2");	


	}
	
	
	
}
