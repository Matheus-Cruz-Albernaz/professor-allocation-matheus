package com.project.professor.allocation.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;

@SpringBootTest
@TestPropertySource(locations = "classpath:course.properties")
public class CourseServiceTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Autowired
	CourseService courseService;

	@Test
	public void findAll() {
		// Act
		List<Course> courses = courseService.findAll();

		// Print
		courses.forEach(System.out::println);
	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Course courses = courseService.findById(id);

		// Print
		System.out.println(courses);
	}
	
	@Test
	public void save() throws ParseException {
		// Arrange
		Course courses = new Course();
		courses.setId(null);
		courses.setName("Qimica");

		// Act
		courses = courseService.create(courses);

		// Print
		System.out.println(courses);
	}

	@Test
	public void update() throws ParseException {
		// Arrange
		Course courses = new Course();
		courses.setId(1L);
		courses.setName("Química");

		// Act
		courses = courseService.update(courses);

		// Print
		System.out.println(courses);
	}
	
	@Test
	public void deleteById() {
		// Arrange
		Long id = 1L;

		// Act
		courseService.deleteById(id);
	}

	@Test
	public void deleteAll() {
		// Act
		courseService.deleteAll();
	}

}
