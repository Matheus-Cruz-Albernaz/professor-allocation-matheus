package com.project.professor.allocation.repository;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
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

		// Act
		Optional<Professor> optional = professorRepository.findById(2L);

		// Print
		Professor prof = optional.orElse(null);
		System.out.println(prof);

	}

	@Test
	public void findByProfessorId() {
		// Arrange

		// Act

		// Print

	}

	@Test
	public void findByCourseId() {
		// Arrange

		// Act

		// Print

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

		// Act
		professorRepository.deleteById(2L);

	}

	@Test
	public void deleteAll() {
		// Act
		professorRepository.deleteAll();

	}
}