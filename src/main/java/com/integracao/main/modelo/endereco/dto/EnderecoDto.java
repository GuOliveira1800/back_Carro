package com.integracao.main.modelo.endereco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EnderecoDto {

	private long codigo_end;
	private String cep_end;
	private String rua_end;
	private String numero_end;
	private String cidade_end;
	private String bairro_end;
	private String uf_end;
}
