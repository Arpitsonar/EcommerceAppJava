package com.main;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HandlerMethod method = (HandlerMethod) handler;
		AllowedLevel allowedLevel = method.getMethodAnnotation(AllowedLevel.class);
		String path = request.getRequestURI();
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
		
		HttpSession session = request.getSession(false);
		System.out.println("Request for \"" + path + "\".");
		
		if(session == null || session.getAttribute("loggedUser") == null) {
			System.out.println("No Login");
			if(allowedLevel == null) {
				System.out.println("Annotation is not present");
				return true;
			}
			else {
				System.out.println("Annotation is present");
				response.sendRedirect("/Company/user/login");
				return false;
			}
		}
		else {
			System.out.println("Login YES");
			if(allowedLevel == null) {
				System.out.println("Annotation is not present");
				return true;
			}
			else {
				System.out.println("Annotation is present");
				if(List.of(allowedLevel.levels()).contains(session.getAttribute("level"))) {
					return true;
				}
				else {
					System.out.println("Not of This level " + Arrays.toString(allowedLevel.levels()));
					response.sendRedirect("/Company/access_denied");
					return false;
				}
			}
		}
		
	}
	
}
