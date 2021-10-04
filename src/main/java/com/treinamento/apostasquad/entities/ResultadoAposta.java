package com.treinamento.apostasquad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resultadoaposta")
public class ResultadoAposta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =" id")
	private Integer id;
	@Column(name =" status_time")
	private Boolean status_time;
	@Column(name =" id_aposta")
	private Integer id_aposta;
	@Column(name =" id_time_partida")
	private Integer id_time_partida;
	
	public ResultadoAposta() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getStatus_time() {
		return status_time;
	}
	public void setStatus_time(Boolean status_time) {
		this.status_time = status_time;
	}
	public Integer getId_aposta() {
		return id_aposta;
	}
	public void setId_aposta(Integer id_aposta) {
		this.id_aposta = id_aposta;
	}
	public Integer getId_time_partida() {
		return id_time_partida;
	}
	public void setId_time_partida(Integer id_time_partida) {
		this.id_time_partida = id_time_partida;
	}
}
