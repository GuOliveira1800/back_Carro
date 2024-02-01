package com.integracao.main.modelo.cliente.dto;

import com.integracao.main.modelo.endereco.dto.EnderecoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

	private Long codigo_cli;
	private int verifi_cli;
	private String nome_cli;
	private String docume_cli;
	private String telefo_cli;	
	private EnderecoDto codend_cli;
	
}
