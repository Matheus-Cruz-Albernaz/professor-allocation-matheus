package com.project.professor.allocation.repository;

import java.text.ParseException;
import java.util.List;

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
		courses.forEach(System.out::println);

	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Course course = courseRepository.findById(id).orElse(null);

		// Print
		System.out.println(course);

	}

	@Test
	public void findByNameContainingIgnoreCase() {
		// Arrange
		String name = "Course";

		// Act
		List<Course> courses = courseRepository.findByNameContainingIgnoreCase(name);

		// Print
		courses.forEach(System.out::println);
	}

	@Test
	public void save_create() throws ParseException {
		// Arrange
		Course courses = new Course();
		courses.setName("DeepCode");

		// Act
		courses = courseRepository.save(courses);

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
		course = courseRepository.save(course);

		// Print
		System.out.println(course);

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