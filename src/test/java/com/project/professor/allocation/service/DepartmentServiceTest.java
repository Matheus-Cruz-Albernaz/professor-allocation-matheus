package com.project.professor.allocation.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;

@SpringBootTest
@TestPropertySource(locations = "classpath:department.properties")
public class DepartmentServiceTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Autowired
	DepartmentService departmentService;
	
	@Test
	public void findAll() {
		// Act
		List<Department> dep = departmentService.findAll();

		// Print
		dep.forEach(System.out::println);
	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Department dep = departmentService.findById(id);

		// Print
		System.out.println(dep);
	}
	
	@Test
	public void save() throws ParseException {
		// Arrange
		Department dep = new Department();
		dep.setId(null);
		dep.setName("UniQualiti");

		// Act
		dep = departmentService.create(dep);

		// Print
		System.out.println(dep);
	}

	@Test
	public void update() throws ParseException {
		// Arrange
		Department dep = new Department();
		dep.setId(1L);
		dep.setName("Universidade Qualiti");

		// Act
		dep = departmentService.update(dep);

		// Print
		System.out.println(dep);
	}
	
	@Test
	public void deleteById() {
		// Arrange
		Long id = 1L;

		// Act
		departmentService.deleteById(id);
	}

	@Test
	public void deleteAll() {
		// Act
		departmentService.deleteAll();
	}

}
