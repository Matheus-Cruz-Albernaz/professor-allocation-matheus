package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	// CRUD Ler todos
	public List<Course> findAll() {

		List<Course> courses = courseRepository.findAll();
		return courses;

	}

	// CRUD Ler Id
	public Course findById(Long id) {

		Optional<Course> optional = courseRepository.findById(id);
		Course courses = optional.orElse(null);
		return courses;

	}

	// CRUD Criar
	public Course create(Course course) {

		course.setId(null);
		return saveInternal(course);

	}

	// CRUD Atualizar
	public Course update(Course course) {

		Long id = course.getId();
		if (id != null && courseRepository.existsById(id)) {
			return saveInternal(course);
		} else {
			return null;
		}

	}

	private Course saveInternal(Course course) {

		Course courseNew = courseRepository.save(course);
		return courseNew;

	}

	// CRUD Deletar pelo Id
	public void deleteById(Long id) {

		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
		}
	}

	// CRUD Deletar tudo
	public void deleteAll() {

		courseRepository.deleteAllInBatch();

	}
}