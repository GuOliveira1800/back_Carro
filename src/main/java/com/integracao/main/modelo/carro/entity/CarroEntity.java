package com.integracao.main.modelo.carro.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "carro")
@Table(name="carro",schema = "api")
@Data
public class CarroEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_car" , unique = true)
	private Long codigo_car;
	
	@Column(name="marca_car")
	private String marca_car;
	
	@Column(name="modelo_car")
	private String modelo_car;
	
	@Column(name="ano_car")
	private int ano_car;
	
	@Column(name="status_car")
	private int status_car;
	
	@Column(name="categoria_car")
	private int categoria_car;
	
	@Column(name="preco_car")
	private BigDecimal preco_car;
	
}
