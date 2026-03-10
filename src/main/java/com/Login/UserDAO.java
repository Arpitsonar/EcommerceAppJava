package com.Login;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	private SessionFactory sessionFactory;
	@Autowired
	public UserDAO(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	public void save(User user) {
		this.sessionFactory.getCurrentSession().persist(user);
	}
	
	public User getById(int id) {
		User user = this.sessionFactory.getCurrentSession().find(User.class, id);
		return user;
	}
	
	public User checkUser(String username, String password) {
		String hql = "FROM User u WHERE u.username = :username AND u.password = :password";
		User user = this.sessionFactory.getCurrentSession()
				.createQuery(hql, User.class)
				.setParameter("username", username)
				.setParameter("password", password)
				.getSingleResultOrNull();
		return user;
	}

	public List<User> getAllCustomers() {
		String hql = "FROM User u WHERE u.userlevel = \"CUSTOMER\"";
		List<User> customers = this.sessionFactory.getCurrentSession().createQuery(hql, User.class).getResultList();
		return customers;
	}
	
	public List<User> getAll(){
		List<User> users = this.sessionFactory.getCurrentSession().createQuery("FROM User", User.class).getResultList();
		return users;
	}
	
	public void deleteUser(User user) {
		this.sessionFactory.getCurrentSession().remove(user);
	}
	
	public void ordered(User user, double amount) {
		String hql = "UPDATE User u SET u.orders = :orders, u.income = :income WHERE u.userId=:id";
		this.sessionFactory.getCurrentSession().createMutationQuery(hql)
		.setParameter("orders", user.getOrders() + 1)
		.setParameter("income", user.getIncome() + amount)
		.setParameter("id",user.getUserId())
		.executeUpdate();
	}
	
}
