package com.agilecrm.dao;

import java.sql.SQLException;
import java.util.List;

import com.agilecrm.model.Contact;

public interface Dao {
	
	public int saveContact(Contact contact) throws SQLException, ClassNotFoundException;
	public int updateContact(Contact contact);
	public List<Contact> listContacts() throws SQLException;
	public List<Contact> getContactById(Contact contact) throws SQLException, ClassNotFoundException;
	public boolean deleteContact(Contact contact);

}
