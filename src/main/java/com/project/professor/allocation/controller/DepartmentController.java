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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;

@RestController
@RequestMapping(path = "departments")
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Department>> findAll(@RequestParam(name = "name", required = false) String name) {

		List<Department> departments = departmentService.findAll(name);
		return new ResponseEntity<>(departments, HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/{department_id}")
	public ResponseEntity<Department> findById(@PathVariable(name = "department_id") Long id) {

		Department department = departmentService.findById(id);

		if (department != null) {
			return new ResponseEntity<>(department,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> create(@RequestBody Department department) {
		
		try {
			Department newDepartment = departmentService.create(department);
			return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(path = "/{department_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> update(@PathVariable(name = "department_id") Long id,@RequestBody Department department) {
		
		department.setId(id);
		
		try {
			Department newDepartment = departmentService.update(department);
			if(newDepartment != null) {
				return new ResponseEntity<>(newDepartment, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{department_id}")
	public ResponseEntity<Void> deleteById (@PathVariable (name = "department_id") Long id) {
		
		departmentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping
	public ResponseEntity<Void> deleteAll() {
		
		departmentService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}