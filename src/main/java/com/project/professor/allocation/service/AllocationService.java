package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {

	private final AllocationRepository allocationRepository;
	private final CourseService courseService;
	private final ProfessorService professorService;

	public AllocationService(AllocationRepository allocationRepository, CourseService courseService, ProfessorService professorService) {
		super();
		this.allocationRepository = allocationRepository;
		this.courseService = courseService;
		this.professorService = professorService;
	}

	// CRUD Ler todos
	public List<Allocation> findAll() {

		List<Allocation> allocations = allocationRepository.findAll();
		return allocations;

	}

	// CRUD Ler pelo Id
	public Allocation findById(Long id) {

		Optional<Allocation> optional = allocationRepository.findById(id);
		Allocation allocation = optional.orElse(null);
		return allocation;

	}
	
	public List <Allocation> findByCourseId(Long courseId) {

		return allocationRepository.findByCourseId(courseId);
	}
	
	public List <Allocation> findByProfessorId(Long professorId) {

		return allocationRepository.findByProfessorId(professorId);
	}
	
	// CRUD Criar
	public Allocation create(Allocation allocation) {

		allocation.setId(null);
		return saveInternal(allocation);

	}

	// CRUD Atualizar
	public Allocation update(Allocation allocation) {

		Long id = allocation.getId();
		if (id != null && allocationRepository.existsById(id)) {
			return saveInternal(allocation);
		} else {
			return null;
		}

	}
	
	private Allocation saveInternal(Allocation allocation) {
        if (!isEndHourGreaterThanStartHour(allocation) || hasCollision(allocation)) {
            throw new RuntimeException();
        } else {
            allocation = allocationRepository.save(allocation);

            Professor professor = professorService.findById(allocation.getProfessorId());
            allocation.setProfessor(professor);

            Course course = courseService.findById(allocation.getCourseId());
            allocation.setCourse(course);

            return allocation;
        }
    }

	// CRUD Deletar pelo Id
	public void deleteById(Long id) {
		
		if (id != null && allocationRepository.existsById(id)) {
			allocationRepository.deleteById(id);
		} 
	}
	
	// CRUD Deletar tudo
	public void deleteAll() {
		
		allocationRepository.deleteAllInBatch();
		
	}
	
	boolean hasCollision(Allocation newAllocation) {
		boolean hasCollision = false;

		List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessorId());

		for (Allocation currentAllocation : currentAllocations) {
			hasCollision = hasCollision(currentAllocation, newAllocation);
			if (hasCollision) {
				break;
			}
		}

		return hasCollision;
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId())
				&& currentAllocation.getDay() == newAllocation.getDay()
				&& currentAllocation.getStart().compareTo(newAllocation.getEnd()) < 0
				&& newAllocation.getStart().compareTo(currentAllocation.getEnd()) < 0;
	}
	
	boolean isEndHourGreaterThanStartHour(Allocation allocation) {
        return allocation != null && allocation.getStart() != null && allocation.getEnd() != null
                && allocation.getEnd().compareTo(allocation.getStart()) > 0;
    }
}
