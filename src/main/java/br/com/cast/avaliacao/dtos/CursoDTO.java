package br.com.cast.avaliacao.dtos;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.model.Curso;
import lombok.Data;

@Data
public class CursoDTO {
	private Long id;
	private String descricao;
	private LocalDateTime dataInicio;
	private LocalDateTime dataTermino;
	private Integer quantidadeAlunosPorTurma;
	private Long codigoCategoria;
	private Categoria categoria;

	public static CursoDTO create(Curso curso) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(curso, CursoDTO.class);
		
	}
}
