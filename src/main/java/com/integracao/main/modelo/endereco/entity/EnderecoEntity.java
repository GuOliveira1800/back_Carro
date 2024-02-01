package com.integracao.main.modelo.endereco.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "endereco")
@Table(name="endereco",schema = "api")
public class EnderecoEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_end" , unique = true)
	private long codigo_end;
	
	@Column(name="cep_end")
	private String cep_end;
	
	@Column(name="rua_end")
	private String rua_end;
	
	@Column(name="numero_end")
	private String numero_end;
	
	@Column(name="cidade_end")
	private String cidade_end;
	
	@Column(name="bairro_end")
	private String bairro_end;
	
	@Column(name="uf_end")
	private String uf_end;
}
