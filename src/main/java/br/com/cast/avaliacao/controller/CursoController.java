package br.com.cast.avaliacao.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cast.avaliacao.dtos.CursoDTO;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<CursoDTO>> pesquisarPorDescricao(@PathVariable("descricao") String descricao) {
		List<CursoDTO> cursosDTO = this.cursoService.pesquisarPorDescricao(descricao);

		return cursosDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cursosDTO);
	}

	@PostMapping()
	public ResponseEntity<CursoDTO> incluir(@RequestBody Curso curso) {
		CursoDTO cursoDTO = this.cursoService.incluir(curso);

		URI location = getURI(cursoDTO.getId());

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<CursoDTO> alterar(@PathVariable("id") Long id, @RequestBody Curso curso) {
		curso.setId(id);

		CursoDTO cursoDTO = this.cursoService.alterar(curso);

		return cursoDTO != null ? ResponseEntity.ok(cursoDTO) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CursoDTO> deletar(@PathVariable("id") Long id) {
		this.cursoService.deletar(id);

		return ResponseEntity.ok().build();
	}

	private URI getURI(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}
