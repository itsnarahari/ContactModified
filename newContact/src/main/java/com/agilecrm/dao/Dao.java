package com.agilecrm.dao;

import java.sql.SQLException;
import java.util.List;

import com.agilecrm.model.Contact;

public interface Dao {
	
	public int saveContact(Contact contact) throws SQLException, ClassNotFoundException;
	public Contact updateContact(Contact contact);
	public List<Contact> listContacts() throws SQLException;
	public Contact getContactById(int id) throws SQLException;
	public void deleteContact(int id);

}
