package com.treinamento.apostasquad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name="timepartida")
	public class TimePartida {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private Integer id;
		
		@Column(name="resultado")
		private Boolean resultado;
		
		@Column(name="id_partida")
		private Integer idPartida;
		
		@Column(name="id_time")
		private Integer idTime;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Boolean getResultado() {
			return resultado;
		}

		public void setResultado(Boolean resultado) {
			this.resultado = resultado;
		}

		public Integer getIdPartida() {
			return idPartida;
		}

		public void setIdPartida(Integer idPartida) {
			this.idPartida = idPartida;
		}

		public Integer getIdTime() {
			return idTime;
		}

		public void setIdTime(Integer idTime) {
			this.idTime = idTime;
		}
}

