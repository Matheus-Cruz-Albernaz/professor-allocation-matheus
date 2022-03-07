package com.project.professor.allocation.repository;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {

	@Autowired
	ProfessorRepository professorRepository;

	@Test
	public void findAll() {
		// Act
		List<Professor> prof = professorRepository.findAll();

		// Print
		System.out.println(prof);
	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Optional<Professor> optional = professorRepository.findById(id);

		// Print
		Professor prof = optional.orElse(null);
		System.out.println(prof);

	}

	@Test
	public void findByDepartmentId() {
		// Arrange
		Long courseId = 2L;

		// Act
		List<Professor> optional = professorRepository.findByDepartmentId(courseId);

		// Print
		System.out.println(optional);

	}

	@Test
	public void save_create() throws ParseException {
		// Arrange
		Professor professor = new Professor();
		professor.setCpf("965.735.612-03");
		professor.setDepartmentId(1L);
		professor.setName("Rafael Duarte");

		// Act
		Professor prof = professorRepository.save(professor);

		// Print
		System.out.println(prof);

	}

	@Test
	public void save_update() throws ParseException {
		// Arrange
		Professor professor = new Professor();
		professor.setId(2L);
		professor.setName("Matheus Cruz Albernaz");

		// Act
		Professor prof = professorRepository.save(professor);

		// Print
		System.out.println(prof);

	}

	@Test
	public void deleteById() {
		// Arrange
		Long id = 1L;

		// Act
		professorRepository.deleteById(id);

	}

	@Test
	public void deleteAll() {
		// Act
		professorRepository.deleteAllInBatch();

	}
}