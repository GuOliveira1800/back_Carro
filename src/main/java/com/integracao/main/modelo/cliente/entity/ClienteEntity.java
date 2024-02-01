package com.integracao.main.modelo.cliente.entity;

import com.integracao.main.modelo.endereco.entity.EnderecoEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "cliente")
@Table(name="cliente",schema = "api")
public class ClienteEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_cli" , unique = true)
	private long codigo_cli;
	
	@Column(name="verifi_cli")
	private int verifi_cli;
	
	@Column(name="nome_cli")
	private String nome_cli;
	
	@Column(name="docume_cli")
	private String docume_cli;
	
	@Column(name="telefo_cli")
	private String telefo_cli;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codend_cli", referencedColumnName = "codigo_end")
	private EnderecoEntity codend_cli;
}
