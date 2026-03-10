package com.Orders;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Login.User;
import com.Products.Product;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
	private OrderDAO orderDAO;
	@Autowired
	public OrderService(OrderDAO orderDAO) {
		super();
		this.orderDAO = orderDAO;
	}
	
	public void save(Order order) {
		this.orderDAO.save(order);
	}
	
	public List<Order> getAll(){
		return this.orderDAO.getAll();
	}
	
	public Order getById(int id) {
		return this.orderDAO.getById(id);
	}

	public void createOrder(User user, Product product, String productName, LocalDate date, long amount) {
		this.orderDAO.createOrder(user, product, productName, date, amount);
	}
	public List<Order> getByUser(User user){
		return this.orderDAO.getByUser(user);
	}
	public void deleteOrder(int orderId) {
		this.orderDAO.deleteOrder(orderId);
	}
	
}
