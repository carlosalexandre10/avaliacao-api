package br.com.cast.avaliacao.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.avaliacao.dtos.CategoriaDTO;
import br.com.cast.avaliacao.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<CategoriaDTO> listar() {
		return this.categoriaRepository.findAll().stream().map(CategoriaDTO::create).collect(Collectors.toList());
	}
	
}
