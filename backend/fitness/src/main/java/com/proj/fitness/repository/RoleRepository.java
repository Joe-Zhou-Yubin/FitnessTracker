package com.proj.fitness.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.fitness.models.ERole;
import com.proj.fitness.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}