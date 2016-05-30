package edu.ubb.cs.idde.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBagent {

	private final List<Connection> pool;
	private static DBagent instance;
	private int SIZE;

	public DBagent() {
		// TODO Auto-generated constructor stub
		pool = new LinkedList<Connection>();
		SIZE = 3;
		initializePool();
	}

	public synchronized static DBagent getInstance() {
		if (instance == null) {
			instance = new DBagent();
		}
		return instance;
	}

	public synchronized Connection getConn()  {
		Connection con = null;
		if (pool.size() > 0) {
			con = pool.get(0);
			pool.remove(0);
		}
		if (con == null) {
			System.out.println("No connections in pool");
		}
		return con;
	}

	private void initializePool() {

		try {
			for (int i = 0; i < SIZE; i++)
				//System.out.println("init connection");
				Class.forName("com.mysql.jdbc.Driver");
				pool.add(DriverManager.getConnection("jdbc:mysql://localhost/person?" + "user=root"));
		} catch (SQLException e) {
			System.out.println("SQLEXCEPTION in DBAgent");
			e.printStackTrace();
			//System.exit(0);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		/*catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("talan itt????");
			e.printStackTrace();
		}*/
	
	}

	public synchronized void returnConnection(final Connection con) {
		if (pool.size() < SIZE) {
			pool.add(con);
		}
	}


}
