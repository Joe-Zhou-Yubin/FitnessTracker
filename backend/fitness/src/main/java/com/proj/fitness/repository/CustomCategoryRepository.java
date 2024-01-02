package com.proj.fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proj.fitness.models.CustomCategory;


@Repository
public interface CustomCategoryRepository extends JpaRepository<CustomCategory, Long> {
	
	@Query("SELECT DISTINCT sc.category FROM CustomCategory sc")
	List<String> findDistinctCategory();
	
    List<CustomCategory> findByCategory(String category);
}
