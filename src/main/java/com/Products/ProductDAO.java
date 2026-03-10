package com.Products;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
	private SessionFactory sessionFactory;

	public ProductDAO(SessionFactory sf) {
		super();
		this.sessionFactory = sf;
	}
	
	public void save(Product product) {
		this.sessionFactory.getCurrentSession().persist(product);
	}
	
	public List<Product> getAll(){
		List<Product> productList = this.sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
		return productList;
	}
	
	public Product getById(int id) {
		return this.sessionFactory.getCurrentSession().find(Product.class, id);
	}
	
	public void deleteProduct(Product p) {
		this.sessionFactory.getCurrentSession().remove(p);
	}
	
	public List<Product> top3(){
		String hql = "FROM Product p ORDER BY p.sales desc LIMIT 3";
		List<Product> top3List = this.sessionFactory.getCurrentSession().createQuery(hql, Product.class).getResultList();
		return top3List;
	}

	public void sold(Product product) {
		String hql = "UPDATE Product p SET p.sales = :sales, p.stock = :stock WHERE p.productId = :id";
		this.sessionFactory.getCurrentSession().createMutationQuery(hql)
			.setParameter("sales", product.getSales() + 1)
			.setParameter("stock", product.getStock() - 1)
			.setParameter("id", product.getProductId())
			.executeUpdate();
	}

	public void update(Product product, int productId) {
		String hql = "UPDATE Product p SET p.productName = :name, p.price = :price, p.stock = :stock, p.sales = :sales, p.description = :description WHERE p.productId = :id";
		this.sessionFactory.getCurrentSession().createMutationQuery(hql)
			.setParameter("name", product.getProductName())
			.setParameter("price", product.getPrice())
			.setParameter("stock", product.getStock())
			.setParameter("sales", product.getSales())
			.setParameter("description", product.getDescription())
			.setParameter("id", productId)
			.executeUpdate();
	}
}
