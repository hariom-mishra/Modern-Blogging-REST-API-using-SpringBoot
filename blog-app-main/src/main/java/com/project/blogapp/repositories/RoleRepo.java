package com.project.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blogapp.Model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
