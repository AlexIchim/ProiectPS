package main;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.text.ParseException;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
 






import BusinessLayer.EventsService;
import BusinessLayer.TicketsService;
import BusinessLayer.UserService;
import DataLayer.MySqlConnection;
import DataLayer.UsersDAOMySQL;
import DomainModel.Admin;
import DomainModel.EventTheatre;
import DomainModel.Ticket;
import DomainModel.User;
import GUI.UserLoginForm;

public class MainClass {
	
	public static void main (String[] args) {
		
		String s1 = "alex";
		String s2 = "parola1";
		//Connection connection = MySqlConnection.openConnection("cjteatru", "root", "");
		//UsersDAOMySQL uD = new UsersDAOMySQL(connection);
		//User u = uD.getUser(s1);
		
		/*
		UserService us = new UserService(null);
		
		User u = us.logIn(s1, s2);
		System.out.println(u.getPassword());
		System.out.println(u.getUser());
		*/
		
		EventsService es = new EventsService();
		
		//SELECT for EVENT
		EventTheatre ev = es.selectEvent("X-Men: Apocalypse");
		EventTheatre ev2 = es.selectEvent("The Hateful Eight");

		if (ev != null) {
			//System.out.println(ev.getEventTitle() + " " + ev.getEventDirector() + " " + ev.getEventDistribution() + " " + ev.getEventDateTime() + " " + ev.getEventTickets() + ".");
			System.out.println(ev.toString());
			System.out.println(ev2.toString());
		}
		else
			System.out.println("Event not found");
		//us.insertUser("George", "parola2", George);
		
		EventTheatre ev3 = new EventTheatre("Capitanul America: Razboiul Civil", "Anthony Russo, Joe Russo", "Tom Holland, Scarlett Johansson, Elizabeth Olsen","2015-11-30 22:11:25", 600 );

		
		//INSERT for EVENT
		//SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		EventTheatre ev6 = new EventTheatre("Capitanul America 2: Razboiul Civil", "Anthony Russo, Joe Russo", "Tom Holland, Scarlett Johansson, Elizabeth Olsen","2015-11-30 22:11:25", 600 );
		if (!es.insertEvent(ev6))
			System.out.println("Eroare");
		else
			System.out.println("Date introduse");
		
		
	
		
		/*
		
		//UPDATE for EVENT
		EventTheatre ev4 = new EventTheatre("Capitanul America: Razboiul Civil", "Anthony Russo, Joe Russo", "Tom Holland, Scarlett Johansson, Elizabeth Ols","2015-11-30 22:11:25", 600 );
		if (!es.updateEvent(ev4.getEventTitle(), ev4))
			System.out.println("Eroare");
		else
			System.out.println("Date updatate");
		*/
		
		/*
		//DELETE FOR EVENT
		EventTheatre ev5 = new EventTheatre("Capitanul America: Razboiul Civil", "", "" ,"", 0);
		
		
		if (!es.deleteEvent(ev5))
			System.out.println("Eroare");
		else
			System.out.println("Stergere efectuata");
	
		*/
		
		//TICKETS SERVICE TEST
		//ADD BOUGHT TICKETS
		EventTheatre ev5 = new EventTheatre("Capitanul America: Razboiul Civil", "", "" ,"", 0);

		TicketsService ts = new TicketsService();
		
		
		//Ticket t1 = new Ticket (ev5, 5, 6);
		//Ticket t2 = new Ticket (ev3, 1, 2);
		Ticket t4 = new Ticket (ev, 4, 2);

		
		
		
		
		//ts.getTicketsList(ev);
		/*
		if (!ts.getTicketsList(ev2).isEmpty()) {
			
			List<Ticket> ar = ts.getTicketsList(ev2);
			for (Ticket t : ar) {
				t.toString();
			}
		}
		else {
			System.out.println("FAIL");
		}
		*/
		
		
		/*
		EventTheatre ev9 = es.selectEvent("Mr. Right");
		
		Ticket t1 = new Ticket(ev9, 3, 4);
		Ticket t2 = new Ticket(ev9, 3, 4);
		Ticket t3 = new Ticket(ev9, 5, 6);
		Ticket t5 = new Ticket(ev9, 7, 8);
		ts.addBoughtTickets(t1);
		ts.addBoughtTickets(t2);
		ts.addBoughtTickets(t3);
		ts.addBoughtTickets(t5);
		*/
		
		UserService u = new UserService();
		
		System.out.println(u.updatePassword("Alex"));

	}

}
