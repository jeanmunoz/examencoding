package com.jean.examencoding.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jean.examencoding.models.Show;

@Repository

//======================
// USARE NATIVE QUERY 
//=====================
public interface ShowRepository extends CrudRepository<Show, Long>{
	@Query(value="SELECT * FROM shows ORDER BY avg_rating DESC;", nativeQuery=true)
	List<Show> findAll();
}