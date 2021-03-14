package br.com.cast.avaliacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.avaliacao.dtos.CursoDTO;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.service.CursoService;

@RestController
@RequestMapping("/cursos")
@CrossOrigin("http://localhost:4200")
public class CursoController {

	@Autowired
	private CursoService cursoService;

	@GetMapping()
	public ResponseEntity<List<CursoDTO>> listar() {
		List<CursoDTO> cursosDTO = this.cursoService.listar();

		return cursosDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cursosDTO);
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<CursoDTO>> pesquisarPorDescricao(@PathVariable String descricao) {
		List<CursoDTO> cursosDTO = this.cursoService.pesquisarPorDescricao(descricao);

		return cursosDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cursosDTO);
	}

	@PostMapping()
	public ResponseEntity<CursoDTO> incluir(@RequestBody @Valid Curso curso) {
		CursoDTO cursoDTO = this.cursoService.incluir(curso);

		return ResponseEntity.status(HttpStatus.CREATED).body(cursoDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CursoDTO> alterar(@PathVariable Long id, @RequestBody Curso curso) {
		curso.setId(id);

		CursoDTO cursoDTO = this.cursoService.alterar(curso);

		return cursoDTO != null ? ResponseEntity.ok(cursoDTO) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CursoDTO> deletar(@PathVariable Long id) {
		this.cursoService.deletar(id);

		return ResponseEntity.ok().build();
	}
}
