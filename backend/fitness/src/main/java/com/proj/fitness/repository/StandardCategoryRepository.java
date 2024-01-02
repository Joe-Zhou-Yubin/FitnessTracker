package com.proj.fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.proj.fitness.models.StandardCategory;

@Repository
public interface StandardCategoryRepository extends JpaRepository<StandardCategory, Long> {
	@Query("SELECT DISTINCT sc.category FROM StandardCategory sc")
	List<String> findDistinctCategory();
	
    List<StandardCategory> findByCategory(String category);

}
