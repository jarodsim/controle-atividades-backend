/**
 * 
 */
package com.controleatividades.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.controleatividades.model.Atividade;
import com.controleatividades.model.Grupo;
import com.controleatividades.services.AtividadeService;
import com.controleatividades.services.GrupoService;

/**
 * @author jarod
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/atividade")
public class AtividadeController {

	@Autowired
	private AtividadeService atividadeService;
	
	@Autowired
	private GrupoService grupoService;

	@GetMapping("/{id}")
	public ResponseEntity<Atividade> findById(@PathVariable Long id) {
		Atividade atividade = atividadeService.findById(id);

		if (atividade != null) {
			return ResponseEntity.ok(atividade);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Atividade>> findAll() {
		List<Atividade> atividades = atividadeService.findAll();
		return ResponseEntity.ok(atividades);
	}

	@PostMapping("/{idGrupo}")
	public ResponseEntity<Atividade> save(@Valid @RequestBody Atividade atividade, @PathVariable Long idGrupo) {
		Atividade newAtividade = atividadeService.saveNewAtividade(atividade, idGrupo);
		return ResponseEntity.ok(newAtividade);
	}

	@PutMapping
	public ResponseEntity<Atividade> update(@Valid @RequestBody Atividade atividade) {
		Atividade updatedAtividade = atividadeService.save(atividade);

		return ResponseEntity.ok(updatedAtividade);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Atividade atividade = atividadeService.findById(id);

		if (atividade != null) {
			atividadeService.delete(atividade);
		}
	}

}
