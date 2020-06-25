package com.example.MockProject.Repository;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MockProject.Model.Interviewer;


public interface InterviewerRepo extends JpaRepository<Interviewer, Long>{

	@Query(
            value = "SELECT COUNT(*) FROM interviewer WHERE interviewer.publication_date = :date and interviewer.email = :mail and interviewer.tag = :tag ",
            nativeQuery = true
    )
    int findInterviewer(Date date, String mail, String tag );
	
	@Query(
            value = "SELECT * FROM interviewer WHERE interviewer.email = :email",
            nativeQuery = true
    )
	List<?> findUserbyMail(String email);

}
