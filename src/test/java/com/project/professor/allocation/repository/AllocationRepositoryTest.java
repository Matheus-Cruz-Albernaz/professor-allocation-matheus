package com.project.professor.allocation.repository;

import com.project.professor.allocation.entity.Allocation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Autowired
	AllocationRepository allocationRepository;

	@Test
	public void findAll() {
		// Act
		List<Allocation> alloc = allocationRepository.findAll();

		// Print
		alloc.forEach(System.out::println);
	}

	@Test
	public void findById() {
		// Arrange
		Long id = 24L;

		// Act
		Allocation allocation = allocationRepository.findById(id).orElse(null);

		// Print
		System.out.println(allocation);

	}

	@Test
	public void findByProfessorId() {
		// Arrange
		Long professorId = 1L;

		// Act
		List<Allocation> allocations = allocationRepository.findByProfessorId(professorId);

		// Print
		allocations.forEach(System.out::println);

	}

	@Test
	public void findByCourseId() {
		// Arrange
		Long courseId = 2L;

		// Act
		List<Allocation> allocations = allocationRepository.findByCourseId(courseId);

		// Print
		allocations.forEach(System.out::println);
	}

	@Test
	public void save_create() throws ParseException {
		// Arrange
		Allocation allocation = new Allocation();
		allocation.setDay(DayOfWeek.THURSDAY);
		allocation.setCourseId(1L);
		allocation.setProfessorId(1L);
		allocation.setStart(sdf.parse("10:00-0300"));
		allocation.setEnd(sdf.parse("13:00-0300"));

		// Act
		Allocation alloc = allocationRepository.save(allocation);

		// Print
		System.out.println(alloc);

	}

	@Test
	public void save_update() throws ParseException {
		// Arrange
		Allocation allocation = new Allocation();
		allocation.setId(25L);
		allocation.setDay(DayOfWeek.FRIDAY);
		allocation.setCourseId(1L);
		allocation.setProfessorId(1L);
		allocation.setStart(sdf.parse("10:00-0300"));
		allocation.setEnd(sdf.parse("13:00-0300"));

		// Act
		Allocation alloc = allocationRepository.save(allocation);

		// Print
		System.out.println(alloc);

	}

	@Test
	public void deleteById() {
		// Arrange
		Long id = 26L;
		
		// Act
		allocationRepository.deleteById(id);

	}

	@Test
	public void deleteAll() {
		// Act
		allocationRepository.deleteAllInBatch();
	}
}