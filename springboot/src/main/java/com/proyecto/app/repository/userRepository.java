package com.proyecto.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.app.model.User;

@Repository
public interface userRepository extends JpaRepository<User, Long>{
	
}
