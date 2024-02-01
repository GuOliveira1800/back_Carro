package com.integracao.main.modelo.relatorio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Relatorio {
	
	private String descricaoCarro;
	private String nomeCliente;
	private String dataContratacao;
	private String dataEntraga;
	private Double valorTotal;
	
}
