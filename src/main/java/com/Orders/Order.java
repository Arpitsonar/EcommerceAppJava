package com.Orders;

import java.time.LocalDate;

import com.Login.User;
import com.Products.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Product product;
	
	private String productName;
	
	private LocalDate date;
	private long amount;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Order(User user, Product product, String productName, LocalDate date, long amount) {
		super();
		this.user = user;
		this.product = product;
		this.productName = productName;
		this.date = date;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", user=" + user + ", product=" + product + ", productName=" + productName
				+ ", date=" + date + ", amount=" + amount + "]";
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
