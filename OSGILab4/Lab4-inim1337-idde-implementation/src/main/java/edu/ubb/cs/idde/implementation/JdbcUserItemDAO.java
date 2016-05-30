package edu.ubb.cs.idde.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.LoggerFactory;

import edu.ubb.cs.idde.inter.RepositoryException;
import edu.ubb.cs.idde.inter.UserItemDAO;
import edu.ubb.cs.idde.inter.Person;

public class JdbcUserItemDAO implements UserItemDAO {

	private DBagent conMan;
	private static final org.slf4j.Logger LOG = LoggerFactory
			.getLogger(JdbcUserItemDAO.class);

	public JdbcUserItemDAO() {
		conMan = DBagent.getInstance();
	}

	
	public ArrayList<Person> getFilteredUsers(Person user) throws RepositoryException {
		Connection con = null;
		PreparedStatement stmt = null;
		ArrayList<Person> users = new ArrayList<Person>();
		String sql;
		try {
			con = conMan.getConn();

			if (user.getAge() != -1){
				sql = "Select * FROM person where Name LIKE ? "
												+ "AND Age = ? "
												+ "AND PhoneNumber LIKE ? ";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, "%"+user.getName()+"%");
				stmt.setInt(2, user.getAge());
				stmt.setString(3, "%"+user.getAddress()+"%");
			}else{
				 sql = "Select * FROM person where Name LIKE ? "
						+ "AND Age LIKE ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, "%"+user.getName()+"%");
				stmt.setString(2, "%"+user.getAddress()+"%");
			}
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				//users.add(new UserItem(rs.getString(1), rs.getInt(2), rs.getString(3), Long.parseLong(rs.getString(4))));
			}
			stmt.close();
		} catch (final SQLException e) {
			LOG.error(e.getClass().getName() + ": " + e.getMessage());
			throw new RepositoryException("User selection failed");
		} catch (NumberFormatException e) {
			LOG.error(e.getClass().getName() + ": " + e.getMessage());
		} catch (RuntimeException e){
			LOG.error(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
		return users;
	}

	@Override
	public ArrayList<Person> getAllUsers() throws RepositoryException {
		Connection con = null;
		Statement stmt = null;
		ArrayList<Person> users = new ArrayList<Person>();
		try {
			con = conMan.getConn();

			stmt = con.createStatement();
			String sql = "Select * FROM person";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				users.add(new Person(rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(1)));
			}
			stmt.close();
		} catch (final SQLException e) {
			// log
			throw new RepositoryException("User selection failed");
		} catch (NumberFormatException e) {
			LOG.error(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
		return users;
	}

	public ArrayList<String> getColumnNames() throws RepositoryException {
		Connection con = null;
		Statement stmt = null;
		ArrayList<String> columnName = new ArrayList<String>();
		try {
			con = conMan.getConn();

			stmt = con.createStatement();
			String sql = "Select * FROM person";
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++)
				columnName.add(rsmd.getColumnLabel(i));
			stmt.close();
		} catch (final SQLException e) {
			LOG.error(e.getClass().getName() + ": " + e.getMessage());
			throw new RepositoryException("User selection failed");
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
		return columnName;
	}

	@Override
	public void insertUser(Person user) throws RepositoryException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = conMan.getConn();

			
			String sql = "INSERT INTO person (Name,Age,PhoneNumber,Address) "
					+ "VALUES (?,?,?,?);";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setInt(2, user.getAge());
			stmt.setString(3, user.getAddress());
			stmt.setInt(5, user.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (final SQLException e) {
			LOG.error(e.getClass().getName() + ": " + e.getMessage());
			throw new RepositoryException("User insert failed");
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
	}

	@Override
	public void updateUser(Person user) throws RepositoryException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = conMan.getConn();

			
			String sql = "UPDATE person SET Age = ?, PhoneNumber = ?, Address = ? WHERE Name =?";
			stmt = (PreparedStatement) con.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setDouble(2, user.getAge());
			stmt.setString(3, user.getAddress());
			stmt.setLong(4, user.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (final SQLException e) {
			LOG.error(e.getClass().getName() + ": " + e.getMessage());
			throw new RepositoryException("User update failed");
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
	}

	@Override
	public void deleteUser(Person user) throws RepositoryException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = conMan.getConn();

			
			String sql = "DELETE FROM person WHERE Name = ?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, user.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (final SQLException e) {
			LOG.error(e.getClass().getName() + ": " + e.getMessage());
			throw new RepositoryException("User deletion failed");
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
	}


	@Override
	public Person getUserById(Long id) throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
