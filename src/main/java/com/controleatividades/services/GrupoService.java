/**
 * 
 */
package com.controleatividades.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controleatividades.model.Atividade;
import com.controleatividades.model.Grupo;
import com.controleatividades.repositories.GrupoRepository;

/**
 * @author jarod
 *
 */
@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private AtividadeService atividadeService;

	public Grupo save(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	public List<Grupo> findAll() {
		List<Grupo> grupos = grupoRepository.findAll();

		grupos.forEach(g -> {
			g.getAtividades().sort(Comparator.comparing(a -> a.getPosition()));
		});

		return grupos;
	}

	public Grupo findById(Long id) {
		Optional<Grupo> optional = grupoRepository.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public void delete(Grupo grupo) {
		Optional<Grupo> optional = grupoRepository.findById(grupo.getId());
		if (optional.isPresent()) {
			grupoRepository.delete(grupo);
		}
	}

	public List<Grupo> mover(Long origem, Long destino, Long atividade, int position, int newPosition) {
		Grupo grupoOrigem = findById(origem);
		Grupo grupoDestino = findById(destino);

		if (grupoOrigem == grupoDestino) {
			Atividade atividadeMoved = atividadeService.findById(atividade);

			if (position < newPosition) {
				grupoOrigem.getAtividades().forEach(a -> a.updateOrigemPosition(newPosition));
			} else {
				grupoOrigem.getAtividades().forEach(a -> a.updateDestinoPosition(newPosition));
			}

			atividadeMoved.setPosition(newPosition);
			atividadeService.save(atividadeMoved);

			return findAll();
		} else {
			return findAll();
		}

		/*
		 * grupoOrigem.getAtividades().remove(atividadeMoved); int positionAnterior =
		 * atividadeMoved.getPosition(); grupoOrigem.getAtividades().forEach(a ->
		 * a.updateOrigemPosition(positionAnterior));
		 * grupoDestino.getAtividades().forEach(a -> a.updateDestinoPosition(position));
		 * atividadeMoved.setPosition(position);
		 * grupoDestino.getAtividades().add(atividadeMoved);
		 * grupoRepository.saveAll(Arrays.asList(grupoOrigem, grupoDestino));
		 * 
		 * return findAll();
		 */
	}
}
