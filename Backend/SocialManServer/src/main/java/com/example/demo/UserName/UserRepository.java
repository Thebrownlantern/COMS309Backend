package com.example.demo.UserName;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> { 
	// SELECT * FROM user u WHERE u.name = :name 
	public Optional<User> findByName(String name);
	
	public Optional<User> findUserByEmailAndPassword(String email, String password);

}
