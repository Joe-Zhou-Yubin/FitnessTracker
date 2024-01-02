package com.proj.fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proj.fitness.models.CustomCategory;
import com.proj.fitness.repository.CustomCategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/cust")
public class CustomCategoryController {

    @Autowired
    private CustomCategoryRepository customCategoryRepository;

    // Add a new custom category
    @PostMapping("/add")
    public ResponseEntity<CustomCategory> addCategory(@RequestBody CustomCategory customCategory) {
        CustomCategory savedCategory = customCategoryRepository.save(customCategory);
        return ResponseEntity.ok(savedCategory);
    }

    // List all custom categories
    @GetMapping("/list")
    public ResponseEntity<List<CustomCategory>> listCategories() {
        List<CustomCategory> categories = customCategoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }
    
 // List all unique custom categories
    @GetMapping("/list/unique")
    public ResponseEntity<List<String>> listUniqueCategories() {
        List<String> uniqueCategories = customCategoryRepository.findDistinctCategory();
        return ResponseEntity.ok(uniqueCategories);
    }

    // List custom categories by a specific category
    @GetMapping("/list/category/{categoryName}")
    public ResponseEntity<List<CustomCategory>> listCategoriesByCategory(@PathVariable("categoryName") String categoryName) {
        List<CustomCategory> categories = customCategoryRepository.findByCategory(categoryName);
        return ResponseEntity.ok(categories);
    }

    // Delete a custom category by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        if (!customCategoryRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Error: Category not found.");
        }
        customCategoryRepository.deleteById(id);
        return ResponseEntity.ok("Category deleted successfully.");
    }
}
