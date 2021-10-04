package com.treinamento.apostasquad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aposta")
public class Aposta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="valor")
	private Double valor;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="id_cliente")
	private Integer idCliente;
	
	@Column(name="id_situacao")
	private Integer idSituacao;

	public Aposta() {}
	
	public Aposta(Integer id, Double valor, String descricao, Integer idCliente, Integer idSituacao) {
		super();
		this.id = id;
		this.valor = valor;
		this.descricao = descricao;
		this.idCliente = idCliente;
		this.idSituacao = idSituacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(Integer idSituacao) {
		this.idSituacao = idSituacao;
	}
}
