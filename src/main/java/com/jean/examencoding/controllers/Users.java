package com.jean.examencoding.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jean.examencoding.models.Show;
import com.jean.examencoding.models.User;
import com.jean.examencoding.services.ShowService;
import com.jean.examencoding.services.UserService;
import com.jean.examencoding.validators.UserValidator;

@Controller
public class Users {
	
    private final UserService userService;
    private final UserValidator userValidator;
    private final ShowService showService;
    
    public Users(UserService userService, UserValidator userValidator, ShowService showService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.showService = showService;
    }
    
     //=============================================================
    // REGISTRO Y LOGIN
    // Y SI ESTA LOGUEADO LO ENVIA DE INMEDIATO A LA LISTA DE SHOWS
    //=============================================================
    
    @RequestMapping("/")
    public String registerForm(@ModelAttribute("user") User user, HttpSession session) {
  	Long userId = (Long) session.getAttribute("userId");
	  	if(userId != null) {
	  		return "redirect:/shows";
	  	}
	  	return "index.jsp";
    }
    
     //=============================================================
    // VAMOS A CREAR UN NUEVO USUARIO
    //=============================================================
    
    @RequestMapping(value="/users", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	    	userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "index.jsp";
		} else {
			User u = userService.registerUser(user);
			session.setAttribute("userId",  u.getId());;
			return "redirect:/shows";
		}
    }
    
    //=============================================================
    // LOGIN
    //=============================================================
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, @ModelAttribute("user") User user) {
    	boolean isAuthenticated = userService.authenticateUser(email, password);
    	if(isAuthenticated) {
    		User u = userService.findByEmail(email);
    		session.setAttribute("userId", u.getId());
    		return "redirect:/shows";
    	} else {
    		model.addAttribute("error", "Datos incorrectos");
    		return "index.jsp";
    	}
    }
    
    //=============================================================
    // USUARIO EN EL DASHBOARD DE SHOWS
    //=============================================================
    
    @RequestMapping("/shows")
    public String home(HttpSession session, Model model, RedirectAttributes flash) {
    	Long userId = (Long) session.getAttribute("userId");
    	if(userId == null) {
    		flash.addFlashAttribute("error", "Ud debe loguearse antes");
    		return "redirect:/login";
    	}
    	User u = userService.findUserById(userId);
    	List<Show> shows = showService.findAll();
    	model.addAttribute("user", u);
    	model.addAttribute("shows", shows);
    	return "dashboard.jsp";
    }
    
    //=============================================================
    // SALIR DEL SISTEMA - LOGOUT
    //=============================================================
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
}
