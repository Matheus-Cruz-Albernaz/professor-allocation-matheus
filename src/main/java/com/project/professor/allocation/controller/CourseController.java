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

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.service.CourseService;

@RestController
public class CourseController {
	
	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Course>> findAll() {

		List<Course> courses = courseService.findAll();
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/courses/{course_id}")
	public ResponseEntity<Course> findById(@PathVariable(name = "course_id") Long id) {

		Course course = courseService.findById(id);

		if (course != null) {
			return new ResponseEntity<>(course,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/courses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> create(@RequestBody Course course) {
		
		try {
			Course newCourse = courseService.create(course);
			return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(path = "/courses/{course_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> update(@PathVariable(name = "course_id") Long id,@RequestBody Course course) {
		
		course.setId(id);
		
		try {
			Course newCourse = courseService.update(course);
			if(newCourse != null) {
				return new ResponseEntity<>(newCourse, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e){
			return null;
		}	
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/courses/{course_id}")
	public ResponseEntity<Void> deleteById (@PathVariable (name = "course_id") Long id) {
		
		courseService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/courses")
	public ResponseEntity<Void> deleteAll() {
		
		courseService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}