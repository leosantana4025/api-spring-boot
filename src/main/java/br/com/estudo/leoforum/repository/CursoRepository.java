package br.com.estudo.leoforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudo.leoforum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nomeCurso);

}
