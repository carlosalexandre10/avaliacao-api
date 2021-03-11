package br.com.cast.avaliacao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.avaliacao.dtos.CursoDTO;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	public List<CursoDTO> pesquisarPorDescricao(String descricao) {
		return this.cursoRepository.findByDescricao(descricao).stream().map(CursoDTO::create)
				.collect(Collectors.toList());
	}

	public CursoDTO incluir(Curso curso) {
		Assert.isNull(curso.getId(), "Não foi possível inserir o registro");
		
		if (curso.getDataInicio().isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException(
					"Não será permitida a inclusão de cursos com a data de início menor que a data atual");
		}
		
		if (curso.getDataInicio().isAfter(curso.getDataTermino())) {
			throw new IllegalArgumentException(
					"Não será permitida a inclusão de cursos com a data de início maior que a data término");
		}

		return CursoDTO.create(this.cursoRepository.save(curso));
	}

	public CursoDTO alterar(Curso curso) {
		Assert.notNull(curso.getId(), "Não foi possível atualizar o registro");
		
		Optional<Curso> optional = this.cursoRepository.findById(curso.getId());

		if (optional.isPresent()) {
			Curso cursoSalvo = optional.get();

			cursoSalvo.setDescricao(curso.getDescricao());
			cursoSalvo.setDataInicio(curso.getDataInicio());
			cursoSalvo.setDataTermino(curso.getDataTermino());
			cursoSalvo.setQuantidadeAlunosPorTurma(curso.getQuantidadeAlunosPorTurma());
			cursoSalvo.setCategoria(curso.getCategoria());
			
			this.cursoRepository.save(cursoSalvo);

			return CursoDTO.create(cursoSalvo);
		} else {
			throw new RuntimeException("Não foi possível atualizar o curso");
		}
	}

	public void deletar(Long id) {
		this.cursoRepository.deleteById(id);
	}
}
