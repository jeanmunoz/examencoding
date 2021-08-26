package com.jean.examencoding.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jean.examencoding.models.Review;
import com.jean.examencoding.models.Show;
import com.jean.examencoding.models.User;
import com.jean.examencoding.services.ReviewService;
import com.jean.examencoding.services.ShowService;
import com.jean.examencoding.services.UserService;

@Controller
public class Reviews {
	
	private ReviewService reviewService;
	private ShowService showService;
	private UserService userService;

	public Reviews(ReviewService reviewService, ShowService showService, UserService userService) {
		this.reviewService = reviewService;
		this.showService = showService;
		this.userService = userService;
	}
	
	@PostMapping("/shows/{id}/review")
	public String review(@Valid @ModelAttribute("review") Review review, BindingResult result, Model model, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "Debe estar logueado");
			return String.format("redirect:/shows/%d", id);
		}
		if(result.hasErrors()) {
			model.addAttribute("show", showService.findOneById(id));
			model.addAttribute("user", userService.findUserById(userId));
			return "showShow.jsp";
		} else {
			User u = userService.findUserById(userId);
			Show s = showService.findOneById(id);
			List<Review> reviews = s.getReviews();
		//=======================================================	
		//UN MISMO USUARIO NO PUEDE RATIAR MAS DE UNA VEZ UN MISMO SHOW
		//=======================================================
			for(Review r: reviews) {
				if(r.getUser().getId() == u.getId()) {
					flash.addFlashAttribute("error", "Ud ya hizo un review de este show");
					return String.format("redirect:/shows/%d", id);
				}
			}
			
			review.setId(null);
			Review newR = reviewService.create(review);
			if(s.getAvgRating() == null) {
				s.setAvgRating(newR.getRating()/1.0);
				showService.update(s);
			} else {
				Double sum = 0.0;
				for(Review r: reviews) {
					System.out.println("rating: " + r.getRating());
					sum += r.getRating();
				}
				sum += newR.getRating();
				System.out.println(sum);
				System.out.println("average: " + sum/(reviews.size() + 1));
				s.setAvgRating(sum/(reviews.size()+1));
				showService.update(s);
			}
			return String.format("redirect:/shows/%d", id);
		}
	}
}
