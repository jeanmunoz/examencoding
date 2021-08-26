package com.jean.examencoding.services;

import org.springframework.stereotype.Service;

import com.jean.examencoding.models.Review;
import com.jean.examencoding.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	private ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	public Review create(Review review) {
		return reviewRepository.save(review);
	}
}
