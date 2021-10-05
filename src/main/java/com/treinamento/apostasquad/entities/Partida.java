package com.treinamento.apostasquad.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partida")
public class Partida {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "data")
	private Date data;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "id_estadio")
	private Integer id_estadio;
	
	public Partida() {
		super();
	}
	
	public Partida(Integer id, Date data, String descricao, Integer id_estadio) {
		super();
		this.id = id;
		this.data = data;
		this.descricao = descricao;
		this.id_estadio = id_estadio;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getId_estadio() {
		return id_estadio;
	}
	public void setId_estadio(Integer id_estadio) {
		this.id_estadio = id_estadio;
	}
}
