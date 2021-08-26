package com.jean.examencoding.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jean.examencoding.models.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>{

}
