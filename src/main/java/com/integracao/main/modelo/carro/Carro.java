package com.integracao.main.modelo.carro;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carro {

	private int codigo_car;
	private String marca_car;
	private String modelo_car;	
	private int ano_car;
	private int status_car;
	private int categoria_car;
	private BigDecimal preco_car;
		
}
