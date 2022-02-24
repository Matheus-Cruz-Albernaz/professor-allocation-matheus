package com.project.professor.allocation.service;

import java.util.List; 
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	// CRUD Ler todos
	public List<Department> findAll() {

		List<Department> departments = departmentRepository.findAll();
		return departments;

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

		Department depNew = departmentRepository.save(department);
		return depNew;

	}

	// CRUD Deletar pelo Id
	public void deleteById(Long id) {

		if (departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
		}
	}

	// CRUD Deletar tudo
	public void deleteAll() {

		departmentRepository.deleteAllInBatch();

	}
}