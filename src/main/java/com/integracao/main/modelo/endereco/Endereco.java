package com.integracao.main.modelo.endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

	private long codigo_end;
	private String cep_end;
	private String rua_end;
	private String numero_end;
	private String cidade_end;
	private String bairro_end;
	private String uf_end;

}
