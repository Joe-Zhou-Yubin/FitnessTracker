package com.proj.fitness.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "custom_category")
public class CustomCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "exercise", nullable = false)
    private String exercise;

	public CustomCategory() {
	}

	public CustomCategory(Long id, String category, String exercise) {
		this.id = id;
		this.category = category;
		this.exercise = exercise;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	@Override
	public String toString() {
		return "CustomCategory [id=" + id + ", category=" + category + ", exercise=" + exercise + "]";
	}

    
}
