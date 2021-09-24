/**
 * 
 */
package com.controleatividades.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controleatividades.model.Atividade;

/**
 * @author jarod
 *
 */
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{

}
