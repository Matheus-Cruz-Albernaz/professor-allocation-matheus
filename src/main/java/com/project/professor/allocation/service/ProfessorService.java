package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;

	public ProfessorService(ProfessorRepository professorRepository) {
		super();
		this.professorRepository = professorRepository;
	}

	// CRUD Ler todos
	public List<Professor> findAll() {

		List<Professor> professors = professorRepository.findAll();
		return professors;

	}

	// CRUD Ler Id
	public Professor findById(Long id) {

		Optional<Professor> optional = professorRepository.findById(id);
		Professor professors = optional.orElse(null);
		return professors;

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

		Professor profNew = professorRepository.save(professor);
		return profNew;

	}

	// CRUD Deletar pelo Id
	public void deleteById(Long id) {

		if (professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
	}

	// CRUD Deletar tudo
	public void deleteAll() {

		professorRepository.deleteAllInBatch();

	}
}