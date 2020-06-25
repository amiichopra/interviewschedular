package com.example.MockProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MockProject.Model.User;


public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query(
            value = "SELECT COUNT(*) FROM users WHERE users.email =:email",
            nativeQuery = true
    )
    int findByMail(String email);

	@Query( 
            value = "SELECT users.password FROM users WHERE users.email = :email",
            nativeQuery = true
    )
	List<?> findByPassword(String email);

	@Query(
            value = "SELECT * FROM users WHERE users.email = :email",
            nativeQuery = true
    )
	User findUserbyMail(String email);

}
