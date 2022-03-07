package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.AllocationService;

@RestController
public class AllocationController {

	private final AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}

	@ResponseStatus(HttpStatus.OK)
	// Find All - Allocation
	// GET http://localhost:8080/departments
	@GetMapping(path = "/departments")
	public ResponseEntity<List<Allocation>> findAll() {

		List<Allocation> allocations = allocationService.findAll();
		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}

	public Allocation findById(Long id) {

		Allocation allocation = allocationService.findById(id);

		if (allocation != null) {
			return allocation;
		} else {
			return null;	
		}
	}
}