package edu.ubb.cs.idde.inter;

import java.util.ArrayList;

import edu.ubb.cs.idde.inter.RepositoryException;
import edu.ubb.cs.idde.inter.Person;

public interface UserItemDAO {
	
	Person getUserById(Long id) throws RepositoryException;
	ArrayList<Person> getAllUsers() throws RepositoryException;
	void insertUser(Person User) throws RepositoryException;
	void updateUser(Person User) throws RepositoryException;
	void deleteUser(Person User) throws RepositoryException;
}

