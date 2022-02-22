package com.project.professor.allocation.repository;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository departmentRepository;

	@Test
	public void findAll() {
		// Act
		List<Department> dep = departmentRepository.findAll();

		// Print
		System.out.println(dep);
	}

	@Test
	public void findById() {
		// Arrange

		// Act

		// Print

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
		Department department = new Department();
		department.setName("UniQualiti");

		// Act
		Department dep = departmentRepository.save(department);

		// Print
		System.out.println(dep);

	}

	@Test
	public void save_update() throws ParseException {
		// Arrange
		Department department = new Department();
		department.setId(3L);
		department.setName("Universidade Qualiti");

		// Act
		Department dep = departmentRepository.save(department);

		// Print
		System.out.println(dep);

	}

	@Test
	public void deleteById() {
		// Arrange

		// Act

	}

	@Test
	public void deleteAll() {
		// Act

	}
}