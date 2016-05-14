package GUI;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import DomainModel.Export.ExportFactoryImpl;
import net.miginfocom.swing.MigLayout;
import BusinessLayer.EventsService;
import BusinessLayer.TicketsService;
import BusinessLayer.UserService;
import DomainModel.EventTheatre;
import DomainModel.User;
import DomainModel.Ticket;

@SuppressWarnings("serial")
public class UserLoginForm extends JFrame {
	
	private CardLayout layout;
	private LoginPanel loginPanel;
	private AdminPanel adminPanel;
	private EmployeePanel employeePanel;
	
	public UserLoginForm (String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		
		initialize();
		addListeners();
	}
	public static void main (String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try
				{
					UserLoginForm userLogin = new UserLoginForm("Event Management");
					userLogin.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	private void initialize() {
		layout = new CardLayout();
		loginPanel = new LoginPanel();
		adminPanel = new AdminPanel();
		employeePanel = new EmployeePanel();
		Container c = getContentPane();
		c.setLayout(layout);;
		c.add(loginPanel, "1");
		c.add(adminPanel, "2");
		c.add(employeePanel, "3");

		layout.show(c, "1");
	}
	
	private void addListeners() {
		
		loginPanel.getLoginButton().addActionListener( new ActionListener() 
		{
		

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!loginPanel.getUserField().getText().isEmpty() && !loginPanel.getPassField().getPassword().toString().isEmpty()) {
					String userName = loginPanel.getUserField().getText();
					String userPass = new String(loginPanel.getPassField().getPassword());
					
					UserService us = new UserService();
					User u = us.logIn(userName, userPass);
					
					if (u != null) {
						//layout.show(getContentPane(), "3");
						System.out.println("good");
						if (us.isAdmin(userName))
							layout.show(getContentPane(),"2");
						else 
							layout.show(getContentPane(), "3");
					}
					else {
						JOptionPane.showMessageDialog(loginPanel, "Login failed! Check forgot password!");
					}
				}
				else {
					JOptionPane.showMessageDialog(loginPanel, "All fields are mandatory!");
				}
			}
		});
	
	
	loginPanel.getForogotPasswordButton().addActionListener( new ActionListener() 
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JPanel p = new JPanel();
			JTextField userName = new JTextField(10);
			
			p.add(new JLabel("Username:"));
			p.add(userName);
			
			userName.setText(loginPanel.getUserField().getText());

			int n = JOptionPane.showConfirmDialog(null, p, "Enter your username: ", JOptionPane.OK_CANCEL_OPTION);
			if(n == JOptionPane.OK_OPTION){ // Afirmative
			UserService us1 = new UserService(); 
			String userName1 = userName.getText();
			String pass = us1.updatePassword(userName1);
			
			if (pass != null) {
				JOptionPane.showMessageDialog(null, "Password: " + pass, "New Password", JOptionPane.INFORMATION_MESSAGE);
				loginPanel.getPassField().setText(pass);
				loginPanel.getUserField().setText(userName1);
			}
			else {
				JOptionPane.showMessageDialog(null, "Wrong username", "Wrong username", JOptionPane.INFORMATION_MESSAGE);

			}

			 }
		}	
	});
	
	
	adminPanel.getInsertUserButton().addActionListener( new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JPanel p = new JPanel();
			JTextField userNameField = new JTextField(10);
			JTextField userPasswordField = new JTextField(10);
			JTextField userFirstNameField = new JTextField(10);
			p.setLayout(new MigLayout("wrap 2"));			
			
			p.add(new JLabel("Username:"));
			p.add(userNameField);
			
			p.add(new JLabel("Password:"));
			p.add(userPasswordField);
			
			p.add(new JLabel("First Name:"));
			p.add(userFirstNameField);
			
			int n = JOptionPane.showConfirmDialog(null, p, "New User Details", JOptionPane.OK_CANCEL_OPTION);
		
			if (n == JOptionPane.OK_OPTION) {
				if (!userNameField.getText().isEmpty() && !userPasswordField.getText().isEmpty() && !userFirstNameField.getText().isEmpty()) {
					UserService us = new UserService();
					if (us.insertUser(userNameField.getText(), userPasswordField.getText(), userFirstNameField.getText())){
						JOptionPane.showMessageDialog(null, "User Added");
					}
					else {
						JOptionPane.showMessageDialog(null, "User Already In System");
					}
				}
				else {
					JOptionPane.showMessageDialog(adminPanel, "All fields are mandatory !");
				}
			}
		}
		
	});
	
	
	
	adminPanel.getUpdateEventButton().addActionListener( new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JPanel p = new JPanel();
			JTextField eventTitleField = new JTextField(10);
			JTextField newEventTitleField = new JTextField(10);

			JTextField eventDirectorField = new JTextField(10);
			JTextField eventDistributionField = new JTextField(10);
			JTextField eventTicketsNoField = new JTextField(10);
			JTextField eventReleaseDateField = new JTextField(10);
			p.setLayout(new MigLayout("wrap 2"));			
			
			
			p.add(new JLabel("Event Title"));
			p.add(eventTitleField);
			
			p.add(new JLabel("New Event Title"));
			p.add(newEventTitleField);
			
			p.add(new JLabel("New Event Director"));
			p.add(eventDirectorField);
			
			p.add(new JLabel("New Event Distribution"));
			p.add(eventDistributionField);
			
			p.add(new JLabel("New Release Date"));
			p.add(eventReleaseDateField);
			
			p.add(new JLabel("New Total Tickets"));
			p.add(eventTicketsNoField);
			
			int n = JOptionPane.showConfirmDialog(null, p, "New Event Details", JOptionPane.OK_CANCEL_OPTION);
		
			if (n == JOptionPane.OK_OPTION) {
				EventsService es = new EventsService();
				EventTheatre ev = null;
				
				if (!eventTitleField.getText().isEmpty()) {
					 ev = es.selectEvent(eventTitleField.getText());
				}

				if (ev != null) {
					
					if (!newEventTitleField.getText().isEmpty()) {
						ev.setEventTitle(newEventTitleField.getText());
					}
					
					if (!eventDistributionField.getText().isEmpty()) {
						ev.setEventDistribution(eventDistributionField.getText());
					}
					
					if (!eventDirectorField.getText().isEmpty()) {
						ev.setEventDirector(eventDirectorField.getText());
					}
					
					if (!eventReleaseDateField.getText().isEmpty()) {
						ev.setEventReleaseDate(eventReleaseDateField.getText());
					}
					
					if (!eventTicketsNoField.getText().isEmpty()) {
						ev.setEventTickets(Integer.parseInt(eventTicketsNoField.getText()));
					}
					
					
					if (es.updateEvent(eventTitleField.getText(), ev)){
						JOptionPane.showMessageDialog(null, "Updated Event");
					}
					else {
						JOptionPane.showMessageDialog(null, "Update Error");
					}
				}
			}
		}
		
	});
	
	
	adminPanel.getInsertEventButton().addActionListener( new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JPanel p = new JPanel();
			JTextField eventTitleField = new JTextField(10);
			JTextField eventDirectorField = new JTextField(10);
			JTextField eventDistributionField = new JTextField(10);
			JTextField eventTicketsNoField = new JTextField(10);
			JTextField eventReleaseDateField = new JTextField(10);
			p.setLayout(new MigLayout("wrap 2"));			
			
			p.add(new JLabel("Event Title"));
			p.add(eventTitleField);
			
			p.add(new JLabel("Event Director"));
			p.add(eventDirectorField);
			
			p.add(new JLabel("Event Distribution"));
			p.add(eventDistributionField);
			
			p.add(new JLabel("Release Date"));
			p.add(eventReleaseDateField);
			
			p.add(new JLabel("Total Tickets"));
			p.add(eventTicketsNoField);
			
			int n = JOptionPane.showConfirmDialog(null, p, "New Event Details", JOptionPane.OK_CANCEL_OPTION);
		
			if (n == JOptionPane.OK_OPTION) {
				if (!eventTicketsNoField.getText().isEmpty() && !eventDistributionField.getText().isEmpty() && !eventReleaseDateField.getText().isEmpty() && !eventTitleField.getText().isEmpty() && !eventDirectorField.getText().isEmpty()) { 
					{
							EventsService es = new EventsService();
							if (es.insertEvent(new EventTheatre(eventTitleField.getText(), eventDirectorField.getText(), eventDistributionField.getText(),eventReleaseDateField.getText(), Integer.parseInt(eventTicketsNoField.getText())))){
								JOptionPane.showMessageDialog(null, "Event Added");
							}
							else {
								JOptionPane.showMessageDialog(null, "Event Not Added");
							}
					}
				}
				else {
					JOptionPane.showMessageDialog(adminPanel, "All fields are mandatory!");
				}
			}
		}
		
	});
	
	
	
	adminPanel.getDeleteEventButton().addActionListener( new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JPanel p = new JPanel();
			JTextField eventTitleField = new JTextField(10);
			
			p.setLayout(new MigLayout("wrap 2"));			
			
			p.add(new JLabel("Event Title"));
			p.add(eventTitleField);
			
			
			int n = JOptionPane.showConfirmDialog(null, p, "Enter Event Title", JOptionPane.OK_CANCEL_OPTION);
		
			if (n == JOptionPane.OK_OPTION) {
				if (!eventTitleField.getText().isEmpty()) {
					EventsService eS = new EventsService();
					
					if (eS.selectEvent(eventTitleField.getText()) != null) {
						if (eS.deleteEvent(eS.selectEvent(eventTitleField.getText()))){
							JOptionPane.showMessageDialog(null, "Event Deleted");
						}
						else {
							JOptionPane.showMessageDialog(null, "Event not Deleted");
						}
					}
					else {
						JOptionPane.showMessageDialog(adminPanel, "Event not in database!");
					}
				}
				else {
					JOptionPane.showMessageDialog(adminPanel, "All fields are mandatory!");
				}
			}
		}
		
	});
	
	adminPanel.getLogOutButton().addActionListener(new ActionListener () {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			layout.show(getContentPane(), "1");
		}
		
	});
	
	
	employeePanel.getLogOutButton().addActionListener(new ActionListener () {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			layout.show(getContentPane(), "1");
		}
		
	});
	
	employeePanel.getAddTicketsButton().addActionListener(new ActionListener () {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JPanel p = new JPanel();
			JTextField ticketEventTitleField = new JTextField(10);
			JTextField ticketRowField = new JTextField(10);
			JTextField ticketColumnField = new JTextField(10);
			
			p.setLayout(new MigLayout("wrap 2"));			

			p.add(new JLabel("Event Title"));
			p.add(ticketEventTitleField);
			
			p.add(new JLabel("Ticket row"));
			p.add(ticketRowField);
			
			p.add(new JLabel("Ticket column"));
			p.add(ticketColumnField);
			
			int n = JOptionPane.showConfirmDialog(null, p, "Enter Ticket Info", JOptionPane.OK_CANCEL_OPTION);

			if (n == JOptionPane.OK_OPTION) {
				TicketsService tS = new TicketsService();
				EventsService eS = new EventsService();
				
				if(!ticketEventTitleField.getText().isEmpty() && !ticketColumnField.getText().isEmpty() && !ticketRowField.getText().isEmpty()) {
					EventTheatre ev = eS.selectEvent(ticketEventTitleField.getText());
					if (ev != null ) {
						
						Ticket t = new Ticket(ev, Integer.parseInt(ticketRowField.getText()), Integer.parseInt(ticketColumnField.getText()));
						System.out.println(t.toString());
						int op = tS.addBoughtTickets(t);
						if (op == -1) {
							JOptionPane.showMessageDialog(employeePanel, "No more tickets for " + ticketEventTitleField.getText());	
						}
						else if (op == 1){
							JOptionPane.showMessageDialog(employeePanel, "Ticket added");	
						}
						else if (op == 0){
							JOptionPane.showMessageDialog(employeePanel, "Ticket taken");								
						}
					}
					else {
						JOptionPane.showMessageDialog(employeePanel, "Event does not exist");
					}
						//Ticket ti = new Ticket(ev, ticketRowField.getText(), ticketColumnField.getText() ); 
						
				}
				else {
					JOptionPane.showMessageDialog(employeePanel, "All fields are mandatory!");
				}
			}
		}
		
	});
	
	
	employeePanel.getViewTicketListButton().addActionListener(new ActionListener () {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JPanel p = new JPanel();
			JTextField eventTitleField = new JTextField(10);

			p.add(new JLabel("Event title"));
			p.add(eventTitleField);
			//p.add(exportOption);

			
			int n = JOptionPane.showConfirmDialog(null, p, "Enter Event Title", JOptionPane.OK_CANCEL_OPTION);

			
			if (n == JOptionPane.OK_OPTION) {
			
				if (!eventTitleField.getText().isEmpty()) {
					EventsService eS = new EventsService();
					EventTheatre ev = null;
					ev = eS.selectEvent(eventTitleField.getText());
					if (ev != null) {
						TicketsService tS = new TicketsService();
						List<Ticket> tickets = tS.getTicketsList(ev);
						if (!tickets.isEmpty()) {
							employeePanel.getTableModel().setRowCount(0);
							employeePanel.getTableModel().setColumnIdentifiers(new String[] {"Row", "Column"});
							for (Ticket t : tickets) {
								employeePanel.getTableModel().addRow(new String[]{Integer.toString(t.getRow()), Integer.toString(t.getColumn())});
							}
						}
						{
							JOptionPane.showMessageDialog(employeePanel, "No tickets added!");

						}
					} else {
						JOptionPane.showMessageDialog(employeePanel, "Event does not exist !");
	
					}
				}
				else {
					JOptionPane.showMessageDialog(employeePanel, "All fields are mandatory!");
	
				}
						
			
			}
		}
			
	});
	
		

	employeePanel.getExportButton().addActionListener(new ActionListener()  {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JPanel p = new JPanel();
			JTextField eventTitleField = new JTextField(10);
			JTextField fileNameField = new JTextField(10);

			String[] expChoose = { "CSVExport", "JSONExport" };
			JComboBox exportOption = new JComboBox(expChoose);
			exportOption.setSelectedIndex(1);
			//exportOption.addActionListener(this);
			exportOption.setEditable(false);


			p.add(new JLabel("Event title"));
			p.add(eventTitleField);
			
			p.add(new JLabel("File name"));
			p.add(fileNameField);
			p.add(exportOption);

			int n = JOptionPane.showConfirmDialog(null, p, "Enter Event Title", JOptionPane.OK_CANCEL_OPTION);

			
			if (n == JOptionPane.OK_OPTION) {
				if (!eventTitleField.getText().isEmpty() && !fileNameField.getText().isEmpty()) { 
					EventsService eS = new EventsService();
					EventTheatre ev = null;
					ev = eS.selectEvent(eventTitleField.getText());
					if (ev != null) {
						TicketsService tS = new TicketsService();

                        if (exportOption.getSelectedIndex() == 0) {
                            tS.generateFile(fileNameField.getText(), tS.getTicketsList(ev), ExportFactoryImpl.Type.CSV);
                            JOptionPane.showMessageDialog(employeePanel, "CSV Generated");
                        } else {
                            tS.generateFile(fileNameField.getText(), tS.getTicketsList(ev), ExportFactoryImpl.Type.JSON);
                            JOptionPane.showMessageDialog(employeePanel, "JSON Generated");
                        }
					}
					else {
						JOptionPane.showMessageDialog(employeePanel, "Event does not exist !");
					}
				}
				else {
					JOptionPane.showMessageDialog(employeePanel, "All fields are mandatory!");
				}
			}
		}
		
	});
	}
}
