/**
 * 
 */
package com.controleatividades.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controleatividades.model.Atividade;
import com.controleatividades.model.Grupo;
import com.controleatividades.repositories.AtividadeRepository;
import com.controleatividades.repositories.GrupoRepository;

/**
 * @author jarod
 *
 */
@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository repository;

	@Autowired
	private GrupoService grupoService;

	public Atividade save(Atividade atividade) {
		return repository.save(atividade);
	}

	public Atividade saveNewAtividade(Atividade atividade, Long idGrupo) {
		Grupo grupo = grupoService.findById(idGrupo);
		int tamanho = grupo.getAtividades().size();
		atividade.setPosition(tamanho++);

		Atividade atividadeCreated = repository.save(atividade);

		grupo.getAtividades().add(atividadeCreated);
		grupoService.save(grupo);
		return atividadeCreated;
	}

	public List<Atividade> findAll() {
		return repository.findAll();
	}

	public Atividade findById(Long id) {
		Optional<Atividade> optional = repository.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public void delete(Atividade atividade) {
		Optional<Atividade> optional = repository.findById(atividade.getId());

		if (optional.isPresent()) {
			repository.delete(atividade);
		}
	}

}
