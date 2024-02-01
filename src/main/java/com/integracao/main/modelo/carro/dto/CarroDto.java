package com.integracao.main.modelo.carro.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CarroDto {

	private Long codigo_car;
	private String marca_car;
	private String modelo_car;	
	private int ano_car;
	private int status_car;
	private int categoria_car;
	private BigDecimal preco_car;
	
}
