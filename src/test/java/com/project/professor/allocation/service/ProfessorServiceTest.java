package com.project.professor.allocation.service;

import java.text.ParseException; 
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:professor.properties")
public class ProfessorServiceTest {

	@Autowired
	ProfessorService professorService;
	
	@Test
	public void findAll() {
		// Act
		List<Professor> prof = professorService.findAll(null);

		// Print
		prof.forEach(System.out::println);
	}
	
	@Test
	public void findAllbyName() {
		// Arrange
		String name = "professor";
		
		// Act
		List<Professor> prof = professorService.findAll(name);

		// Print
		prof.forEach(System.out::println);
	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Professor prof = professorService.findById(id);

		// Print
		System.out.println(prof);
	}
	
	@Test
	public void findByDepartmentId() {
		// Arrange
		Long id = 1L;

		// Act
		List <Professor> prof = professorService.findByDepartmentId(id);

		// Print
		System.out.println(prof);
	}
	
	@Test
	public void save() throws ParseException {
		// Arrange
		Professor prof = new Professor();
		prof.setId(null);
		prof.setCpf("17298791877");
		prof.setDepartmentId(2L);
		prof.setName("Thiago Santos");
		

		// Act
		prof = professorService.create(prof);

		// Print
		System.out.println(prof);
	}

	@Test
	public void update() throws ParseException {
		// Arrange
		Professor prof = new Professor();
		prof.setId(1L);
		prof.setName("Tiago Santos");
		prof.setCpf("172.987.918-77");
		prof.setDepartmentId(2L);

		// Act
		prof = professorService.update(prof);

		// Print
		System.out.println(prof);
	}
	
	@Test
	public void deleteById() {
		// Arrange
		Long id = 1L;

		// Act
		professorService.deleteById(id);
	}

	@Test
	public void deleteAll() {
		// Act
		professorService.deleteAll();
	}

}
