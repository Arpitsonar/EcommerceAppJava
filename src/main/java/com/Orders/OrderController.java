package com.Orders;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Login.User;
import com.Login.UserService;
import com.Products.Product;
import com.Products.ProductService;
import com.main.AllowedLevel;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private ProductService productService;
	private OrderService orderService;
	private UserService userService;
	
	
	@Autowired
	public OrderController(ProductService productService, OrderService orderService, UserService userService) {
		super();
		this.productService = productService;
		this.orderService = orderService;
		this.userService = userService;
	}



	@AllowedLevel(levels = {"CUSTOMER"})
	@GetMapping("/{productId}")
	public String order(@PathVariable("productId") int productId, HttpSession session) {
		Product product = this.productService.getById(productId);
		User user = (User)session.getAttribute("loggedUser");
		if(product == null) {
			return "";
		}
		this.orderService.createOrder(user, product, product.getProductName(), LocalDate.now(), (long) product.getPrice());
		this.productService.sold(product);
		this.userService.ordered(user, product.getPrice());
		
		return "redirect:/order/myorders";
	}
	
	@AllowedLevel(levels = {"CUSTOMER"})
	@GetMapping("/myorders")
	public String myorders(HttpSession session, Model model) {
		List<Order> orderList = this.orderService.getByUser((User)session.getAttribute("loggedUser"));
		model.addAttribute("orders", orderList);
		return "myorders";
	}
	
	@AllowedLevel(levels = {"CUSTOMER"})
	@GetMapping("/cancel/{orderId}")
	public String cancelOrder(@PathVariable("orderId") int orderId) {
		this.orderService.deleteOrder(orderId);
		
		return "redirect:/order/myorders";
	}
	
	@AllowedLevel(levels = {"ADMIN", "MANAGER"})
	@GetMapping("/all")
	public String products(Model model) {
		List<Order> orderList = this.orderService.getAll();
		if(orderList.isEmpty()) {
			return "Nothing";
		}
		model.addAttribute("orders",orderList);
		return "allOrders";
	}
	
}
