package com.agilecrm.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.agilecrm.dao.Dao;
import com.agilecrm.model.Contact;
import com.agilecrm.util.MySqlConnection;

public class DaoImpl implements Dao {

	static Connection con;
	static int status;
	Contact contact = new Contact();

	java.util.Date dt = new java.util.Date();

	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	String updatedDate = sdf.format(dt);
	String updatedBy = "Narahari";

	public int saveContact(Contact contact) throws SQLException, ClassNotFoundException {

		con = MySqlConnection.getConnection();
		try {
			String sql = "insert into contact(firstName,lastName,email,createdBy,createdDate,address,dob,isActive,phoneNumber) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, contact.getFirstName());
			ps.setString(2, contact.getLastName());
			ps.setString(3, contact.getEmail());
			ps.setString(4, contact.getCreatedBy());
			ps.setDate(5, new java.sql.Date(contact.getCreatedDate().getTime()));
			ps.setString(6, contact.getAddress());
			ps.setDate(7, new java.sql.Date(contact.getDob().getTime()));
			ps.setString(8, contact.getIsActive());
			// ps.setString(9, contact.getUpdatedBy());
			// ps.setDate(10, new java.sql.Date(contact.getUpdatedDate().getTime()));
			ps.setString(9, contact.getPhoneNumber());
			status = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return status;

	}

	public int updateContact(Contact contact) {

		String sql = "update contact set firstName=?,lastName=?,email=?,address=?,dob=?,updatedBy=?,updatedDate=?,phoneNumber=? where contactId=?";

		try {
			con = MySqlConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, contact.getFirstName());
			ps.setString(2, contact.getLastName());
			ps.setString(3, contact.getEmail());
			// ps.setString(4, contact.getCreatedBy());
			// ps.setDate(5, new java.sql.Date(contact.getCreatedDate().getTime()));
			ps.setString(4, contact.getAddress());
			ps.setDate(5, new java.sql.Date(contact.getDob().getTime()));
			ps.setString(6, contact.getUpdatedBy());
			ps.setDate(7, new java.sql.Date(contact.getDob().getTime()));
			ps.setString(8, contact.getPhoneNumber());
			status = ps.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public List<Contact> listContacts() throws SQLException {

		String sql = "select *from contact";
		List<Contact> list = new ArrayList<Contact>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			contact.setContactId(rs.getInt("contactId"));
			contact.setFirstName(rs.getString("firstName"));
			contact.setLastName(rs.getString("lastName"));
			contact.setEmail(rs.getString("email"));
			contact.setCreatedBy(rs.getString("createdBy"));
			contact.setCreatedDate(rs.getDate("createdDate"));
			contact.setAddress(rs.getString("address"));
			contact.setDob(rs.getDate("dob"));
			contact.setIsActive(rs.getString("isActive"));
			contact.setUpdatedBy(rs.getString("updatedBy"));
			contact.setUpdatedDate(rs.getDate("updatedDate"));
			contact.setPhoneNumber(rs.getString("phoneNumber"));
			list.add(contact);
		}
		return (List<Contact>) contact;
	}

	public List<Contact> getContactById(Contact contact) throws SQLException, ClassNotFoundException {
		
		List<Contact> list=new ArrayList<>();
		
		con=MySqlConnection.getConnection();
		
		PreparedStatement ps = con.prepareStatement("select * from contact WHERE contactId=?");
		ps.setInt(1, contact.getContactId());                                                                                                                 
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			contact.setContactId(rs.getInt("contactId"));
			contact.setFirstName(rs.getString("firstName"));
			contact.setLastName(rs.getString("lastName"));
			contact.setEmail(rs.getString("email"));
			contact.setCreatedBy(rs.getString("createdBy"));
			contact.setCreatedDate(rs.getDate("createdDate"));
			contact.setAddress(rs.getString("address"));
			contact.setDob(rs.getDate("dob"));
			contact.setIsActive(rs.getString("isActive"));
			contact.setUpdatedBy(rs.getString("updatedBy"));
			contact.setUpdatedDate(rs.getDate("updatedDate"));
			contact.setPhoneNumber(rs.getString("phoneNumber"));

		}
		return list;
	}

	public boolean deleteContact(Contact contact) {
		int status = 0;
		try {
			con = MySqlConnection.getConnection();

			PreparedStatement ps = con.prepareStatement("DELETE FROM contact WHERE contactId=?");
			ps.setInt(1, contact.getContactId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return status > 0;

	}

}
