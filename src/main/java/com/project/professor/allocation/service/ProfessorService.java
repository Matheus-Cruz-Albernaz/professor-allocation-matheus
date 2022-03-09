package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final AllocationRepository allocationRepository;
	private final DepartmentService departmentService;

	public ProfessorService(ProfessorRepository professorRepository, AllocationRepository allocationRepository, DepartmentService departmentService) {
		super();
		this.professorRepository = professorRepository;
		this.allocationRepository = allocationRepository;
		this.departmentService = departmentService;
	}

	// CRUD Ler todos
	public List<Professor> findAll(String name) {
		
		if (name == null) {
			return professorRepository.findAll();
		} else {
			return professorRepository.findByNameContainingIgnoreCase(name);
		}
	}

	// CRUD Ler Id
	public Professor findById(Long id) {

		Optional<Professor> optional = professorRepository.findById(id);
		Professor professors = optional.orElse(null);
		return professors;

	}
	
	public List <Professor> findByDepartmentId(Long id) {

		return professorRepository.findByDepartmentId(id);

	}

	// CRUD Criar
	public Professor create(Professor professor) {

		professor.setId(null);
		return saveInternal(professor);

	}

	// CRUD Atualizar
	public Professor update(Professor professor) {

		Long id = professor.getId();
		if (id != null && professorRepository.existsById(id)) {
			return saveInternal(professor);
		} else {
			return null;
		}

	}
	
	private Professor saveInternal(Professor professor) {
        professor = professorRepository.save(professor);

        Department department = departmentService.findById(professor.getDepartmentId());
        professor.setDepart(department);

        List<Allocation> allocations = allocationRepository.findByProfessorId(professor.getId());
        professor.setAllocations(allocations);

        return professor;
    }

	// CRUD Deletar pelo Id
	public void deleteById(Long id) {

		if (id != null && professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
	}

	// CRUD Deletar tudo
	public void deleteAll() {

		professorRepository.deleteAllInBatch();

	}
}