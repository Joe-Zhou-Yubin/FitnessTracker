package com.proj.fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proj.fitness.models.StandardCategory;
import com.proj.fitness.repository.StandardCategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/cat")
public class StandardCategoryController {

    @Autowired
    private StandardCategoryRepository standardCategoryRepository;

    // Add a new standard category
    @PostMapping("/add")
    public ResponseEntity<StandardCategory> addCategory(@RequestBody StandardCategory standardCategory) {
        StandardCategory savedCategory = standardCategoryRepository.save(standardCategory);
        return ResponseEntity.ok(savedCategory);
    }

    // List all standard categories
    @GetMapping("/list")
    public ResponseEntity<List<StandardCategory>> listCategories() {
        List<StandardCategory> categories = standardCategoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping("/list/unique")
    public ResponseEntity<List<String>> listUniqueCategories() {
        List<String> uniqueCategories = standardCategoryRepository.findDistinctCategory();
        return ResponseEntity.ok(uniqueCategories);
    }
    
    @GetMapping("/list/category/{categoryName}")
    public ResponseEntity<List<StandardCategory>> listCategoriesByCategory(@PathVariable("categoryName") String categoryName) {
        List<StandardCategory> categories = standardCategoryRepository.findByCategory(categoryName);
        return ResponseEntity.ok(categories);
    }


    // Delete a standard category by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        if (!standardCategoryRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Error: Category not found.");
        }
        standardCategoryRepository.deleteById(id);
        return ResponseEntity.ok("Category deleted successfully.");
    }
}
