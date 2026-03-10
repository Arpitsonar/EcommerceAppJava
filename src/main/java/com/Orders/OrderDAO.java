package com.Orders;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Login.User;
import com.Products.Product;

@Repository
public class OrderDAO {
	private SessionFactory sessionFactory;
	@Autowired
	public OrderDAO(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	public void save(Order order) {
		this.sessionFactory.getCurrentSession().persist(order);
	}
	
	public List<Order> getAll(){
		List<Order> orderList = this.sessionFactory.getCurrentSession().createQuery("FROM Order", Order.class).getResultList();
		return orderList;
	}
	
	public Order getById(int id) {
		Order order = this.sessionFactory.getCurrentSession().find(Order.class, id);
		return order;
	}
	
	public void createOrder(User user, Product product, String productName, LocalDate date, long amount) {
		Order order = new Order(user, product, productName, date, amount);
		System.out.println(order);
		this.save(order);
	}
	
	public List<Order> getByUser(User user){
		String hql = "FROM Order o WHERE o.user.userId = :id";
		List<Order> orderList = this.sessionFactory.getCurrentSession().createQuery(hql, Order.class)
				.setParameter("id", user.getUserId())
				.getResultList();
		return orderList;
	}
	
	public void deleteOrder(int orderId) {
		this.sessionFactory.getCurrentSession().createMutationQuery("DELETE FROM Order o WHERE o.orderId = :id")
			.setParameter("id", orderId)
			.executeUpdate();
	}
	
}
