package com.agilecrm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLPermission;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agilecrm.model.Contact;
import com.agilecrm.services.Services;
import com.agilecrm.servicesimpl.ServiceImpl;
import com.agilecrm.util.MySqlConnection;

//o@WebServlet("/contact")
public class ContactController extends HttpServlet {
	/**
	 * Author By Narahari
	 */



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		MySqlConnection connection = new MySqlConnection();
		try {
			out.print(connection.getConnection().getCatalog());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		Services services = new ServiceImpl();
				
		Contact contact=new Contact();
		
		PrintWriter out=resp.getWriter();
		out.print("In Post");
		
		contact.setFirstName(req.getParameter("firstName"));
		contact.setLastName(req.getParameter("lastName"));
		contact.setEmail(req.getParameter("email"));
		contact.setCreatedBy(req.getParameter("createdBy"));
		try {
			contact.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("createdDate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contact.setAddress(req.getParameter("address"));
		
		try {
			contact.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("dob")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contact.setIsActive(req.getParameter("isActive"));
		contact.setUpdatedBy(req.getParameter("updatedBy"));
		try {
			contact.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("updatedDate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contact.setPhoneNumber(req.getParameter("phoneNumber"));
		
		int status =0;
		try {
			status = services.saveContact(contact);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(status>0)
		out.print("Recorded");
		else
			out.print("not done");
			
}

}
