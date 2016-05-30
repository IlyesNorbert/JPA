package edu.ubb.cs.idde.client;

import java.util.ArrayList;

import org.slf4j.LoggerFactory;

import edu.ubb.cs.idde.inter.UserItemDAO;
import edu.ubb.cs.idde.inter.Person;

public class ClientServices {

	UserItemDAO usersDAO;
	ArrayList<Person> users;
	ArrayList<String> colNames;

	public ClientServices(UserItemDAO ud){
		//usersDAO = factory.getJdbcUserDAO();
		usersDAO = ud;
	}
	
	public ArrayList<Person> FindAllUsers(){
		try {
			users = usersDAO.getAllUsers();
		} catch (RuntimeException e) {
			//LOG.error(e.getClass().getName() + ": " + e.getMessage());
		}
		return users;
	}
	
/*	public ArrayList<String> GetColumnNames(){
		try {
			colNames = usersDAO.getColumnNames();
		} catch (RuntimeException e) {
			LOG.error(e.getClass().getName() + ": " + e.getMessage());
		}
		return colNames;
		
	}*/
}
