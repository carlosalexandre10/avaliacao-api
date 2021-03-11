package br.com.cast.avaliacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cast.avaliacao.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{

	List<Curso> findByDescricao(String descricao);
}
