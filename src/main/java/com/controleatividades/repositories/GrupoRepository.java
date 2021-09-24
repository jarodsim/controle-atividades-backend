/**
 * 
 */
package com.controleatividades.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controleatividades.model.Grupo;

/**
 * @author jarod
 *
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
