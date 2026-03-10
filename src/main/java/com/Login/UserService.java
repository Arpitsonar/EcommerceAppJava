package com.Login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	private UserDAO userDAO;
	@Autowired
	public UserService(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}
	
	public void save(User user) {
		this.userDAO.save(user);
	}
	
	public User getById(int id) {
		return this.userDAO.getById(id);
	}
	
	public User checkUser(String username, String password) {
		 return this.userDAO.checkUser(username, password);
	}

	public List<User> getAllCustomers() {
		List<User> users = this.userDAO.getAllCustomers();
		return users;
	}

	public List<User> getAll() {
		return this.userDAO.getAll();
	}
	
	public void deleteUser(User user) {
		this.userDAO.deleteUser(user);
	}
	
	public void ordered(User user, double amount) {
		this.userDAO.ordered(user, amount);
	}
	
}
