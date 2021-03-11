package br.com.cast.avaliacao.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity()
@Data
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 300)
	@NotEmpty(message = "{curso.campo.descricao.obrigatorio}")
	private String descricao;

	@Column(name = "data_inicio", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "{curso.campo.dataInicio.obrigatorio}")
	private LocalDateTime dataInicio;

	@Column(name = "data_termino", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "{curso.campo.dataTermino.obrigatorio}")
	private LocalDateTime dataTermino;

	@Column(name = "quantidade_alunos_por_turma")
	private Integer quantidadeAlunosPorTurma;

	@ManyToOne
	@JoinColumn(name = "categoria_codigo", nullable = false)
	@NotNull(message = "{curso.campo.categoria.obrigatorio}")
	private Categoria categoria;
}
