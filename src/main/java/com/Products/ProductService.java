package com.Products;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
	private ProductDAO productDAO;

	public ProductService(ProductDAO productDAO) {
		super();
		this.productDAO = productDAO;
	}
	
	public void save(Product product) {
		this.productDAO.save(product);
	}
	
	public List<Product> getAll(){
		return this.productDAO.getAll();
	}
	
	public Product getById(int id) {
		return this.productDAO.getById(id);
	}
	
	public void deleteProduct(Product p) {
		this.productDAO.deleteProduct(p);
	}
	
	public List<Product> top3(){
		return this.productDAO.top3();
	}

	public void sold(Product product) {
		this.productDAO.sold(product);
	}

	public void update(Product product, int productId) {
		this.productDAO.update(product, productId);
	}
}
