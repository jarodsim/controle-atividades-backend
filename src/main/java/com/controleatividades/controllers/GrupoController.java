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
import com.controleatividades.services.GrupoService;

/**
 * @author jarod
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;

	@GetMapping("/{id}")
	public ResponseEntity<Grupo> findById(@PathVariable Long id) {
		Grupo grupo = grupoService.findById(id);

		if (grupo != null) {
			return ResponseEntity.ok(grupo);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping()
	public ResponseEntity<List<Grupo>> findAll() {
		List<Grupo> grupos = grupoService.findAll();
		return ResponseEntity.ok(grupos);
	}

	@PostMapping()
	public ResponseEntity<Grupo> save(@Valid @RequestBody Grupo grupo) {
		Grupo newGrupo = grupoService.save(grupo);
		return ResponseEntity.ok(newGrupo);
	}

	@PutMapping("/mover/{origem}/{destino}/{atividade}/{position}/{newPosition}")
	public ResponseEntity<List<Grupo>> mover(@PathVariable Long origem, @PathVariable Long destino,
			@PathVariable Long atividade, @PathVariable int position, @PathVariable int newPosition) {
		return ResponseEntity.ok(grupoService.mover(origem, destino, atividade, position, newPosition));
	}

	@PostMapping("/{id}/atividade")
	public ResponseEntity<Grupo> save(@PathVariable Long id, @Valid @RequestBody Atividade atividade) {
		Grupo grupo = grupoService.findById(id);
		atividade.setPosition(grupo.getAtividades().size() + 1);
		grupo.getAtividades().add(atividade);
		return ResponseEntity.ok(grupoService.save(grupo));
	}

	@PutMapping()
	public ResponseEntity<Grupo> update(@RequestBody Grupo grupo) {
		Grupo updateGrupo = grupoService.save(grupo);
		return ResponseEntity.ok(updateGrupo);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Grupo grupo = grupoService.findById(id);

		if (grupo != null) {
			grupoService.delete(grupo);
		}
	}

}
