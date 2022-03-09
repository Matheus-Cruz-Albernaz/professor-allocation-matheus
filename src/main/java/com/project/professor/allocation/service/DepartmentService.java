package com.project.professor.allocation.service;

import java.util.List; 
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final ProfessorRepository professorRepository;

	public DepartmentService(DepartmentRepository departmentRepository, ProfessorRepository professorRepository) {
		super();
		this.departmentRepository = departmentRepository;
		this.professorRepository = professorRepository;
	}

	// CRUD Ler todos
	public List<Department> findAll(String name) {
		
		if (name == null) {
			return departmentRepository.findAll();
		} else {
			return departmentRepository.findByNameContainingIgnoreCase(name);
		}
	}

	// CRUD Ler Id
	public Department findById(Long id) {

		Optional<Department> optional = departmentRepository.findById(id);
		Department departments = optional.orElse(null);
		return departments;

	}

	// CRUD Criar
	public Department create(Department department) {

		department.setId(null);
		return saveInternal(department);

	}

	// CRUD Atualizar
	public Department update(Department department) {

		Long id = department.getId();
		if (id != null && departmentRepository.existsById(id)) {
			return saveInternal(department);
		} else {
			return null;
		}

	}

	private Department saveInternal(Department department) {
        department = departmentRepository.save(department);

        List<Professor> professors = professorRepository.findByDepartmentId(department.getId());
        department.setProfessors(professors);

        return department;
    }

	// CRUD Deletar pelo Id
	public void deleteById(Long id) {

		if (id != null && departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
		}
	}

	// CRUD Deletar tudo
	public void deleteAll() {

		departmentRepository.deleteAllInBatch();

	}
}