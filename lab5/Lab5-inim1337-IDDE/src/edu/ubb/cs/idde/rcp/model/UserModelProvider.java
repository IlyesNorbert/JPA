package edu.ubb.cs.idde.rcp.model;

import java.lang.reflect.Field;
import java.util.ArrayList;

import edu.ubb.cs.server.DAO.JdbcDAOFactory;
import edu.ubb.cs.server.DAO.UserItemDAO;
import edu.ubb.cs.server.model.Person;


public enum UserModelProvider {
	INSTANCE;
	private ArrayList<Person> usersToDisplay;
	private UserItemDAO usersDAO;
	private JdbcDAOFactory factory;
	ArrayList<String> colNames;
	public Field[] fields;
	
	private UserModelProvider() {
		usersToDisplay = new ArrayList<Person>();
		colNames = new ArrayList<String>();
		factory = new JdbcDAOFactory();
		usersDAO = factory.getHibernateUserDao();
		Person u = new Person();
		@SuppressWarnings("rawtypes")
		Class cls = u.getClass();
		fields = cls.getDeclaredFields();

	}

	public ArrayList<Person> getUsers() {
		try {
			usersToDisplay = usersDAO.getAllUsers();
			//usersToDisplay.add(new Users(1,"Jani",10,"ithon"));
		} catch (RuntimeException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return usersToDisplay;
	}
	
	
	
	public Field[] getFields(){
		
		return fields;
	}

}
