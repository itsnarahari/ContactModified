package com.agilecrm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLPermission;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
	ServiceImpl services = new ServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter("action");

		if (action.equals("addContact")) {
			addContact(req, resp);
		}

		if (action.equals("updateContact")) {
			updateContact(req, resp);
		}
		if (action.equals("deleteContact")) {
			deleteContactOp(req, resp);
		}
		if (action.equals("getContactByIdOp")) {
		
				try {
					getContactByIdOp(req, resp);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
	}

	protected void addContact(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Services services = new ServiceImpl();

		Contact contact = new Contact();

		PrintWriter out = resp.getWriter();
		out.print("In Post");

		contact.setFirstName(req.getParameter("firstName"));
		contact.setLastName(req.getParameter("lastName"));
		contact.setEmail(req.getParameter("email"));
		
		contact.setAddress(req.getParameter("address"));

		try {
			contact.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("dob")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contact.setIsActive(req.getParameter("isActive"));
		// contact.setUpdatedBy(req.getParameter("updatedBy"));
		/*
		 * try { contact.setUpdatedDate(new
		 * SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("updatedDate"))); }
		 * catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		contact.setPhoneNumber(req.getParameter("phoneNumber"));

		int status = 0;
		try {
			status = services.saveContact(contact);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (status > 0)
			out.print("Recorded");
		else
			out.print("not done");

	}

	protected void updateContact(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {


		Contact contact = new Contact();

		PrintWriter out = resp.getWriter();
		out.print("In Post Update");
		contact.setContactId(Integer.parseInt(req.getParameter("contactId")));
		contact.setFirstName(req.getParameter("firstName"));
		contact.setLastName(req.getParameter("lastName"));
		contact.setEmail(req.getParameter("email"));
		// contact.setCreatedBy(req.getParameter("createdBy"));
		/*
		 * try { contact.setCreatedDate(new
		 * SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("createdDate"))); }
		 * catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		contact.setAddress(req.getParameter("address"));

		try {
			contact.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("dob")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contact.setPhoneNumber(req.getParameter("phoneNumber"));
		int status = 0;
		status = services.updateContact(contact);
		if (status > 0)
			out.print("Record Updated");
		else
			out.print("not done");

	}

	protected void deleteContactOp(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Contact contact=new Contact();
		int id1=Integer.parseInt(req.getParameter("id"));
		contact.setContactId(id1);
		PrintWriter out = resp.getWriter();
		boolean status=services.deleteContact(contact);
		if(status)
		out.print("In Post Delete");
		else
			out.println("Arey halva Correct chey ra babu anni sarlu kodthav ra");
	}
	
	protected void getContactByIdOp(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		resp.setContentType("text/html");
		Contact contact=new Contact();
		int id1=Integer.parseInt(req.getParameter("id"));
		contact.setContactId(id1);
		PrintWriter out = resp.getWriter();
		List<Contact> co=services.getContactById(contact);
		for(Contact c:co)
		{
			out.print(c.getContactId());
			
		}
		
				
	}

}
