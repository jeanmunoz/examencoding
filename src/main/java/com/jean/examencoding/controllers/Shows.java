package com.jean.examencoding.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jean.examencoding.models.Review;
import com.jean.examencoding.models.Show;
import com.jean.examencoding.models.User;
import com.jean.examencoding.services.ShowService;
import com.jean.examencoding.services.UserService;

@Controller
public class Shows {
	
	private ShowService showService;
	private UserService userService;

	public Shows(ShowService showService, UserService userService) {
		this.showService = showService;
		this.userService = userService;
	}
	
	
	//==================================
	// CREAMOS UN NUEVO SHOW (Y VERIFICAMOS QUE ESTE LOGUEADO EL USUARIO)
	//==================================
	
	@PostMapping("/shows")
	public String createShow(@Valid @ModelAttribute("show") Show show, BindingResult result, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "Ud debe estar logueado");
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "newShow.jsp";
		} else {
			Show s = showService.create(show);
			Long newShowId = s.getId();
			return String.format("redirect:/shows/%d", newShowId);
		}
	}
	
		//==================================
		// PAGINA PARA CREAR SHOWS (Y VERIFICAMOS QUE ESTE LOGUEADO EL USUARIO)
		//==================================
		
		@RequestMapping("/shows/new")
		public String newShow(Model model, @ModelAttribute("show") Show show, HttpSession session, RedirectAttributes flash) {
			Long userId = (Long) session.getAttribute("userId");
			if(userId == null) {
				flash.addFlashAttribute("error", "Ud debe estar logueado");
				return "redirect:/";
			}
			User u = userService.findUserById(userId);
			model.addAttribute("user", u);
			return "newShow.jsp";
		}
		
	//========================================
	// MOSTRAMOS UN SHOW POR SU ID (Y VERIFICAMOS QUE ESTE LOGUEADO EL USUARIO)
	//========================================
	
	@RequestMapping("/shows/{id}")
	public String showShow(Model model, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash, @Valid @ModelAttribute("review") Review review, BindingResult result) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "Ud debe estar logueado");
			return "redirect:/";
		}
		model.addAttribute("show", showService.findOneById(id));
		User u = userService.findUserById(userId);
		model.addAttribute("user", u);
		return "showShow.jsp";
	}
	
	//========================================
	// PAGINA DONDE VAMOS A EDITAR UN SHOW (Y VERIFICAMOS QUE ESTE LOGUEADO EL USUARIO)
	//========================================
	
	@RequestMapping("/shows/{id}/edit")
	public String editShow(Model model, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		Show s = showService.findOneById(id);
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "Ud debe estar logueado");
			return "redirect:/";
			
			//SI EL USUARIO NO INGRESO ESE SHOW NO LO PUEDE EDITAR! JA!
			
		} else if(userId != s.getUserT().getId()) {
			flash.addFlashAttribute("error", "ud no puede editar este show");
			return "redirect:/shows";
		}
		model.addAttribute("show", s);
		return "editShow.jsp";
	}
	
	//========================================
	// ACTUALIZAMOS UN SHOW (Y VERIFICAMOS QUE ESTE LOGUEADO EL USUARIO Y QUE ES "SUYO" ESE SHOW)
	//========================================
	
	@RequestMapping("/shows/{id}/update")
	public String updateShow(@Valid @ModelAttribute("show") Show show, BindingResult result, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		Show s = showService.findOneById(id);
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "Ud debe estar logueado");
			return "redirect:/";
		} else if(userId != s.getUserT().getId()) {
			flash.addFlashAttribute("error", "ud no puede editar este show");
			return "redirect:/shows";
		}
		
		if(result.hasErrors()) {
			return "editShow.jsp";
		} else {
			showService.update(show);
			return String.format("redirect:/shows/%d", id);
		}
	}
	
	//========================================
	// BORRAMOS UN SHOW (Y VERIFICAMOS QUE ESTE LOGUEADO EL USUARIO)
	//========================================
	
	@PostMapping("/shows/delete/{id}")
    public String destroy(@PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
    	Show s = showService.findOneById(id);
    	Long userId = (Long) session.getAttribute("userId");
    	if(userId == null) {
    		flash.addFlashAttribute("error", "Debe estar logueado");
    		return "redirect:/";
    	} else if(userId != s.getUserT().getId()) {
    		flash.addFlashAttribute("error", "No puede editar este show");
    		return "redirect:/shows";
    	}
    	
    	showService.delete(id);
    	return "redirect:/shows";
    }
	
	
}
