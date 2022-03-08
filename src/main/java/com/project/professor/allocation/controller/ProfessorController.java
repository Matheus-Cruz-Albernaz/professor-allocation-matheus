package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.ProfessorService;

@RestController
public class ProfessorController {
	
	private final ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		super();
		this.professorService = professorService;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/professors", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Professor>> findAll() {

		List<Professor> professor = professorService.findAll();
		return new ResponseEntity<>(professor, HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/professors/{professor_id}")
	public ResponseEntity<Professor> findById(@PathVariable(name = "professor_id") Long id) {

		Professor professor = professorService.findById(id);

		if (professor != null) {
			return new ResponseEntity<>(professor,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/professor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professor> create(@RequestBody Professor professor) {
		
		try {
			Professor newProfessor = professorService.create(professor);
			return new ResponseEntity<>(newProfessor, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(path = "/professors/{professor_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professor> update(@PathVariable(name = "professor_id") Long id,@RequestBody Professor professor) {
		
		professor.setId(id);
		
		try {
			Professor newProfessor = professorService.update(professor);
			if(newProfessor != null) {
				return new ResponseEntity<>(newProfessor, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e){
			return null;
		}	
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/professors/{professor_id}")
	public ResponseEntity<Void> deleteById (@PathVariable (name = "professor_id") Long id) {
		
		professorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/professors")
	public ResponseEntity<Void> deleteAll() {
		
		professorService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}