package com.example.MockProject.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MockProject.Exception.ApiRequestException;
import com.example.MockProject.Model.Interviewer;
import com.example.MockProject.Repository.InterviewerRepo;

@RestController
@RequestMapping("/api/process")
@CrossOrigin(origins = "http://localhost:3000")
public class InterviewerController {

	@Autowired
    private InterviewerRepo iRepo;

	@PostMapping("/post/interviewer")
    ResponseEntity<Interviewer> createSlot(@Valid @RequestBody Interviewer interviewer) throws URISyntaxException {
    	
		if (iRepo.findInterviewer(interviewer.getPublicationDate(), interviewer.getEmail(), interviewer.getTag()) == 1 )
		{
			throw new ApiRequestException("slot already exist");
			
		}
			Interviewer result = iRepo.save(interviewer);
			return ResponseEntity.created(new URI("/api/process/interviewer" + result.getId())).body(result);
    }
	
	
	@GetMapping("/get/interviewer")
	List<?> findSlot(@Valid @RequestBody String email) throws URISyntaxException {
		
		return iRepo.findUserbyMail(email);
		
	}	
	
}
