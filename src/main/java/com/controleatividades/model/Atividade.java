/**
 * 
 */
package com.controleatividades.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author jarod
 *
 */
@Entity
@Table(name = "atividade")
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false)
	private String descricao;

	private LocalDate dataEntrega;

	@NotNull
	@Column(nullable = false)
	private boolean concluida;

	private int position;

	public void updateDestinoPosition(int pos) {
		if (position >= pos)
			position--;
	}

	public void updateOrigemPosition(int pos) {
		if (position <= pos)
			position--;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Boolean getConcluida() {
		return concluida;
	}

	public void setConcluida(Boolean concuida) {
		this.concluida = concuida;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
