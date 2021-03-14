package br.com.cast.avaliacao.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cast.avaliacao.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	List<Curso> findByDescricao(String descricao);

	@Query("select count(c.id) from Curso c where (c.dataInicio between ?1 and ?2) or (c.dataTermino between ?1 and ?2)")
	Integer cursosDentroDoMesmoPeriodo(LocalDateTime dataInicio, LocalDateTime dataTermino);
}
