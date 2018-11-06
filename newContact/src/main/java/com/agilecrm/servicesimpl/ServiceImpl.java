package com.agilecrm.servicesimpl;

import java.sql.SQLException;
import java.util.List;

import com.agilecrm.dao.Dao;
import com.agilecrm.daoimpl.DaoImpl;
import com.agilecrm.model.Contact;
import com.agilecrm.services.Services;

public class ServiceImpl implements Services {
	Dao dao = new DaoImpl();

	public int saveContact(Contact contact) throws SQLException, ClassNotFoundException {
		return dao.saveContact(contact);
	}
	public Contact updateContact(Contact contact) {
		return dao.updateContact(contact);
	}
	public List<Contact> listContacts() throws SQLException {
		return dao.listContacts();
	}
	public Contact getContactById(int id) throws SQLException {
		return dao.getContactById(id);
	}
	public void deleteContact(int id) {
		dao.deleteContact(id);
	}

}
