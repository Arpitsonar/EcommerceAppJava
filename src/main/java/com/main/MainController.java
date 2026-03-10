package com.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Products.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MainController {
	private ProductService productService;
	
	@Autowired
	public MainController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@RequestMapping
	public String homePage(Model model, HttpSession session) {
		model.addAttribute("user", session.getAttribute("loggedUser"));
		model.addAttribute("top3", this.productService.top3());
		return "home";
	}
	
	@RequestMapping("/access_denied")
	public String noAccess() {
		return "access_denied";
	}
	
	
}
