package com.integracao.main.modelo.cliente;

import com.integracao.main.modelo.endereco.Endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

	private int codigo_cli;
	private int verifi_cli;
	private String nome_cli;
	private String docume_cli;
	private String telefo_cli;	
	private Endereco codend_cli;
		
}
