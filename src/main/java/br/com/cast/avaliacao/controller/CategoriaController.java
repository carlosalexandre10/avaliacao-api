package br.com.cast.avaliacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.avaliacao.dtos.CategoriaDTO;
import br.com.cast.avaliacao.service.CategoriaService;

@RestController
@RequestMapping("categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
    @GetMapping()
    public ResponseEntity<List<CategoriaDTO>> listar(){
    	List<CategoriaDTO> categoriasDTO = this.categoriaService.listar();
    	
    	return categoriasDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categoriasDTO);
    }
}
