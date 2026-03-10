package com.Login;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.main.AllowedLevel;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@PostMapping("/login")
	public String formSubmit(@RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes redirectAttributes, HttpSession session) {
		User user = this.userService.checkUser(username, password);
		if(user == null) {
			redirectAttributes.addFlashAttribute("error", "Username or passwrd is not correct !");
			return "redirect:/user/login";
		}
		session.setAttribute("loggedUser", user);
		session.setAttribute("level", user.getUserlevel());
		return "redirect:/";
	}
	
	//--------------------------------------------
	
	@GetMapping("/register")
	public String register(Model model) {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerForm(@ModelAttribute User user, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) {
		if(result.hasErrors()) {
			redirectAttributes.addAttribute("error","Fill the form correctly !");
			return "redirect:/user/register";
		}
		this.userService.save(user);
		session.setAttribute("loggedUser", user);
		session.setAttribute("level", user.getUserlevel().toUpperCase());
		return "redirect:/";
	}
	
	//----------------------------------------------
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loggedUser");
		session.removeAttribute("level");
		session.invalidate();
		return "redirect:/";
	}
	//----------------------------------------------
	
	@AllowedLevel(levels = {"MANAGER", "ADMIN"})
	@GetMapping("/all")
	public String allUsers(HttpSession session, Model model) {
		if(((User)session.getAttribute("loggedUser")).getUserlevel().equals("MANAGER")) {
			List<User> customers = this.userService.getAllCustomers();
			model.addAttribute("users", customers);
		}
		else {
			List<User> users = this.userService.getAll();
			model.addAttribute("users", users);
		}
		return "allUsers";
	}
	
	@AllowedLevel(levels = {"MANAGER", "ADMIN"})
	@GetMapping("/delete/{userId}")
	public String deleteUser(@PathVariable("userId") int userId) {
		User user = this.userService.getById(userId);
		if(user != null) {
			this.userService.deleteUser(user);
		}
		
		return "redirect:/user/all";
	}
}
