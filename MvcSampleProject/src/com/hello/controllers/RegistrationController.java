package com.hello.controllers;


import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hello.forms.RegistrationForm;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@Controller
public class RegistrationController {
	
	 @RequestMapping(value = "/register-form", method = RequestMethod.GET)
	   public String getRegistrationForm(ModelMap model) 
	   {
		   RegistrationForm registrationForm = new RegistrationForm();
		   model.addAttribute("registrationForm", registrationForm);
		   return "student/registration";
	   }
	 
	 @RequestMapping(value = "/register", method = RequestMethod.POST)
	   public String createRegistrationForm(RegistrationForm registrationForm, ModelMap model) 
	   {
		   
		   System.out.println(registrationForm.getFirstName());
		   System.out.println(registrationForm.getLastName());
		   if(registrationForm.getFirstName()!= null && registrationForm.getLastName()!=null){
			  boolean result = this.setCustomerDataIntoDb(registrationForm);
			   model.addAttribute("msg", result);
		   }else {
			   System.out.println("Empty first name and last Name");
		   }
		   registrationForm = new RegistrationForm();
		   return "student/registration";
	   }

	 
	 public boolean setCustomerDataIntoDb(RegistrationForm registrationForm) {
		 
		 try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
				return false;
			}

			System.out.println("MySQL JDBC Driver Registered!");
			Connection connection = null;
			String sql = "INSERT INTO STUDENT (firstName, lastName, email) VALUES (?, ?, ?)";
			try {
				connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/myprograms","root", "Sreedhar@3");
				PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
				ps.setString(1, registrationForm.getFirstName());
				ps.setString(2, registrationForm.getLastName());
				ps.setString(3, registrationForm.getEmail());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return false;
			}

			if (connection != null) {
				System.out.println("You made it, take control your database now!");
			} else {
				System.out.println("Failed to make connection!");
			}
			return true;
		  }
}
