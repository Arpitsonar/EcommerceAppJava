package com.Products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.main.AllowedLevel;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/products")
public class ProductController {
	private ProductService productService;
	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping
	public String allProducts(Model model, HttpSession session) {
		List<Product> productList = this.productService.getAll();
		if(productList.isEmpty()) {
			return "Nothing";
		}
		model.addAttribute("level", session.getAttribute("level"));
		model.addAttribute("products", productList);
		return "allProducts";
	}
	
	@GetMapping("/{id}")
	public String productById(@PathVariable("id") int id,Model model) {
		Product product = this.productService.getById(id);
		if(product == null) {
			return "Nothing";
		}
		model.addAttribute("product", product);
		return "product";
	}
	
	@AllowedLevel(levels = {"ADMIN", "MANAGER"})
	@GetMapping("/edit/{productId}")
	public String editProducts(@PathVariable("productId") int productId,Model model) {
		Product product = this.productService.getById(productId);
		if(product == null) {
			return "Nothing";
		}
		model.addAttribute("product", product);
		return "productForm";
	}
	
	@AllowedLevel(levels = {"ADMIN", "MANAGER"})
	@PostMapping("/edit/{productId}")
	public String editProductsPost(@ModelAttribute Product product, BindingResult bindingResult, @PathVariable("productId") int productId, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Fill the form correctly !");
			return "redirect:/products/edit/" + productId;
		}
		this.productService.update(product, productId);
		return "redirect:/products";
	}
	
	@AllowedLevel(levels = {"ADMIN"})
	@GetMapping("/delete/{productId}")
	public String deleteProducts(@PathVariable("productId") int productId) {
		this.productService.deleteProduct(this.productService.getById(productId));
		return "redirect:/products";
	}
	
	@AllowedLevel(levels = {"ADMIN"})
	@GetMapping("/new")
	public String newProducts() {
		return "productForm";
	}
	
	@AllowedLevel(levels = {"ADMIN"})
	@PostMapping("/new")
	public String newProductsPost(@ModelAttribute Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Fill the form correctly !");
			return "redirect:/products/new";
		}
		this.productService.save(product);
		return "redirect:/products";
	}
	
}
