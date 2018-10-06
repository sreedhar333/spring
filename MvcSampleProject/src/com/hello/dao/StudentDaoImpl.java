package com.hello.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Required;

import com.hello.data.dto.StudentData;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class StudentDaoImpl implements StudentDao
{

	private DriverManager driverManager;
	private String dburl;
	private String dbUser;
	private String dbPassword;
	private static Connection connection;
	
	@Override
	public boolean setStudentInfo(StudentData studentData) 
	{
		String sql = "INSERT INTO STUDENT (firstName, lastName, email) VALUES (?, ?, ?)";
		this.dbConnection();
		try{
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, studentData.getFirstName());
			ps.setString(2, studentData.getLastName());
			ps.setString(3, studentData.getEmail());
			ps.executeUpdate();
			ps.close();
		}catch (Exception e) {
			System.out.println("Unable to insert record into DB");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public ResultSet getStudentDetails(String search) 
	{
		this.dbConnection();
		String selectTableSQL = "SELECT firstName, lastName, email from STUDENT WHERE firstName Like '%"+ search +"%' OR lastName Like '%"+ search +"%' OR email Like '%"+ search +"%'";
		
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(selectTableSQL);
		/*	preparedStatement.setString(1, search);
			preparedStatement.setString(2, search);
			preparedStatement.setString(3, search);*/
			rs = preparedStatement.executeQuery(selectTableSQL);
		} catch (SQLException e) {
			System.out.println("Unable to search " + search);
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("Unable to search " + search);
			e.printStackTrace();
		}
		/*finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}*/
		return rs;
	}

	@SuppressWarnings("static-access")
	public void dbConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return ;
		}

		//System.out.println("MySQL JDBC Driver Registered!");
		try {
			if(connection == null){
				connection = (Connection) getDriverManager().getConnection(
																			this.getDburl(), 
																			this.getDbUser(), 
																			this.getDbPassword()
																			);
			}
			
			if (connection != null) {
				System.out.println("You made it, take control your database now!");
			} else {
				System.out.println("Failed to make connection!");
				return ;
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return ;
		}
	}
	
	public DriverManager getDriverManager() {
		return driverManager;
	}

	@Required
	public void setDriverManager(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public String getDburl() {
		return dburl;
	}

	@Required
	public void setDburl(String dburl) {
		this.dburl = dburl;
	}

	public String getDbUser() {
		return dbUser;
	}

	@Required
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	@Required
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
}
