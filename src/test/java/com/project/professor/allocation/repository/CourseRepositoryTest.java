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

import com.project.professor.allocation.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepository;

	@Test
	public void findAll() {
		// Act
		List<Course> courses = courseRepository.findAll();

		// Print
		System.out.println(courses);

	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Optional<Course> optional = courseRepository.findById(id);

		// Print
		Course course = optional.orElse(null);
		System.out.println(course);

	}

	@Test
	public void save_create() throws ParseException {
		// Arrange
		Course course = new Course();
		course.setName("DeepCode");

		// Act
		Course courses = courseRepository.save(course);

		// Print
		System.out.println(courses);
	}

	@Test
	public void save_update() throws ParseException {
		// Arrange
		Course course = new Course();
		course.setId(2L);
		course.setName("Deep Code 1");

		// Act
		Course courses = courseRepository.save(course);

		// Print
		System.out.println(courses);

	}

	@Test
	public void deleteById() {
		// Arrange
		Long id = 1L;

		// Act
		courseRepository.deleteById(id);

	}

	@Test
	public void deleteAll() {
		// Act
		courseRepository.deleteAllInBatch();

	}
}